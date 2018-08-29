import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TileTest {

    private Tile tile;
    private Tile tallTile;
    private Tile emptyTile;
    private List<Block> startingBlocks;

    @Before
    public void setUp() {
        tile = new Tile();

        startingBlocks = new ArrayList<>();
        startingBlocks.add(new SoilBlock());
        startingBlocks.add(new SoilBlock());
        startingBlocks.add(new GrassBlock());
        startingBlocks.add(new StoneBlock());
        startingBlocks.add(new WoodBlock());
        startingBlocks.add(new WoodBlock());
        startingBlocks.add(new WoodBlock());

        try {
            tallTile = new Tile(startingBlocks);
            emptyTile = new Tile(new ArrayList<>());
        } catch (TooHighException e) {
            Assert.fail();
        }
    }

    @Test
    public void testConstructor() {
        Tile tile = new Tile();
        Block[] blocks = new Block[] {new SoilBlock(), new SoilBlock(),
                new GrassBlock()};

        Assert.assertEquals(3, tile.getBlocks().size());
        for (int i = 0; i < blocks.length; i++) {
            Assert.assertEquals(blocks[i].getBlockType(),
                    tile.getBlocks().get(i).getBlockType());
        }

        try {
            new Tile(startingBlocks);
        } catch (TooHighException e) {
            Assert.fail();
        }

        startingBlocks.add(new WoodBlock());
        startingBlocks.add(new WoodBlock());

        try {
            new Tile(startingBlocks);
            Assert.fail();
        } catch (TooHighException e) {}

        startingBlocks = new ArrayList<>();
        startingBlocks.add(new SoilBlock());
        startingBlocks.add(new SoilBlock());
        startingBlocks.add(new GrassBlock());

        try {
            new Tile(startingBlocks);
        } catch (TooHighException e) {
            Assert.fail();
        }

        startingBlocks.add(new GrassBlock());

        try {
            new Tile(startingBlocks);
            Assert.fail();
        } catch (TooHighException e) {}
    }

    @Test
    public void testExits() {
        Assert.assertEquals(0, tile.getExists().size());

        Tile leftTile = new Tile();

        try {
            tile.addExit("left", leftTile);
        } catch (NoExitException e) {
            Assert.fail();
        }

        Assert.assertEquals(1, tile.getExists().size());
        Assert.assertEquals(leftTile, tile.getExists().get("left"));

        try {
            tile.addExit(null, leftTile);
            Assert.fail();
        } catch (NoExitException e) {}

        try {
            tile.addExit("left", null);
            Assert.fail();
        } catch (NoExitException e) {}
    }

    @Test
    public void testGetTopBlock() {
        try {
            Assert.assertEquals("grass", tile.getTopBlock().getBlockType());
            Assert.assertEquals("wood", tallTile.getTopBlock().getBlockType());

            Block expectedBlock = startingBlocks.get(startingBlocks.size() - 1);
            Assert.assertEquals(expectedBlock, tallTile.getTopBlock());
        } catch (TooLowException e) {
            Assert.fail();
        }

        try {
            emptyTile.getTopBlock();
            Assert.fail();
        } catch (TooLowException e) {}
    }

    @Test
    public void testRemoveTopBlock() {
        Block topBlock = startingBlocks.get(startingBlocks.size() - 1);
        Block secondBlock = startingBlocks.get(startingBlocks.size() - 2);
        try {
            Assert.assertEquals(7, tallTile.getBlocks().size());
            Assert.assertEquals(topBlock, tallTile.getTopBlock());

            tallTile.removeTopBlock();

            Assert.assertEquals(6, tallTile.getBlocks().size());
            Assert.assertEquals(secondBlock, tallTile.getTopBlock());
        } catch (TooLowException e) {
            Assert.fail();
        }

        try {
            emptyTile.removeTopBlock();
            Assert.fail();
        } catch (TooLowException e) {}
    }

    @Test
    public void testRemoveExit() {
        Tile leftTile = new Tile();
        try {
            tile.addExit("left", leftTile);
        } catch (NoExitException e) {
            Assert.fail();
        }

        Assert.assertEquals(1, tile.getExists().size());
        Assert.assertEquals(leftTile, tile.getExists().get("left"));

        try {
            tile.removeExit("right");
            Assert.fail();
        } catch (NoExitException e) {}

        try {
            tile.removeExit(null);
            Assert.fail();
        } catch (NoExitException e) {}

        try {
            tile.removeExit("left");
        } catch (NoExitException e) {
            Assert.fail();
        }

        Assert.assertEquals(0, tile.getExists().size());
    }

    @Test
    public void testDig() {
        try {
            emptyTile.dig();
            Assert.fail();
        } catch (TooLowException e) {

        } catch (InvalidBlockException e) {
            Assert.fail();
        }

        try {
            Block expectedBlock = tallTile.getTopBlock();
            Block topBlock = tallTile.dig();

            Assert.assertEquals(expectedBlock, topBlock);
        } catch (TooLowException|InvalidBlockException e) {
            Assert.fail();
        }

        startingBlocks = new ArrayList<>();
        startingBlocks.add(new SoilBlock());
        startingBlocks.add(new SoilBlock());
        startingBlocks.add(new StoneBlock());
        Tile noDigTile;
        try {
            noDigTile = new Tile(startingBlocks);
        } catch (TooHighException e) {
            Assert.fail();
            return;
        }

        try {
            noDigTile.dig();
            Assert.fail();
        } catch (TooLowException e) {
            Assert.fail();
        } catch (InvalidBlockException e) {

        }
    }

    @Test
    public void testMoveBlock() {
        // create exits for testing
        try {
            tile.addExit("tall", tallTile);
            tile.addExit("empty", emptyTile);
            tallTile.addExit("tall", tile);
            emptyTile.addExit("tile", tile);
        } catch (NoExitException e) {
            Assert.fail();
        }

        // exit name that doesn't exist causes
        try {
            tile.moveBlock("right");
            Assert.fail();
        } catch (TooHighException|InvalidBlockException e) {
            Assert.fail();
        } catch (NoExitException e) {}

        // null exist name causes NoExitException
        try {
            tile.moveBlock(null);
            Assert.fail();
        } catch (TooHighException|InvalidBlockException e) {
            Assert.fail();
        } catch (NoExitException e) {}

        // moving to a taller block causes TooHighException
        try {
            tile.placeBlock(new WoodBlock());
            tile.placeBlock(new WoodBlock());
            tile.moveBlock("tall");
            tile.moveBlock("tall");
            Assert.fail();
        } catch (NoExitException|InvalidBlockException e) {
            Assert.fail();
        } catch (TooHighException e) {}

        // trying to move a grass block causes an InvalidBlockException
        try {
            emptyTile.placeBlock(new GrassBlock());
            emptyTile.moveBlock("tile");
            Assert.fail();
        } catch (NoExitException|TooHighException e) {
            Assert.fail();
        } catch (InvalidBlockException e) {}


        // successfully move a tile from one block to another
        try {
            Assert.assertEquals(8, tallTile.getBlocks().size());
            Assert.assertEquals(3, tile.getBlocks().size());
            tallTile.moveBlock("tall");
            Assert.assertEquals(7, tallTile.getBlocks().size());
            Assert.assertEquals(4, tile.getBlocks().size());
        } catch (NoExitException|TooHighException|InvalidBlockException e) {
            Assert.fail();
        }
    }

    @Test
    public void testPlaceBlock() {
        Block newBlock = new WoodBlock();
        Block topBlock = startingBlocks.get(startingBlocks.size() - 1);

        try {
            Assert.assertEquals(7, tallTile.getBlocks().size());
            Assert.assertEquals(topBlock, tallTile.getTopBlock());

            tallTile.placeBlock(newBlock);

            Assert.assertEquals(8, tallTile.getBlocks().size());
            Assert.assertEquals(newBlock, tallTile.getTopBlock());
        } catch (TooHighException|TooLowException|InvalidBlockException e) {
            Assert.fail();
        }

        try {
            tile.placeBlock(null);
            Assert.fail();
        } catch (TooHighException e) {
            Assert.fail();
        } catch (InvalidBlockException e) {}

        try {
            tallTile.placeBlock(newBlock);
            Assert.fail();
        } catch (TooHighException e) {
        } catch (InvalidBlockException e) {
            Assert.fail();
        }

        try {
            tile.placeBlock(new GrassBlock());
            Assert.fail();
        } catch (TooHighException e) {
        } catch (InvalidBlockException e) {
            Assert.fail();
        }
    }
}
