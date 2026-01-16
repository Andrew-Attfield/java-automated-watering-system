import org.firmata4j.Pin;

import java.io.IOException;


public class wateringSystem {

    public wateringSystem() {

    }

    public static void wateringMode(Pin waterPump) throws IOException, InterruptedException {

        waterPump.setValue(1);

        Thread.sleep(250);

        waterPump.setValue(0);

    }

    public static void wateringModeSlow(Pin waterPump) throws IOException, InterruptedException {

        waterPump.setValue(1);

        Thread.sleep(200);

        waterPump.setValue(0);

    }

    public static void idleMode(Pin waterPump) throws IOException {

        waterPump.setValue(0);

    }
}
