interface MetricTemperatureSensor {
    double getTemperatureCelsius();
}

class FahrenheitSensor {
    public double readFahrenheit() {
        return 98.6;
    }
}

public class ClassTemperatureAdapter extends FahrenheitSensor implements MetricTemperatureSensor {

    @Override
    public double getTemperatureCelsius() {
        return (readFahrenheit() - 32) * 5.0 / 9.0;
    }

    public static void main(String[] args) {
        String studentId = "25103061";
        System.out.println("Student ID: " + studentId);

        MetricTemperatureSensor classAdapter = new ClassTemperatureAdapter();
        MetricTemperatureSensor objectAdapter = new ObjectTemperatureAdapter();

        double classCelsius = classAdapter.getTemperatureCelsius();
        double objectCelsius = objectAdapter.getTemperatureCelsius();

        System.out.println("Class Adapter Celsius:  " + classCelsius);
        System.out.println("Object Adapter Celsius: " + objectCelsius);
        System.out.println("Are they identical? " + (classCelsius == objectCelsius));
    }
}