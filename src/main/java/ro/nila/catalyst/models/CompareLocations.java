package ro.nila.catalyst.models;

public class CompareLocations {
    private Location locationA;
    private Location locationB;

    public CompareLocations() {
    }

    public CompareLocations(Location locationA, Location locationB) {
        this.locationA = locationA;
        this.locationB = locationB;
    }

    public Location getLocationA() {
        return locationA;
    }

    public void setLocationA(Location locationA) {
        this.locationA = locationA;
    }

    public Location getLocationB() {
        return locationB;
    }

    public void setLocationB(Location locationB) {
        this.locationB = locationB;
    }
}
