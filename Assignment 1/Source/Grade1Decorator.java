import java.awt.*;

public class Grade1Decorator extends KnightDecorator
{
    public Grade1Decorator(Knight decoratedKnight){
        super(decoratedKnight);
    }

    /**
     * draws cyan shape over knight
     */
    @Override
    public void decorate(Graphics g,Color c){
        decoratedKnight.decorate(g,c);
        g.setColor(Color.CYAN);
        g.fillOval(this.position.getIntX()+10,this.position.getIntY()-25,10,10);
    }

}
