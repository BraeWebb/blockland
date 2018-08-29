import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WoodBlockTest {

    private WoodBlock block;

    @Before
    public void setUp() {
        block = new WoodBlock();
    }

    @Test
    public void testGetBlockType() {
        Assert.assertEquals("wood", block.getBlockType());
    }

    @Test
    public void testGetBlockColour() {
        Assert.assertEquals("brown", block.getColour());
    }

    @Test
    public void testIsDiggable() {
        Assert.assertTrue(block.isDiggable());
    }

    @Test
    public void testIsMoveable() {
        Assert.assertTrue(block.isMoveable());
    }

    @Test
    public void testIsCarriable() {
        Assert.assertTrue(block.isCarryable());
    }
}
