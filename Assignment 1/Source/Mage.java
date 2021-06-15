import java.awt.*;

public class Mage extends BasicKnight {
    //TODO

    private int specialskilldmg;
    public Mage() {
        super();
        this.shape=new Polygon(new int[]{-20,0,20,0},new int[]{0,20,0,-20}, 4);
        this.health = 400;
        this.speed = 75;
        this.normalAttackRange = 100;
        this.normal_damage = 100;
        this.specialSkillRange = 100;
        this.specialskilldmg = 75;
        this.knighttype="mage";
    }

    @Override
    public void translatepoly(int x, int y) {
        shape.translate(x,y);
    }

    @Override
    public Polygon getShape() {
        return this.shape;
    }


    @Override
    public void normalAttack(Knight knight) {
        if(this.getPosition().distanceTo(knight.getPosition().getIntX(),knight.getPosition().getIntY())<normalAttackRange){
            normalattackhappened=true;
            this.setTotaldamage(this.getTotaldamage()+normal_damage);
            knight.setHealth(knight.getHealth()-normal_damage);
            //System.out.println("mage is attacking");
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
            if(this.position.distanceTo(k.getPosition().getIntX(),k.getPosition().getIntY())<specialSkillRange){
                specialattackhappened=true;
                this.setTotaldamage(this.getTotaldamage()+normal_damage);
                k.setHealth(k.getHealth()-specialskilldmg);
                if(knight.isdead()){
                    this.setScore(this.getScore()+75);
                    this.updatekillcount();
                }
            }
        }
    }



}
