import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for the Builder class
 */
public class BuilderTest {

    private Builder bob;
    private Builder steve;
    private Builder sally;
    private Builder rebecca;

    private Tile startTile;
    private Tile[] tiles;

    private List<Block> inventory;

    @Before
    public void setUp() {
        startTile = new Tile();
        List<Block> tallTile = new ArrayList<>();
        tallTile.add(new StoneBlock());
        tallTile.add(new StoneBlock());
        tallTile.add(new StoneBlock());
        tallTile.add(new StoneBlock());
        tallTile.add(new StoneBlock());
        tallTile.add(new StoneBlock());
        tallTile.add(new StoneBlock());
        tallTile.add(new StoneBlock());

        try {
            tiles = new Tile[]{new Tile(), new Tile(), new Tile(tallTile)};
        } catch (TooHighException e) {}

        bob = new Builder("Bob", startTile);
        steve = new Builder("Steve", tiles[2]);
        sally = new Builder("Sally", tiles[0]);

        inventory = new ArrayList<>();
        inventory.add(new SoilBlock());
        inventory.add(new WoodBlock());
        inventory.add(new SoilBlock());
        inventory.add(new WoodBlock());

        try {
            rebecca = new Builder("Rebecca", startTile, inventory);
        } catch (InvalidBlockException invalidBlock) {
            Assert.fail();
        }
    }

    @Test
    public void testConstructor() {
        try {
            Builder rebecca = new Builder("Rebecca", startTile, inventory);
            Assert.assertEquals(inventory, rebecca.getInventory());
        } catch (InvalidBlockException invalidBlock) {
            Assert.fail();
        }

        inventory.add(new GrassBlock());
        try {
            Builder emma = new Builder("Emma", startTile, inventory);
            Assert.fail();
        } catch (InvalidBlockException invalidBlock) {}
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Bob", bob.getName());
        Assert.assertEquals("Steve", steve.getName());
        Assert.assertEquals("Sally", sally.getName());
    }

    @Test
    public void testGetCurrentTile() {
        Assert.assertEquals(startTile, bob.getCurrentTile());
        Assert.assertEquals(tiles[2], steve.getCurrentTile());
        Assert.assertEquals(tiles[0], sally.getCurrentTile());
    }

    @Test
    public void testGetInventory() {
        Assert.assertEquals(0, bob.getInventory().size());

        // starting inventory tested in constructor
    }

    @Test
    public void testDropFromInventory() {
        Assert.assertEquals(3, startTile.getBlocks().size());

        try {
            bob.dropFromInventory(3);
            Assert.fail();
        } catch (InvalidBlockException e) {

        } catch (TooHighException e) {
            Assert.fail();
        }

        try {
            bob.dropFromInventory(-1);
            Assert.fail();
        } catch (InvalidBlockException e) {

        } catch (TooHighException e) {
            Assert.fail();
        }

        Assert.assertEquals(3, startTile.getBlocks().size());
        Block blockToRemove = bob.getInventory().get(1);

        try {
            bob.dropFromInventory(1);
        } catch (InvalidBlockException|TooHighException e) {
            Assert.fail();
        }

        Assert.assertEquals(2, startTile.getBlocks().size());
        Assert.assertNotEquals(blockToRemove, bob.getInventory().get(1));

        try {
            bob.dropFromInventory(1);
            Assert.fail();
        } catch (InvalidBlockException e) {
            Assert.fail();
        } catch (TooHighException e) {

        }

        Assert.assertEquals(2, startTile.getBlocks().size());
        Assert.assertNotEquals(blockToRemove, bob.getInventory().get(1));

        try {
            steve.dropFromInventory(1);
            Assert.fail();
        } catch (InvalidBlockException e) {
            Assert.fail();
        } catch (TooHighException e) {

        }
    }
}
