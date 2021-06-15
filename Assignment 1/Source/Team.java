import java.util.ArrayList;

public class Team
{
    //TOD0
    private final String teamName;
    private int teamKillCount;
    private int teamSize;
    private int totalDamage;
    private final ArrayList<Knight> knights;
    private int score;
    Team(String teamName){
        teamSize = 8;
        this.teamName=teamName;
        this.teamKillCount=0;
        this.totalDamage=0;
        this.score=0;
        knights= new ArrayList<>();
    }

    public void updateKillCount(int killcount){
        this.teamKillCount+=killcount;
    }

    public int getTeamKillCount() {
        int s=teamKillCount;
        for (Knight k:knights
             ) {
            s+=k.getKillcount();
        }
        return s;
    }

    /**
     * @param _damage updates the total damage of team with this given amount
     */
    public void updateTotalDamage(int _damage){
        this.totalDamage+=_damage;
    }


    /**
     * @return total damage applied by team in opponents
     */
    public int getTotalDamage() {
        int s=totalDamage;
        for (Knight k:knights
        ) {
            s+=k.getTotaldamage();
        }
        return s;
    }

    /**
     * @param score updates score of team with given score amount
     */
    public void updateScore(int score) {
        this.score += score;
    }

    /**
     * @return this returns current score of team used to display score in panel
     */
    public int getScore() {
        int s=score;
        for (Knight k:knights
             ) {
            s+=k.getScore();
        }
        return s;
    }





    /**
     * @param knight adds this knight to team
     */
    public void addKnightToTeam(Knight knight){
        knights.add(knight);
    }

    /**
     * @return knight list which contains knights of team
     */
    public ArrayList<Knight> getKnightList(){
        return knights;
    }

    public void removeKnightFromTeam(Knight knight){
        knights.remove(knight);
    }

}
