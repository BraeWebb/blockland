/**
 * An abstract GroundBlock that enforces not moveable and diggable
 */
public abstract class GroundBlock implements Block {

    /**
     * Is the GroundBlock diggable? GroundBlocks enforce allowing digging
     *
     * @return true
     */
    @Override
    public boolean isDiggable() {
        return true;
    }

    /**
     * Is the GroundBlock moveable? GroundBlocks enforce not moving
     *
     * @return false
     */
    @Override
    public boolean isMoveable() {
        return false;
    }
}
