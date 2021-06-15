import java.awt.*;



public abstract class Knight
{

    protected int totaldamage;
    protected int killcount;
    protected Polygon shape;
    protected Position position;

    /**
     * these are cooldown variables
     */
    protected boolean canattack;
    protected boolean canspecialattack;
    /**
     * these are for decorator to be notified when attack happened
     */
    protected boolean normalattackhappened;
    protected boolean specialattackhappened;
    /**
     * holds score of knight
     */
    protected int score;
    protected int speed;
    /**
     * holds the hp of knight
     */
    protected int health;
    protected Strategy strategy;


    public Knight(){
        totaldamage=0;
        killcount=0;
        shape=new Polygon();
        position=new Position(0,0);
    }


    public abstract int getTotaldamage();
    public abstract int getKillcount();
    public abstract int getScore();

    public void setTotaldamage(int totaldamage) {
        this.totaldamage = totaldamage;
    }

    public abstract boolean isCanattack();
    public abstract void setCanattack(boolean canattack);


    public abstract boolean isCanspecialattack();
    public abstract void setCanspecialattack(boolean canspecialattack);

    public boolean isNormalattackhappened() {
        return normalattackhappened;
    }

    public boolean isSpecialattackhappened() {
        return specialattackhappened;
    }





    public void updatekillcount() {
        this.killcount++;
    }




    public void setScore(int score){
        this.score=score;
    }
    public  int getHealth(){
        return health;
    }


    public void setHealth(int health) {
        this.health = health;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void decorate(Graphics g,Color c){

    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(int x,int y) {
        this.position.setX(x);
        this.position.setY(y);
        this.translatepoly(x,y);
    }

    public void translatepoly(int x, int y){
        shape.translate(x,y);
    }

    public Polygon getShape(){
        return shape;
    }
    public void setShape(Polygon shape){
        this.shape=shape;
    }


    /**
     * knight will make a step which is it will apply its strategy
     */
    public void step(){
        this.getStrategy().act(this);
    }


    /**
     * returns speed of the knight
     * @return speed
     */
    public int getSpeed(){
        return speed;
    }

    /**
     * knight will move with this method to coordinate x,y
     * @param knight this is the knight which will move
     * @param x this is the x coordinate that knight wants to move
     * @param y this is the y coordinate that knight wants to move
     * if knight wants to move out of panels dont make action for that coordinate
     */
    public void trytomove(Knight knight,int x,int y){
        double direction=Math.atan2((y-knight.getPosition().getIntY()),(x-knight.getPosition().getIntX()));
        int deltax=(int)(knight.getSpeed()*Math.cos(direction))/20;
        int deltay=(int)(knight.getSpeed()*Math.sin(direction))/20;
        if(knight.getPosition().getIntX()+deltax>750 || knight.getPosition().getIntX()+deltax<0){
            //dont do anything because generated x is out of border
        }
        else {
            knight.getPosition().setX(knight.getPosition().getIntX()+deltax );
            knight.translatepoly( deltax,0);
        }
        if(knight.getPosition().getIntY()+deltay>550 || knight.getPosition().getIntY()+deltay<0){
            //dont do anything because generated y is out of border
        }
        else {
            knight.getPosition().setY(knight.getPosition().getIntY()+deltay);
            knight.translatepoly(0,deltay);
        }
    }

    /**
     * check if knight is dead
     * @return a boolean true if knight is dead false ow
     */
    public boolean isdead(){
        return getHealth()<=0;
    }


    public abstract void normalAttack(Knight knight);
    public abstract void specialSkill(Knight knight);

}
