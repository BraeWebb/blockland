/**
 * A grass block
 */
public class GrassBlock extends GroundBlock {

    /**
     * Get the type of a GrassBlock
     * Always returns "grass"
     *
     * @return "grass"
     */
    @Override
    public String getBlockType() {
        return null;
    }

    /**
     * Get the colour of a GrassBlock
     * Always returns "green"
     *
     * @return "green"
     */
    @Override
    public String getColour() {
        return null;
    }

    /**
     * GrassBlocks are not carryable
     * Always returns false
     *
     * @return false
     */
    @Override
    public boolean isCarryable() {
        return false;
    }
}
