package ro.nila.catalyst.models;

public class CalculatedRoute extends CompareLocations{

    private double currentTime;

    public CalculatedRoute(double currentTime) {
        this.currentTime = currentTime;
    }

    public CalculatedRoute(Location locationA, Location locationB, double currentTime) {
        super(locationA, locationB);
        this.currentTime = currentTime;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "CalculatedRoute{" +
                "currentTime=" + currentTime +
                '}';
    }
}
