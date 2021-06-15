import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicKnight extends Knight
{
    protected int normal_damage;
    protected int normalAttackRange;
    protected int specialSkillRange;
    protected String knighttype;

    /**
     *creates execution service
     */
    protected ExecutorService executor;
    protected Runnable runnableTask;
    public BasicKnight(){
        super();

        normalattackhappened=false;
        specialattackhappened=false;
        canattack=false;
        canspecialattack=false;
        strategy=helper.nextStrategy();
        executor = Executors.newFixedThreadPool(1);
        this.score=0;
        Knight k=this;
        runnableTask=new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        canattack=true;
                        canspecialattack=true;
                        k.setStrategy(helper.nextStrategy());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executor.execute(runnableTask);
    }

    @Override
    public Strategy getStrategy(){
        return strategy;
    }

    public void normalAttack(Knight knight){

    }
    public void specialSkill(Knight knight){

    }


    @Override
    public int getTotaldamage(){
        return this.totaldamage;
    }

    @Override
    public boolean isCanattack() {
        return this.canattack;
    }
    @Override
    public void setCanattack(boolean canattack){
        this.canattack=canattack;
    }

    @Override
    public boolean isCanspecialattack() {
        return this.canspecialattack;
    }

    @Override
    public void setCanspecialattack(boolean canspecialattack) {
        this.canspecialattack=canspecialattack;
    }

    @Override
    public int getKillcount(){
        return this.killcount;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int getHealth() {
        return this.health;
    }
    @Override
    public void updatekillcount(){
        this.killcount++;
    }
}
