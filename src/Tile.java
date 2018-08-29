import java.util.List;
import java.util.Map;

public class Tile {

    public Tile() {

    }

    public Tile(List<Block> startingBlocks) {

    }

    public Map<String, Tile> getExists() {
        return null;
    }

    public List<Block> getBlocks() {
        return null;
    }

    public Block getTopBlock() throws TooLowException {
        return null;
    }

    public void removeTopBlock() throws TooLowException {

    }

    public void addExit(String name, Tile target) throws NoExitException {

    }

    public void removeExit(String name) throws NoExitException {

    }

    public Block dig() throws TooLowException, InvalidBlockException {
        return null;
    }

    public void moveBlock(String exitName)
            throws TooHighException, InvalidBlockException, NoExitException {

    }

    public void placeBlock(Block block)
            throws TooHighException, InvalidBlockException {

    }
}
