import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GrassBlockTest {

    private GrassBlock block;

    @Before
    public void setUp() {
        block = new GrassBlock();
    }

    @Test
    public void testGetBlockType() {
        Assert.assertEquals("grass", block.getBlockType());
    }

    @Test
    public void testGetBlockColour() {
        Assert.assertEquals("green", block.getColour());
    }

    @Test
    public void testIsDiggable() {
        Assert.assertTrue(block.isDiggable());
    }

    @Test
    public void testIsMoveable() {
        Assert.assertFalse(block.isMoveable());
    }

    @Test
    public void testIsCarriable() {
        Assert.assertFalse(block.isCarryable());
    }
}
