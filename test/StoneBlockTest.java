import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StoneBlockTest {

    private StoneBlock block;

    @Before
    public void setUp() {
        block = new StoneBlock();
    }

    @Test
    public void testGetBlockType() {
        Assert.assertEquals("stone", block.getBlockType());
    }

    @Test
    public void testGetBlockColour() {
        Assert.assertEquals("gray", block.getColour());
    }

    @Test
    public void testIsDiggable() {
        Assert.assertFalse(block.isDiggable());
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
