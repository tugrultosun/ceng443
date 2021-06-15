import java.util.ArrayList;
import java.util.List;

/**
 * Holds student information such as if won game,which round has lost,
 * list of chairs which has been sit by student,etc.
 * overriding run method to extend thread class
 */
public class Student extends Thread {
    private final String name;
    private final MusicalChairsGame game;
    private final List<Integer> sitList;
    private int lostRound;
    private boolean hasWon;
    public Student(String name, MusicalChairsGame game) {
        this.name=name;
        this.game=game;
        this.sitList=new ArrayList<>();
        hasWon=false;
    }

    /**
     * default message when waiting new round
     */
    public void waitingNewRound()
    {
        System.out.println( name + ": " + "I'm ready for the new round.");
    }

    /**
     * default message when walking around chairs
     */
    public void walkingAround(){
        System.out.println( name + ": " + "I'm walking around.");
    }

    /**
     * @param chair_id the id to be added to sitList
     */
    public void addChairToSitList(int chair_id){
        sitList.add(chair_id);
    }


    /**
     * @param c occupied chair
     * if chair is null then student couldn't sit this round
     */
    public void gotChairOrNot(Chair c){
        if(c!=null){
            System.out.println(name + ": " + "I got chair " + c.getID() + ".");
        }
        else {
            System.out.println(name + ": " + "I couldn't get any chair.");
        }
    }


    /**
     * @param lostRound setting at which round student has lost
     * will be called only when student
     */
    public void setLostRound(int lostRound) {
        this.lostRound = lostRound;
    }


    /**
     * outputs game summary for student
     * based on winning message changes
     * based on how many chairs has been sit message changes
     */
    public synchronized void printGameSummary() {
        synchronized (System.out){
            if(hasWon){
                System.out.print(name + ": " + "I won the game :)" +" I sat on chairs ");
                int n=sitList.size();
                for(int i=0;i<n; i++){
                    if(i== n-1){
                        System.out.println(sitList.get(i)+".");
                        break;
                    }
                    System.out.print(sitList.get(i)+", ");
                }
            }
            else {
                if(sitList.size() == 0)
                {
                    System.out.println(name + ": I lost the game in round " + lostRound + "! I never got any chairs.");
                }
                else if(sitList.size()==1)
                {
                    System.out.println(name + ": I lost the game in round " + lostRound +"! I sat on chair " + sitList.get(0) + "." );
                }
                else
                {
                    System.out.print(name + ": I lost the game in round " + lostRound +"! I sat on chairs ");
                    int n=sitList.size();
                    for(int i=0;i<n; i++){
                        if(i== n-1){
                            System.out.println(sitList.get(i)+".");
                            break;
                        }

                        System.out.print(sitList.get(i)+", ");
                    }
                }
            }
        }

    }

    @Override
    public void run() {
        while (true){
            game.barrierRoundBegins.await();
            waitingNewRound();
            game.barrierMusicStarts.await();
            walkingAround();
            game.barrierMusicStops.await();
            Chair c=game.sitDownOnAnAvailableChair(this);
            if(c!=null){
                gotChairOrNot(c);
                game.barrierPlayersSettle.await();
            }
            else {
                //chair is null so pass it directly here
                /*
                 * if there is no available chair then wait at settle barrier
                 * when notified then wait at reporting barrier,then object will be
                 * notified at the end of game.
                 */
                gotChairOrNot(null);
                game.barrierPlayersSettle.await();
                game.barrierReporting.await();
                printGameSummary();
                break;
            }
            //if player got chair it will wait at player settle barrier
            //if all reached barrier then we update current round and notify
            //awaiting threads at end so execution will come to here and if current round
            //becomes last round then student will be marked as won and will wait at reporting barrier
            if(game.getCurrentRound()==game.getNumberOfPlayers()){
                hasWon=true;
                game.barrierReporting.await();
                printGameSummary();
                break;
            }
        }
    }


}

