import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that will be used in order to hold game related information such as
 *
 */
public class MusicalChairsGame {
    public Barrier barrierRoundBegins;
    public Barrier barrierMusicStarts;
    public Barrier barrierMusicStops;
    public Barrier barrierPlayersSettle;
    public Barrier barrierReporting;
    private List<Chair> chairs;
    private int numberOfPlayers;
    private int currentRound;
    public MusicalChairsGame(int numberOfPlayers) {
        this.numberOfPlayers=numberOfPlayers;
        chairs=new ArrayList<>(numberOfPlayers-1);
        for (int i = 0; i < numberOfPlayers-1; i++) {
            chairs.add(new Chair(i));
        }
        barrierRoundBegins=new Barrier(numberOfPlayers);
        barrierMusicStarts=new Barrier(numberOfPlayers);
        barrierMusicStops=new Barrier(numberOfPlayers);
        barrierPlayersSettle=new Barrier(numberOfPlayers);
        barrierReporting=new Barrier(numberOfPlayers);
        currentRound =1;
    }

    /**
     * @return number of players
     */
    public int getNumberOfPlayers()
    {
        return numberOfPlayers;
    }

    /**
     * @return current round of game
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * @param currentRound round# which will be set
     */
    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }


    /**
     * plays music for 4-9 seconds of time
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public void playMusicForARandomDuration() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        URL myURL= ClassLoader.getSystemResource("Drum.wav");
        AudioInputStream audio = AudioSystem.getAudioInputStream(myURL);
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.setMicrosecondPosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        try {
            Random ran=new Random();
            int seconds=ran.nextInt(6)+4;
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clip.stop();
    }


    /**
     * @param student student which will sit on a available chair
     * @return  the chair which has been sit by this student
     * if there is no available chair then return null and set the round
     * for that student as lost
     */
    public synchronized Chair sitDownOnAnAvailableChair(Student student) {
        for (Chair c:chairs
             ) {
            if(c.isAvailable()){
                c.setAvailable(false);
                student.addChairToSitList(c.getID());
                return c;
            }
        }
        student.setLostRound(currentRound);
        return null;
    }


    /**
     * makes all chairs as available
     */
    public synchronized void standUpFromChair() {
        for (Chair c:chairs
             ) {
            c.setAvailable(true);
        }
    }


    /**
     * removes one chair from end of list
     */
    public synchronized void removeOneChair() {
        chairs.remove(chairs.size() - 1);
    }


}
