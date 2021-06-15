import java.awt.*;

public class Grade3Decorator extends KnightDecorator
{
    public Grade3Decorator(Knight decoratedKnight){
        super(decoratedKnight);
    }

    /**
     * Draws green shape over knight
     */
    @Override
    public void decorate(Graphics g,Color c){
        decoratedKnight.decorate(g,c);
        g.setColor(Color.GREEN);
        g.fillOval(this.position.getIntX()-10,this.position.getIntY()-25,10,10);
    }

}
