import java.awt.*;


public class Priest extends BasicKnight
{
    /**
     * priest's special skill is healing
     */
    private final int specialskillheal;
    public Priest(){
        super();
        this.shape=new Polygon(new int[] {-15, -15, 15,15}, new int[] {15, -15, -15,15}, 4);
        this.health=350;
        this.speed=50;
        this.normalAttackRange=30;
        this.normal_damage=150;
        this.specialSkillRange=100;
        this.specialskillheal=75;
        this.knighttype="Priest";
    }


    /**
     * @param knight this is knight to be attacked
     * if distance less then normal attack range then attack it
     * update necessary fields dmg,score,kill count etc
     */
    @Override
    public void normalAttack(Knight knight) {
        if(this.getPosition().distanceTo(knight.getPosition().getIntX(),knight.getPosition().getIntY())<normalAttackRange){
            normalattackhappened=true;
            this.setTotaldamage(this.getTotaldamage()+normal_damage);
            knight.setHealth(knight.getHealth()-normal_damage);
            //System.out.println("priest is attacking");
            if(knight.isdead()){
                this.setScore(this.getScore()+75);
                this.updatekillcount();
            }

        }
    }


    /**
     * @param knight this is the base knight not opponent in order to find its team easily
     * heal its own and its allies by the amount of specialskillheal
     */
    @Override
    public void specialSkill(Knight knight) {
        specialattackhappened=true;
        this.setHealth(this.getHealth()+specialskillheal);
        for (Knight k:Simulation.getInstance().findteamof(knight).getKnightList()
             ) {
            if(this.position.distanceTo(k.getPosition().getIntX(),k.getPosition().getIntY())<specialSkillRange){
                k.setHealth(k.getHealth()+specialskillheal);
            }
        }
    }


}
