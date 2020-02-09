package ro.nila.catalyst.utils;

import ro.nila.catalyst.models.CalculatedRoute;
import ro.nila.catalyst.models.CompareLocations;
import ro.nila.catalyst.models.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataExtractor {

    private static List<Location> locationList = new ArrayList<>();
    private static List<CompareLocations> compareLocationsList = new ArrayList<>();
    private static List<CalculatedRoute> calculatedRouteList = new ArrayList<>();

    private static final String LOCATION_REGEX = "\\S+\\s{1}+\\p{Punct}?\\d+\\s{1}+\\p{Punct}?\\d+";
    private static final String COMPARED_LOCATION_REGEX = "\\S+\\s{1}+\\S+";
    private static final String CALCULATED_ROUTES_REGEX = "\\S+\\s{1}+\\S+\\s{1}+\\d+";


    public static void extractDataFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String currentLine = reader.readLine();
        while (currentLine != null) {
            if (currentLine.matches(COMPARED_LOCATION_REGEX)) {
                createComparedLocationFromFile(currentLine);
            }
            if (currentLine.matches(CALCULATED_ROUTES_REGEX)) {
                if (currentLine.matches(LOCATION_REGEX)) {
                    createLocationsFromLine(currentLine);
                } else {
                    createCalculatedRouteFromLine(currentLine);
                }
            }
            currentLine = reader.readLine();
        }
        reader.close();
    }

    private static void createCalculatedRouteFromLine(String line) {
        String[] split = line.split(" ");
        Location locationA = getLocationByName(split[0]);
        Location locationB = getLocationByName(split[1]);
        double currentTime = Double.parseDouble(split[2]);
        CalculatedRoute calculatedRoute = new CalculatedRoute(locationA, locationB, currentTime);
        getCalculatedRouteList().add(calculatedRoute);
    }

    private static void createComparedLocationFromFile(String line) {
        String[] split = line.split(" ");
        Location locationA = getLocationByName(split[0]);
        Location locationB = getLocationByName(split[1]);
        CompareLocations compareLocation = new CompareLocations(locationA, locationB);
        getCompareLocationsList().add(compareLocation);
    }

    private static void createLocationsFromLine(String line) {
        String locationName = null;
        double x = 0.0;
        double y = 0.0;
        String[] split = line.split(" ");
        locationName = split[0];
        x = Double.parseDouble(split[1]);
        y = Double.parseDouble(split[2]);
        Location location = new Location(locationName, x, y);
        getLocationList().add(location);
    }

    private static Location getLocationByName(String name) {
        return getLocationList().stream().filter(location -> location.getName().equals(name)).collect(Collectors.toList()).get(0);
    }

    public static List<Location> getLocationList() {
        return locationList;
    }

    public static List<CompareLocations> getCompareLocationsList() {
        return compareLocationsList;
    }

    public static List<CalculatedRoute> getCalculatedRouteList() {
        return calculatedRouteList;
    }
}
