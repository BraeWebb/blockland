/**
 * A stone block
 */
public class StoneBlock implements Block {

    /**
     * Get the type of a StoneBlock
     * Always returns "stone"
     *
     * @return "stone"
     */
    @Override
    public String getBlockType() {
        return "stone";
    }

    /**
     * Get the colour of a StoneBlock
     * Always returns "gray"
     *
     * @return "gray"
     */
    @Override
    public String getColour() {
        return "gray";
    }

    /**
     * StoneBlocks are not carryable
     * Always returns false
     *
     * @return false
     */
    @Override
    public boolean isCarryable() {
        return false;
    }

    /**
     * StoneBlocks are not diggable
     * Always returns false
     *
     * @return false
     */
    @Override
    public boolean isDiggable() {
        return false;
    }

    /**
     * StoneBlocks are not moveable
     * Always returns false
     *
     * @return false
     */
    @Override
    public boolean isMoveable() {
        return false;
    }
}
