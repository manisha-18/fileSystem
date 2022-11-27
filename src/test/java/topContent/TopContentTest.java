package topContent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TopContentTest {

    TopContentService topContentService;

    @Before
    public void init(){
        topContentService = new SimpleTopContentManager();
    }
    @Test
    public void testIncrement(){
        Assert.assertEquals(topContentService.incrementPopularity(1, 1), 1);
        Assert.assertEquals(topContentService.incrementPopularity(2, 5), 2);
        Assert.assertEquals(topContentService.incrementPopularity(3, 1), 2);
        Assert.assertEquals(topContentService.incrementPopularity(4, 6), 4);
        Assert.assertEquals(topContentService.incrementPopularity(2, 4), 2);
    }

    @Test
    public void testDecrement(){
        Assert.assertEquals(topContentService.incrementPopularity(1, 1), 1);
        Assert.assertEquals(topContentService.incrementPopularity(2, 5), 2);
        Assert.assertEquals(topContentService.incrementPopularity(3, 1), 2);
        Assert.assertEquals(topContentService.incrementPopularity(4, 6), 4);
        Assert.assertEquals(topContentService.incrementPopularity(2, 4), 2);

        Assert.assertEquals(topContentService.decrementPopularity(1), 2);
        Assert.assertEquals(topContentService.decrementPopularity(4), 2);
        Assert.assertEquals(topContentService.decrementPopularity(4), 2);

    }

}
