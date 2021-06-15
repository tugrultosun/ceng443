import java.util.Random;

public class RandomMove extends Strategy
{

    /**
     * @param knight which will use this strategy
     * randomly move around map
     * attack the close knight if it can
     */
    @Override
    public void act(Knight knight) {
        /*
            Generate random coordinates if it is in borders then keep executing it.
        */

        Random ran = new Random();
        int x = ran.nextInt(600)+100;
        int y = ran.nextInt(400)+100;
        knight.trytomove(knight,x,y);
        Knight knight2=Simulation.getInstance().getclosestKnight(Simulation.getInstance().findopponentteamof(knight),knight);
        if(knight.isCanattack()){
            knight.normalAttack(knight2);
            knight.setCanattack(false);
        }
        if(knight.isCanspecialattack()){
            knight.setCanspecialattack(false);
            Random r=new Random();
            if(r.nextInt(100)<20){
                knight.specialSkill(knight);
            }
        }
    }
}
