public abstract class GroundBlock implements Block {
    @Override
    public boolean isDiggable() {
        return false;
    }

    @Override
    public boolean isMoveable() {
        return false;
    }
}
