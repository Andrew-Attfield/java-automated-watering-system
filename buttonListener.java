import org.firmata4j.IODevice;
import org.firmata4j.IODeviceEventListener;
import org.firmata4j.IOEvent;
import org.firmata4j.Pin;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;

public class buttonListener implements IODeviceEventListener {
    private final Pin buttonPin;
    private final Pin waterPump;
    private final IODevice myGrove;
    private final SSD1306 oledScreen;

    buttonListener(Pin buttonPin, Pin waterPump, IODevice myGrove, SSD1306 oledScreen) {

        this.buttonPin = buttonPin;
        this.waterPump = waterPump;
        this.myGrove = myGrove;
        this.oledScreen = oledScreen;

    }

    @Override

    public void onPinChange(IOEvent event) {

        if (event.getPin().getIndex() != buttonPin.getIndex()) {
            return;
        }

        try {

            waterPump.setValue(0);

            oledScreen.clear();

            myGrove.stop();

            System.out.println("Manual Override");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onStart(IOEvent event) {
    }

    @Override
    public void onStop(IOEvent event) {
    }

    @Override
    public void onMessageReceive(IOEvent event, String message) {
    }
}
