import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tiles for a map.
 * Contains Blocks
 * Maintains a mapping between exit names and other tiles.
 */
public class Tile {

    private List<Block> blocks;
    private Map<String, Tile> exits;

    /**
     * Construct a new tile.
     * Each tile should be constructed with no exits (getExits().size() == 0).
     * Each tile must be constructed to start with two soil blocks and then a grass block on top.
     * i.e. getBlocks() must contain {SoilBlock, SoilBlock, GrassBlock} for a new Tile.
     */
    public Tile() {
        blocks = new ArrayList<>();
        blocks.add(new SoilBlock());
        blocks.add(new SoilBlock());
        blocks.add(new GrassBlock());

        exits = new HashMap<>();
    }

    /**
     * Construct a new tile.
     * Each tile should be constructed with no exits (getExits().size() == 0).
     * Set the blocks on the tile to be the contents of startingBlocks.
     * Index 0 in startingBlocks is the lowest block on the tile, while index N -1 is the top block on the tile for N blocks.
     * startingBlocks cannot be null.
     * i.e. getBlocks() must contain the contents of startingBlocks, but modifying startingBlocks after constructing the Tile should not change the results of getBlocks().
     * Handle the following cases:
     *    1. If startingBlocks contains more than 8 elements, throw a TooHighException.
     *    2. If startingBlocks contains an instance of GroundBlock that is at an index of 3 or higher, throw a TooHighException.
     *
     * @param startingBlocks a list of blocks on the tile, cannot be null
     *
     * @throws TooHighException if startingBlocks.size() > 8, or if startingBlocks elements with index ≥ 3 are instances of GroundBlock
     */
    public Tile(List<Block> startingBlocks) throws TooHighException {
        if (startingBlocks.size() > 8) {
            throw new TooHighException();
        }

        for (int i = 3; i < startingBlocks.size(); i++) {
            if (startingBlocks.get(i) instanceof GroundBlock) {
                throw new TooHighException();
            }
        }

        blocks = new ArrayList<>(startingBlocks);
        exits = new HashMap<>();
    }

    /**
     * What exits are there from this Tile?
     * No ordering is required.
     *
     * @return map of names to Tiles
     */
    public Map<String, Tile> getExists() {
        return exits;
    }

    /**
     * What Blocks are on this Tile?
     * Order of blocks returned must be in order of height.
     * Index 0 is bottom, and index N - 1 is the top, for N blocks.
     *
     * @return Blocks on the Tile
     */
    public List<Block> getBlocks() {
        return blocks;
    }

    /**
     * Return the block that is the top block on the tile.
     * If there are no blocks, throw a TooLowException
     *
     * @return the top Block
     *
     * @throws TooLowException if there are no blocks on the tile
     */
    public Block getTopBlock() throws TooLowException {
        if (blocks.size() == 0) {
            throw new TooLowException();
        }
        return blocks.get(blocks.size() - 1);
    }

    /**
     * Remove the block on top of the tile
     * Throw a TooLowException if there are no blocks on the tile
     *
     * @throws TooLowException if there are no blocks on the tile
     */
    public void removeTopBlock() throws TooLowException {
        if (blocks.size() == 0) {
            throw new TooLowException();
        }
        blocks.remove(blocks.size() - 1);
    }

    /**
     * Add a new exit to this tile
     * The Map returned by getExits() must now include an entry (name, target). Overwrites any existing exit with the same name
     * If name or target is null, throw a NoExitException
     *
     * @param name Name of the exit
     * @param target Tile the exit goes to
     *
     * @throws NoExitException if name or target is null
     */
    public void addExit(String name, Tile target) throws NoExitException {
        if (name == null || target == null) {
            throw new NoExitException();
        }
        this.exits.put(name, target);
    }

    /**
     * Remove an exit from this tile
     * The Map returned by getExits() must no longer have the key name.
     * If name does not exist in getExits(), or name is null, throw a NoExitException.
     *
     * @param name Name of exit to remove
     * @throws NoExitException if name is not in exits, or name is null
     */
    public void removeExit(String name) throws NoExitException {

    }

    /**
     * Attempt to dig in the current tile.
     * If the top block (given by getTopBlock()) is diggable (block.isDiggable()), remove the top block of the tile and return it.
     * Handle the following cases:
     *    1. Throw a TooLowException if there are no blocks on the tile
     *    2. Throw an InvalidBlockException if the block is not diggable
     *
     * @return the removed block
     *
     * @throws TooLowException if there are no blocks on the tile
     * @throws InvalidBlockException if the block is not diggable
     */
    public Block dig() throws TooLowException, InvalidBlockException {
        return null;
    }

    /**
     * Attempt to move the current top block to another tile. Remove the top block (given by getTopBlock()) from this tile and add it to the tile at the named exit (exitName in getExits()), if the block is moveable (block.isMoveable()) and the height of that tile (the number of blocks given by getBlocks().size()) is less than the current tile *before* the move.
     * Handle the following cases:
     *      * If the exit is null, or does not exist, throw a NoExitException
     *      * If the number of blocks on the target tile is ≥ to this one, throw a TooHighException
     *      * If the block is not moveable, throw a InvalidBlockException
     *
     * @param exitName the name of the exit to move the block to
     *
     * @throws TooHighException if the target tile is ≥ to this one.
     * @throws InvalidBlockException if the block is not moveable
     * @throws NoExitException if the exit is null or does not exist
     */
    public void moveBlock(String exitName)
            throws TooHighException, InvalidBlockException, NoExitException {

    }

    /**
     * Place a block on a tile. Add the block to the top of the blocks on this tile. If the block is an instance of GroundBlock, it can only be placed underground. Handle the following cases:
     *      * If the block is null, throw an InvalidBlockException
     *      * If the target tile has 8 blocks already, or if the block is a GroundBlock and the target tile has 3 or more blocks already, throw a TooHighException
     *
     * @param block the block to place.
     *
     * @throws TooHighException if there are already 8 blocks on the tile, or if this is a ground block and there are already 3 or more blocks on the tile.
     * @throws InvalidBlockException if the block is null
     */
    public void placeBlock(Block block)
            throws TooHighException, InvalidBlockException {

    }
}
