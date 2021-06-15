import java.util.Random;

public class AttackClosest extends Strategy
{


    /**
     * @param knight this is the parameter that gonna use this strategy
     * First we find team that contains this knight parameter then we find closest knight
     * attack our cool down is enough to attack,set cool down if attacks happened
     */
    @Override
    public void act(Knight knight) {
            Knight knight2=Simulation.getInstance().getclosestKnight(Simulation.getInstance().findopponentteamof(knight),knight);
            knight.trytomove(knight,knight2.getPosition().getIntX(),knight2.getPosition().getIntY());
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
