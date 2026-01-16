import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class unitTest {

    @Test
    public void moistureTest() throws IOException {
        int expectedValue = 650;
        int actualValue = plantProject.mTest();

        assertEquals("Moisture sensor deviates from expected values.", expectedValue, actualValue, 150);
    }
}
