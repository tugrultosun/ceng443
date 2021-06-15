public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public int getIntX() { return  x; }
    public int getIntY() { return  y; }

    public int distanceTo(int x, int y) {
        return (int)Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
}