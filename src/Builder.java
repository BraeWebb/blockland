import java.util.ArrayList;
import java.util.List;

/**
 * A Player who modifies the map
 * Manages an inventory of Blocks
 * Maintains a position in the map (by maintaining the current tile that the Builder is on)
 */
public class Builder {

    private String name;
    private Tile currentTile;
    private List<Block> inventory;

    /**
     * Create a builder.
     * Set the name of the Builder (such that getName() == name) and the current tile to startingTile (such that getCurrentTile() == startingTile).
     *
     * @param name name of the builder (returned by getName())- cannot be null
     * @param startingTile the tile the builder starts in - cannot be null
     */
    public Builder(String name, Tile startingTile) {
        this.name = name;
        this.currentTile = startingTile;
        this.inventory = new ArrayList<>();
    }

    /**
     * Create a builder
     * Set the name of the Builder (such that getName() == name) and the current tile to startingTile (such that getCurrentTile() == startingTile).
     * Copy the starting inventory into the builder's inventory, such that the contents of getInventory() are identical to startingInventory.
     * i.e. getInventory() must contain the contents of startingInventory, but modifying startingInventory after the Builder is constructed should not change the result of getInventory().
     *
     * @param name name of the builder (returned by getName()) - cannot be null
     * @param startingTile the tile the builder starts in - cannot be null
     * @param startingInventory the starting inventory (blocks) - cannot be null
     *
     * @throws InvalidBlockException if for any Block (block) in startingInventory, block.isCarryable() == false
     */
    public Builder(String name, Tile startingTile, List<Block> startingInventory)
            throws InvalidBlockException {
        this.name = name;
        this.currentTile = startingTile;

        for (Block block : startingInventory) {
            if (!block.isCarryable()) {
                throw new InvalidBlockException();
            }
        }
        this.inventory = new ArrayList<>(startingInventory);
    }

    /**
     * Get the Builder's name
     *
     * @return the Builder's name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the current tile that the builder is on
     *
     * @return the current tile
     */
    public Tile getCurrentTile() {
        return currentTile;
    }

    /**
     * What is in the Builder's inventory
     *
     * @return blocks in the inventory
     */
    public List<Block> getInventory() {
        return inventory;
    }

    /**
     * Drop a block from inventory on the top of the current tile
     * The block at inventoryIndex should be removed from the Builder's inventory, and added to the Builder's current tile.
     * Blocks can only be dropped on tiles with less than 8 blocks, or tiles with less than 3 blocks if a GroundBlock.
     * Note: the current tile is that given by getCurrentTile() and the index should refer to an item in the list returned by getInventory()
     * Handle the following cases:
     *    1. If the inventoryIndex is < 0 or ≥ the inventory size, throw an InvalidBlockException.
     *    2. If there are 8 blocks on the current tile, throw a TooHighException.
     *    3. If there are 3 or more blocks on the current tile, and the inventory block is a GroundBlock, throw a TooHighException
     * Hint: call Tile.placeBlock, after checking the inventory
     *
     * @param inventoryIndex the index in the inventory to place
     *
     * @throws InvalidBlockException if the inventoryIndex is out of the inventory range
     * @throws TooHighException if there are 8 blocks on the current tile already, or if the block is an instance of GroundBlock and there are already 3 or more blocks on the current tile.
     */
    public void dropFromInventory(int inventoryIndex)
            throws InvalidBlockException, TooHighException {

    }

    /**
     * Attempt to dig in the current tile and add tile to the inventory
     * If the top block (given by getCurrentTile().getTopBlock()) is diggable, remove the top block of the tile and destroy it, or add it to the end of the inventory (given by getInventory()).
     * Handle the following cases:
     * If there are no blocks on the current tile, throw a TooLowException
     * If the top block is not diggable, throw a InvalidBlockException
     * If the top block is not carryable, remove the block, but do not add it to the inventory.
     * Hint: call Tile.dig()
     *
     * @throws TooLowException if there are no blocks on the current tile
     * @throws InvalidBlockException if the top block is not diggable
     */
    public void digOnCurrentTile()
            throws TooLowException, InvalidBlockException {

    }

    /**
     * Check if the Builder can enter a tile from the current tile.
     * Returns true if:
     * the tiles are connected via an exit (i.e. there is an exit from the current tile to the new tile), and
     * the height of the new tile (number of blocks) is the same or different by 1 from the current tile (i.e. abs(current tile height - new tile) <= 1)
     * If newTile is null return false.
     *
     * @param newTile the tile to test if we can enter
     * @return true if the tile can be entered
     */
    public boolean canEnter(Tile newTile) {
        return false;
    }

    /**
     * move the builder to a new tile.
     * If canEnter(newTile) == true then change the builders current tile to be newTile. (i.e. getCurrentTile() == newTile)
     * If canEnter(newTile) == false then throw a NoExitException.
     *
     * @param newTile the tile to move to
     *
     * @throws NoExitException if canEnter(newTile) == false
     */
    public void moveTo(Tile newTile) throws NoExitException {

    }
}
