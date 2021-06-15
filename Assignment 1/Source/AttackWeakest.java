import java.util.Random;


public class AttackWeakest extends Strategy
{

    /**
     * @param knight this is the knight that gonna use this strategy
     * First we find team that contains this knight parameter then we find weakest knight in opponent
     * attack if our cool down is enough to attack,set cool down if attacks happened
     * if we can special attack put a random number if equals then use special skill
     */
    @Override
    public void act(Knight knight) {

            Knight knight2=Simulation.getInstance().getWeakestKnight(Simulation.getInstance().findopponentteamof(knight));
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
