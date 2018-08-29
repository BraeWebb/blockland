/**
 * A soil block
 */
public class SoilBlock extends GroundBlock {

    /**
     * Get the type of a SoilBlock
     * Always returns "soil"
     *
     * @return "soil"
     */
    @Override
    public String getBlockType() {
        return "soil";
    }

    /**
     * Get the colour of a SoilBlock
     * Always returns "black"
     *
     * @return "black"
     */
    @Override
    public String getColour() {
        return "black";
    }

    /**
     * SoilBlocks are carryable.
     * Always returns true
     *
     * @return true
     */
    @Override
    public boolean isCarryable() {
        return true;
    }
}
