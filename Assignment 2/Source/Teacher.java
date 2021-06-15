import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Teacher class controls game flow by accessing game methods
 * and do necessary actions such as removing chair,playing music etc
 */
public class Teacher extends Thread {
    private final MusicalChairsGame game;

    public Teacher(MusicalChairsGame game) {
        this.game=game;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{"Student1", "Student2", "Student3","Student4","Student5"};
        }

        int numberOfStudents = args.length;
        MusicalChairsGame game = new MusicalChairsGame(numberOfStudents);



        for (int i = 0; i < numberOfStudents; i++) {
            new Student(args[i], game).start();
        }

        new Teacher(game).start();
    }


    /**
     * @param roundnumber current round number to be printed
     * outputs current round number at beginning of each round
     */
    public void roundAboutBegin(int roundnumber)
    {
        System.out.println("Teacher: Be ready!" + " Round " + roundnumber + " is about the begin now.");
    }

    /**
     * output to be printed when music starts
     */
    public void startMusic()
    {
        System.out.println("Teacher: The music is started!");
    }


    /**
     * output to be printed after music has started
     */
    public void playMusic()
    {
        System.out.println("Teacher: The music will be stopped in random amount of time...");
    }

    /**
     * output to be printed when music stops
     */
    public void stopMusic()
    {
        System.out.println("Teacher: The music is stopped!");
    }

    /**
     * output to be printed when teacher removes a chair.
     */
    public void removeChair()
    {
        System.out.println("Teacher: I am removing one chair.");
    }

    /**
     * Prints question in order to satisfy output
     */
    public void askGameHistory()
    {
        System.out.println("Teacher: You did great job kids. The game is over. How was the game?");
    }

    
    @Override
    public void run() {
        while (true) {
                if (game.barrierRoundBegins.hasreachedbarrier()) {
                    roundAboutBegin(game.getCurrentRound());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    game.barrierRoundBegins.setNumberOfThreadsCurrentlyWaiting(0);
                    game.barrierRoundBegins.awakeallthreads();
                }
                if (game.barrierMusicStarts.hasreachedbarrier()) {
                    startMusic();
                    try {
                        playMusic();
                        game.barrierMusicStarts.setNumberOfThreadsCurrentlyWaiting(0);
                        game.barrierMusicStarts.awakeallthreads();
                        game.playMusicForARandomDuration();
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                        e.printStackTrace();
                    }
                }
                if(game.barrierMusicStops.hasreachedbarrier())
                {
                    stopMusic();
                    game.barrierMusicStops.setNumberOfThreadsCurrentlyWaiting(0);
                    game.barrierMusicStops.awakeallthreads();

                }
                if(game.barrierPlayersSettle.hasreachedbarrier())
                {
                    game.removeOneChair();
                    removeChair();
                    System.out.println("--------------------");


                    game.setCurrentRound(game.getCurrentRound()+1);
                    game.standUpFromChair();

                    game.barrierRoundBegins.setNumberOfThreadsToReachBarrierPoint(game.barrierRoundBegins.getNumberOfThreadsToReachBarrierPoint()-1);
                    game.barrierMusicStarts.setNumberOfThreadsToReachBarrierPoint(game.barrierMusicStarts.getNumberOfThreadsToReachBarrierPoint()-1);
                    game.barrierMusicStops.setNumberOfThreadsToReachBarrierPoint(game.barrierMusicStops.getNumberOfThreadsToReachBarrierPoint()-1);
                    game.barrierPlayersSettle.setNumberOfThreadsToReachBarrierPoint(game.barrierPlayersSettle.getNumberOfThreadsToReachBarrierPoint()-1);

                    game.barrierPlayersSettle.setNumberOfThreadsCurrentlyWaiting(0);
                    game.barrierPlayersSettle.awakeallthreads();
                }
                if(game.barrierReporting.hasreachedbarrier()){
                    askGameHistory();
                    game.barrierReporting.setNumberOfThreadsCurrentlyWaiting(0);
                    game.barrierReporting.awakeallthreads();
                    break;
                }
        }

    }

}

