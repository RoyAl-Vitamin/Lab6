package vi.al.ro;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigTest {

    private Config config;

    @Before
    public void setUpTConfg() {
        config = Config.getInstance();
    }

    @Test
    public void testHostName() {
        assertEquals(config.getClientHost(), "127.0.0.1");
    }

    @Test
    public void testHostPort() {
        assertEquals(config.getServerPort(), 9090);
    }

    @After
    public void tearDownToHexStringData() {
        config = null;
    }
}
