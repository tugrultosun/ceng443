import javax.swing.*;
import java.awt.*;


public class Display extends JFrame {

    private static Display instance;
    public JPanel mainMap;
    private JLabel labelElMorad;
    private JLabel labelKarus;
    private JLabel labelScore;
    private JLabel labelScoreElMorad;
    private JLabel labelScoreKarus;
    private JLabel labelKills;
    private JLabel labelKillElMorad;
    private JLabel labelKillKarus;
    private JLabel labelTotalDamage;
    private JLabel labelTotalDamageElMorad;
    private JLabel labelTotalDamageKarus;
    private JLabel koLogo;
    private int minimumXPanel;
    private int minimumYPanel;
    private int width;
    private int height;

    public Display(JPanel p) {

        mainMap = p;
        initialize();

        this.minimumXPanel = 230;
        this.minimumYPanel = 0;
        this.height = 700;
        this.width = 1100;

    }

    public static Display getInstance() {
        if (instance == null) {
            Simulation simulationInstance = Simulation.getInstance();
            Display.instance = new Display(simulationInstance);
            simulationInstance.setDisplay(Display.instance);
            Display.instance.run();
        }
        return instance;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    private void run() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setFont(new Font("System", Font.PLAIN, 14));

        setTitle("Knight Online");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        JPanel infoPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                this.removeAll();
                labelElMorad = new JLabel("El Morad");
                labelKarus = new JLabel("Karus");

                labelElMorad.setBounds(45, 5, 150, 15);
                labelElMorad.setHorizontalAlignment(SwingConstants.CENTER);
                add(labelElMorad);

                labelKarus.setBounds(55, 5, 150, 15);
                labelKarus.setHorizontalAlignment(SwingConstants.RIGHT);
                add(labelKarus);

                labelScore = new JLabel("Score:");
                labelScore.setBounds(10, 45, 150, 15);
                labelScore.setHorizontalAlignment(SwingConstants.LEFT);
                add(labelScore);

                labelScoreElMorad = new JLabel( Integer.toString(Simulation.getInstance().getTeam1().getScore()) ); //TODO
                labelScoreElMorad.setBounds(45, 45, 150, 15);
                labelScoreElMorad.setHorizontalAlignment(SwingConstants.CENTER);
                add(labelScoreElMorad);

                labelScoreKarus = new JLabel(Integer.toString(Simulation.getInstance().getTeam2().getScore())); //TODO
                labelScoreKarus.setBounds(55, 45, 150, 15);
                labelScoreKarus.setHorizontalAlignment(SwingConstants.RIGHT);
                add(labelScoreKarus);

                labelKills = new JLabel("Kills:");
                labelKills.setBounds(10, 65, 150, 15);
                labelKills.setHorizontalAlignment(SwingConstants.LEFT);
                add(labelKills);

                labelKillElMorad = new JLabel(Integer.toString(Simulation.getInstance().getTeam1().getTeamKillCount())); //TODO
                labelKillElMorad.setBounds(45, 65, 150, 15);
                labelKillElMorad.setHorizontalAlignment(SwingConstants.CENTER);
                add(labelKillElMorad);

                labelKillKarus = new JLabel(Integer.toString(Simulation.getInstance().getTeam2().getTeamKillCount())); //TODO
                labelKillKarus.setBounds(55, 65, 150, 15);
                labelKillKarus.setHorizontalAlignment(SwingConstants.RIGHT);
                add(labelKillKarus);

                labelTotalDamage = new JLabel("T. Damage:");
                labelTotalDamage.setBounds(10, 85, 150, 15);
                labelTotalDamage.setHorizontalAlignment(SwingConstants.LEFT);
                add(labelTotalDamage);

                labelTotalDamageElMorad = new JLabel(Integer.toString(Simulation.getInstance().getTeam1().getTotalDamage())); //TODO
                labelTotalDamageElMorad.setBounds(45, 85, 150, 15);
                labelTotalDamageElMorad.setHorizontalAlignment(SwingConstants.CENTER);
                add(labelTotalDamageElMorad);

                labelTotalDamageKarus = new JLabel(Integer.toString(Simulation.getInstance().getTeam2().getTotalDamage())); //TODO
                labelTotalDamageKarus.setBounds(55, 85, 150, 15);
                labelTotalDamageKarus.setHorizontalAlignment(SwingConstants.RIGHT);
                add(labelTotalDamageKarus);

                ImageIcon koImageIcon = new ImageIcon("koicon.jpg");
                koLogo = new JLabel(koImageIcon);
                koLogo.setBounds(0, 230, 250, 350);
                koLogo.setHorizontalAlignment(SwingConstants.CENTER);
                add(koLogo);
            }
        };

        infoPanel.setBackground(Color.WHITE);
        infoPanel.setPreferredSize(new Dimension(300, 600));
        infoPanel.setMinimumSize(new Dimension(300, 600));
        infoPanel.setSize(300, 600);
        infoPanel.setLayout(null);
        container.add(infoPanel);
        container.add(mainMap);
        pack();
    }

}
