import java.awt.*;
public abstract class KnightDecorator extends Knight
{
    protected Knight decoratedKnight;

    /**
     * Base decorator class
     */
    public KnightDecorator(Knight decoratedKnight){
        this.decoratedKnight=decoratedKnight;
        this.position=decoratedKnight.position;
        this.score=decoratedKnight.score;
        this.shape=decoratedKnight.shape;
        this.health=decoratedKnight.health;
        this.speed=decoratedKnight.speed;
        this.totaldamage=decoratedKnight.totaldamage;
        this.killcount=decoratedKnight.killcount;
        this.strategy=decoratedKnight.strategy;
        this.canattack=decoratedKnight.canattack;
        this.canspecialattack=decoratedKnight.canspecialattack;
        this.normalattackhappened=decoratedKnight.normalattackhappened;
        this.specialattackhappened=decoratedKnight.specialattackhappened;
    }

    public void decorate(Graphics g,Color c){
        decoratedKnight.decorate(g,c);
    }

    /**
     *  decorate normal attacks if happened
     */
    @Override
    public void normalAttack(Knight knight){
        decoratedKnight.normalAttack(knight);
        if(decoratedKnight.isNormalattackhappened()){
            Graphics g=Simulation.getInstance().getGraphics();
            g.setColor(new Color(255,0,255,150));
            g.fillOval(decoratedKnight.getPosition().getIntX(),decoratedKnight.getPosition().getIntY(),35,35);
            decoratedKnight.normalattackhappened=false;
        }

    }


    /**
     * Decorate special attacks if happened
     */
    @Override
    public void specialSkill(Knight knight){
        decoratedKnight.specialSkill(knight);
        if(decoratedKnight.isSpecialattackhappened()){
            Graphics g=Simulation.getInstance().getGraphics();
            g.setColor(new Color(0,255,255,150));
            g.fillOval(decoratedKnight.getPosition().getIntX()-10,decoratedKnight.getPosition().getIntY()-10,55,55);
            decoratedKnight.specialattackhappened=false;
        }
    }

    @Override
    public int getTotaldamage(){
        return decoratedKnight.getTotaldamage();
    }
    @Override
    public int getKillcount(){
        return decoratedKnight.getKillcount();
    }
    @Override
    public int getScore(){
        return decoratedKnight.getScore();
    }
    @Override
    public boolean isCanattack(){
        return decoratedKnight.isCanattack();
    }
    @Override
    public void setCanattack(boolean canattack) {
        decoratedKnight.setCanattack(canattack);
    }

    @Override
    public boolean isCanspecialattack() {
        return decoratedKnight.isCanspecialattack();
    }

    @Override
    public void setCanspecialattack(boolean canspecialattack) {
        decoratedKnight.setCanspecialattack(canspecialattack);
    }



}
