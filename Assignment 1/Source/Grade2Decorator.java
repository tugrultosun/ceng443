import java.awt.*;

public class Grade2Decorator extends KnightDecorator {
    public Grade2Decorator(Knight decoratedKnight){
        super(decoratedKnight);
    }

    /**
     * Draws yellow shape over knight
     */
    @Override
    public void decorate(Graphics g,Color c){
        decoratedKnight.decorate(g,c);
        g.setColor(Color.YELLOW);
        g.fillOval(this.position.getIntX(),this.position.getIntY()-25,10,10);
    }

}