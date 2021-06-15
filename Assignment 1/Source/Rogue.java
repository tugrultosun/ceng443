import java.awt.*;

public class Rogue extends BasicKnight
{
    //TOD0
    private final int specialskilldmg;

    public Rogue(){
        super();
        this.shape=new Polygon(new int[] {-20, 0, 20}, new int[] {20, -20, 20}, 3);
        this.health=500;
        this.speed=100;
        this.normalAttackRange=30;
        this.normal_damage=150;
        this.specialSkillRange=200;
        this.specialskilldmg=250;
        this.knighttype="rogue";
    }

    @Override
    public void normalAttack(Knight knight) {
        if(this.getPosition().distanceTo(knight.getPosition().getIntX(),knight.getPosition().getIntY())<normalAttackRange){
            normalattackhappened=true;
            this.setTotaldamage(this.getTotaldamage()+normal_damage);
            knight.setHealth(knight.getHealth()-normal_damage);
            //System.out.println("rogue is attacking");
            if(knight.isdead()){
                this.setScore(this.getScore()+75);
                this.updatekillcount();
            }

        }
    }

    @Override
    public void specialSkill(Knight knight) {

        for (Knight k:Simulation.getInstance().findopponentteamof(knight).getKnightList()
             ) {
            if(knight.position.distanceTo(k.getPosition().getIntX(),k.getPosition().getIntY())<specialSkillRange){
                this.speed*=2;
                while (knight.position.distanceTo(k.getPosition().getIntX(),k.getPosition().getIntY())>normalAttackRange){
                    knight.trytomove(knight,k.getPosition().getIntX(),k.getPosition().getIntY());
                    if(knight.position.distanceTo(k.getPosition().getIntX(),k.getPosition().getIntY())<normalAttackRange){
                        this.speed/=2;
                        specialattackhappened=true;
                        knight.setTotaldamage(knight.getTotaldamage()+specialskilldmg);
                        k.setHealth(k.getHealth()-specialskilldmg);
                        //System.out.println("rogue is attacking");
                        if(k.isdead()){
                            knight.setScore(this.getScore()+75);
                            knight.updatekillcount();
                        }
                        break;
                    }
                }
                break;
            }
        }
    }



}
