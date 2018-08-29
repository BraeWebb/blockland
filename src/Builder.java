import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Builder {

    public Builder(String name, Tile startingTile) {

    }

    public Builder(String name, Tile startingTile, List<Block> startingInventory) {

    }

    public String getName() {
        return null;
    }

    public Tile getCurrentTile() {
        return null;
    }

    public List<Block> getInventory() {
        return null;
    }

    public void dropFromInventory(int inventoryIndex)
            throws InvalidBlockException, TooHighException {

    }

    public void digOnCurrentTile()
            throws TooLowException, InvalidBlockException {

    }

    public boolean canEnter(Tile newTile) {
        return false;
    }

    public void moveTo(Tile newTile) throws NoExitException {

    }
}
