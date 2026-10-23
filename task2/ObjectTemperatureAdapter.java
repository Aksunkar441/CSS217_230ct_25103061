public class ObjectTemperatureAdapter implements MetricTemperatureSensor {

    private final FahrenheitSensor sensor;

    public ObjectTemperatureAdapter() {
        this.sensor = new FahrenheitSensor();
    }

    @Override
    public double getTemperatureCelsius() {
        return (sensor.readFahrenheit() - 32) * 5.0 / 9.0;
    }
}