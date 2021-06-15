/**
 * Chair class which represents the objects that students will
 * sit on during game.
 */
public class Chair {
    private int id;
    private boolean available;

    public Chair(int id) {
        this.id = id;
        this.available = true;
    }

    /**
     * @return id of chair
     */
    public int getID() {
        return id;
    }

    /**
     * @return true if chair is available false otherwise
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available bool value to set availability of chair
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
}


