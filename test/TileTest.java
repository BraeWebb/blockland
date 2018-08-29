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
            Assert.assertEquals(8, tallTile.getBlocks().size());
            Assert.assertEquals(topBlock, tallTile.getTopBlock());

            tallTile.removeTopBlock();

            Assert.assertEquals(7, tallTile.getBlocks().size());
            Assert.assertEquals(secondBlock, tallTile.getTopBlock());
        } catch (TooLowException e) {
            Assert.fail();
        }

        try {
            emptyTile.removeTopBlock();
            Assert.fail();
        } catch (TooLowException e) {}
    }
}
