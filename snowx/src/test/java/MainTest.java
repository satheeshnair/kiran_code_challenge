import com.codechallenge.snowx.SnowX;
import com.codechallenge.snowx.Target;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MainTest {

    /*
    * custom inputs
    * arg[0] : test data
    * arg[1] : HPtorpedo
    *
    * */
    private static String testDataPath = "src/test/resources/TestDataTesting.snw";
    private static String testTorpedoPath = "src/test/resources/TorpedoTesting.snw";


    @Test
    public void testSnowX() {
        String[] args = new String[] {testDataPath, testTorpedoPath};
        SnowX.main(args);

        List<Target> targetList = SnowX.getTargetList();

        Assert.assertEquals(4, targetList.size());

        for (Target t: targetList) {
            Assert.assertEquals("HPtorpedo", t.getType());
        }
    }
}
