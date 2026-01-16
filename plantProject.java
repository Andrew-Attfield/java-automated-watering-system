import edu.princeton.cs.introcs.StdDraw;
import org.firmata4j.I2CDevice;
import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;
import java.util.ArrayList;

public class plantProject {

    public static void main(String[] args) throws IOException, InterruptedException {

        String myPort = "COM3"; //assign COM3 port to string myPort

        IODevice myGrove = new FirmataDevice(myPort); //create IODevice object myGrove

        //////////////////////////////////////////////////////////////////////////////

        try {

            myGrove.start(); //turn grove on
            System.out.println("Board Started"); //notify user grove is turned on
            myGrove.ensureInitializationIsDone(); //initialize board

        } catch (Exception ex) { //catch errors
            System.out.println("Couldn't connect to board.");

        }

        //////////////////////////////////////////////////////////////////////////////

        I2CDevice i2cObject = myGrove.getI2CDevice((byte) 0x3C);
        SSD1306 oledScreen = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);

        /////////////////////////////////////////////////////////////

        int dryPlant = 750;
        int moistPlant = 650;
        int wetPlant = 610;

        /////////////////////////////////////////////////////////////

        oledScreen.init();

        oledScreen.getCanvas().drawString(0, 5, "SOIL STATE: INITIALIZING");

        oledScreen.getCanvas().drawString(0, 20, "PUMP STATE:INITIALIZING");

        oledScreen.display();

        ////////////////////////////////////////////////////////////////

        Pin waterPump = myGrove.getPin(Pins.D2);
        waterPump.setMode(Pin.Mode.OUTPUT);

        Pin moistureSensor = myGrove.getPin(Pins.A1);
        moistureSensor.setMode(Pin.Mode.ANALOG);

        var killSwitch = myGrove.getPin(Pins.D6);
        killSwitch.setMode(Pin.Mode.INPUT);

        ////////////////////////////////////////////////////////////////

        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 800);

        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLUE);

        StdDraw.line(0, 0, 0, 750);
        StdDraw.line(0, 0, 100, 0);

        StdDraw.text(50, -25, "TIME");
        StdDraw.text(5, 775, "MOISTURE");
        StdDraw.text(50, 800, "MOISTURE LEVEL OVER TIME");

        ////////////////////////////////////////////////////////////////

        ArrayList<Integer> moistureData = new ArrayList<>();

        ////////////////////////////////////////////////////////////////

        myGrove.addEventListener(new buttonListener(killSwitch, waterPump, myGrove, oledScreen));

        ////////////////////////////////////////////////////////////////

        while (true) {

            try {

                int moistureVoltage = (int) moistureSensor.getValue();

                moistureData.add(moistureVoltage); //store moisture data as an arrayList

                System.out.println("Moisture Voltage:" + moistureData);

                for (int i = 0; i < moistureData.size(); i++) {
                    StdDraw.point(i, moistureData.get(i));
                }

                if (moistureVoltage < dryPlant && moistureVoltage > moistPlant) {

                    wateringSystem.wateringMode(waterPump);
                    System.out.println("Plant is Dry. Watering Plant.");

                    oledScreen.clear();

                    oledScreen.getCanvas().drawString(0, 5, "SOIL STATE: DRY");

                    oledScreen.getCanvas().drawString(0, 20, "PUMP STATE: ON");

                    oledScreen.display();


                } else if (moistureVoltage < moistPlant && moistureVoltage > wetPlant) {

                    wateringSystem.wateringModeSlow(waterPump);
                    System.out.println("Plant is moist. Watering Plant Slowly.");

                    oledScreen.clear();

                    oledScreen.getCanvas().drawString(0, 5, "SOIL STATE: MOIST");

                    oledScreen.getCanvas().drawString(0, 20, "PUMP STATE: SLOW");

                    oledScreen.display();


                } else if (moistureVoltage < wetPlant) {

                    wateringSystem.idleMode(waterPump);
                    System.out.println("Plant is wet. Watering Stopped.");

                    oledScreen.clear();

                    oledScreen.getCanvas().drawString(0, 5, "SOIL STATE: WET");

                    oledScreen.getCanvas().drawString(0, 20, "PUMP STATE: OFF");

                    oledScreen.display();

                }

                Thread.sleep(1000);

            } catch (Exception ex) {

                System.out.println("Board Stopped");

                break;
            }
        }
    }

    public static int mTest() throws IOException { //unitTest

        String myPort = "COM3"; //assign COM3 port to string myPort

        IODevice myGrove = new FirmataDevice(myPort);

        try {

            myGrove.start(); //turn grove on
            System.out.println("Board Started"); //notify user grove is turned on
            myGrove.ensureInitializationIsDone(); //initialize board

        } catch (Exception ex) { //catch errors
            System.out.println("Couldn't connect to board.");

        }

        Pin moistureSensor = myGrove.getPin(Pins.A1);
        moistureSensor.setMode(Pin.Mode.ANALOG);

        int moistureVoltage = (int) moistureSensor.getValue();
        return moistureVoltage;

    }
}


