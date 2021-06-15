import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;



public class Simulation extends JPanel {
    private static Simulation instance;
    public Display display;
    private Team team1;
    private Team team2;
    private ArrayList<KnightFactory> knightfactory;
    private ArrayList<Knight> toberemovedknights1;
    private ArrayList<Knight> toberemovedknights2;
    private Random ran;
    public Simulation() {
        // TODO
        knightfactory=new ArrayList<>();
        knightfactory.add(new RogueFactory());
        knightfactory.add(new PriestFactory());
        knightfactory.add(new MageFactory());
        team1=new Team("El Morad");
        team2=new Team("Karus");
        toberemovedknights1=new ArrayList<>();
        toberemovedknights2=new ArrayList<>();
        ran=new Random();
        //random bound shows upper bound which is exclusive
        for (int i = 0; i < 8; i++) {
            Random ran = new Random();
            int x = ran.nextInt(3) ;
            knightfactory.get(x).produce(team1).setPosition(50,50);
        }
        for (int i = 0; i < 8; i++) {
            Random ran = new Random();
            int x = ran.nextInt(3);
            knightfactory.get(x).produce(team2).setPosition(700,500);
        }
    }

    public void setDisplay(Display displayInstance) {
        display = displayInstance;
    }

    public synchronized static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    public static void main(String[] args) {
        Display.getInstance();

        while (true) {
            try {
                Thread.sleep(30);
                Display.getInstance().repaint();
                Simulation.getInstance().stepAll();
                // TODO
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * Stepping through all knights in team1 then team2
     * if someone is dead add them to toberemovedlist,we remove dead ones
     * after all knights step,but we produce new knights from factory
     * if a knight is dead,update is necessary in order to not to remove
     * their score,kill count etc.
     */
    public void stepAll() {
       for (Knight k1:team1.getKnightList()
             ) {
            k1.step();
           //System.out.println(k1.getStrategy().toString());
            if(k1.getHealth()<=0){
                toberemovedknights1.add(k1);
            }
            else {
                int i=team1.getKnightList().indexOf(k1);
                k1=needsdecoration(k1);
                team1.getKnightList().set(i,k1);
            }
        }
       for (int i=0;i<toberemovedknights1.size();i++){
           int x = ran.nextInt(3) ;
           knightfactory.get(x).produce(team1).setPosition(50,50);
       }
       team1.updateScore(toberemovedknights1.stream().mapToInt(Knight::getScore).sum());
       team1.updateKillCount(toberemovedknights1.stream().mapToInt(Knight::getKillcount).sum());
       team1.updateTotalDamage(toberemovedknights1.stream().mapToInt(Knight::getTotaldamage).sum());
        for (Knight k2:team2.getKnightList()
        ) {
            k2.step();
            if(k2.getHealth()<=0){
                toberemovedknights2.add(k2);
            }
            else{
                int i=team2.getKnightList().indexOf(k2);
                k2=needsdecoration(k2);
                team2.getKnightList().set(i,k2);
            }
        }
        team2.updateScore(toberemovedknights2.stream().mapToInt(Knight::getScore).sum());
        team2.updateKillCount(toberemovedknights2.stream().mapToInt(Knight::getKillcount).sum());
        team2.updateTotalDamage(toberemovedknights2.stream().mapToInt(Knight::getTotaldamage).sum());
        for (int i=0;i<toberemovedknights2.size();i++){
            int x = ran.nextInt(3) ;
            knightfactory.get(x).produce(team2).setPosition(700,500);
        }
        team1.getKnightList().removeAll(toberemovedknights1);
        toberemovedknights1.clear();
        team2.getKnightList().removeAll(toberemovedknights2);
        toberemovedknights2.clear();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            Image image = ImageIO.read(new File("background.jpg"));
            g.drawImage(image, 0, 0, 800, 600, null);
            for (int i = 0; i <8 ; i++) {
                team1.getKnightList().get(i).decorate(g,Color.BLUE);
                team2.getKnightList().get(i).decorate(g,Color.RED);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param knight to be checked if in need of decoration
     * @return decorated knight
     */
    public Knight needsdecoration(Knight knight){
        if(knight.getScore()<=75)return new Noob(knight);
        else if(knight.getScore()<=150)return new Grade3Decorator(knight);
        else if(knight.getScore()<=300)return new Grade2Decorator(knight);
        else if(knight.getScore()>=300)return new Grade1Decorator(knight);
        else return knight;

    }



    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }


    /**
     * @param team this is team in which we gonna search weakest knight
     * @return the that has lowest hp
     */
    public Knight getWeakestKnight(Team team){
        return Collections.min(team.getKnightList(), Comparator.comparing(k->k.getHealth()));
    }

    /**
     * @param team this is the team that that we gonna search in
     * @param knight this is the knight to compare distance to other knights
     * @return a knight that has lowest distance to knight
     */
    public Knight getclosestKnight(Team team,Knight knight){
        return Collections.min(team.getKnightList(),Comparator.comparing(k->k.getPosition().distanceTo(knight.getPosition().getIntX(),knight.getPosition().getIntY())));
    }

    /**
     * @param knight knight to be searched in teams
     * @return  the team of given knight as parameter
     */
    public Team findteamof(Knight knight){
        if(team1.getKnightList().contains(knight))return team1;
        if(team2.getKnightList().contains(knight))return team2;
        return null;
    }

    /**
     * @param knight knight to be searched in teams
     * @return  the opponent team of given knight as parameter
     */
    public Team findopponentteamof(Knight knight){
        if(team1.getKnightList().contains(knight))return team2;
        if(team2.getKnightList().contains(knight))return team1;
        return null;
    }
}
