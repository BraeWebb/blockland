/**
 * A wooden block or crate
 */
public class WoodBlock implements Block {

    /**
     * Get the type of a WoodBlock
     * Always returns "wood"
     *
     * @return "wood"
     */
    @Override
    public String getBlockType() {
        return null;
    }

    /**
     * Get the colour of a WoodBlock
     * Always returns "brown"
     *
     * @return "brown"
     */
    @Override
    public String getColour() {
        return null;
    }

    /**
     * A woodblock is carryable
     * Always returns true
     *
     * @return true
     */
    @Override
    public boolean isCarryable() {
        return false;
    }

    /**
     * A woodblock is diggable
     * Always returns true
     *
     * @return true
     */
    @Override
    public boolean isDiggable() {
        return false;
    }

    /**
     * A woodblock is moveable
     * Always returns true
     *
     * @return true
     */
    @Override
    public boolean isMoveable() {
        return false;
    }
}
