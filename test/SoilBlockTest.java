import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SoilBlockTest {

    private SoilBlock block;

    @Before
    public void setUp() {
        block = new SoilBlock();
    }

    @Test
    public void testGetBlockType() {
        Assert.assertEquals("soil", block.getBlockType());
    }

    @Test
    public void testGetBlockColour() {
        Assert.assertEquals("black", block.getColour());
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
        Assert.assertTrue(block.isCarryable());
    }
}
