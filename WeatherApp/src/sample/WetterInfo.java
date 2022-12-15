package sample;

public class WetterInfo {
    private String timestamp;
    private String temperature;


    public WetterInfo(String timestamp, String temperature) {
        this.timestamp = timestamp;
        this.temperature = temperature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getTemperature() {
        return temperature;
    }

}