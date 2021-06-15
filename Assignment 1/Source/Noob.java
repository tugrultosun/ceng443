import java.awt.*;

public class Noob extends KnightDecorator{
    //TODO
    public Noob(Knight decoratedKnight){
        super(decoratedKnight);
    }

    /**
     * Draw shape and life of Knight
     */
    @Override
    public void decorate(Graphics g,Color c){
        decoratedKnight.decorate(g,c);
        g.setColor(c);
        g.fillPolygon(decoratedKnight.shape);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(decoratedKnight.getHealth()),this.position.getIntX()-10,this.position.getIntY());
    }


}
