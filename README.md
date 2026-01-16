Smart Plant Irrigation System (Java/IoT)

This project is an automated hardware-software solution designed to maintain soil moisture levels for indoor plants. Developed using IntelliJ IDEA and the Grove Arduino Beginner Kit, it demonstrates the application of Object-Oriented Design and Event-Driven Programming in a real-world IoT (Internet of Things) context.
🛠 Key Features

    State-Machine Controller: Implements a three-state logic (Dry, Moist, Wet) to determine pump intervals, preventing over-watering and ensuring plant survival.

    Real-time Data Visualization: Integrates the StdLib API to generate live moisture trend graphs, allowing for visual analysis of soil health over time.

    Asynchronous Safety Mechanism: Utilizes Java Listeners to monitor a physical hardware button, acting as an instant "Kill Switch" to disable the pump and board during malfunctions.

    Robust Data Collection: Moisture readings are processed and stored in a dynamic ArrayList, facilitating easy data manipulation and graphing.

📐 Object-Oriented Implementation

The system was designed with a focus on clean, modular code:

    Abstraction: The plantProject class hides complex low-level Firmata4j communications behind simple method calls.

    Inheritance: Specialized logic for the waterPump and buttonListener are separated into child classes to improve maintainability.

    Encapsulation: Hardware attributes and watering modes are bundled within specific classes to protect data integrity.

💻 Technical Stack

    Language: Java

    APIs: Firmata4j (Arduino communication), StdLib (Graphing)

    Hardware: Grove Arduino Kit, Moisture Sensor, MOSFET, Water Pump, 9V Battery

    IDE: IntelliJ IDEA

📂 Project Structure

    plantProject.java: The main driver class and system coordinator.

    wateringSystem.java: Contains the state-machine logic for the pump.

    buttonListener.java: Event handler for the emergency stop button.

    unitTest.java: Quality assurance class to verify sensor accuracy and deviation.


