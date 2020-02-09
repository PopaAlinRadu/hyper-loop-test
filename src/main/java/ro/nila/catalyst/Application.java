package ro.nila.catalyst;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ro.nila.catalyst.models.CalculatedRoute;
import ro.nila.catalyst.models.CompareLocations;
import ro.nila.catalyst.utils.DataExtractor;
import ro.nila.catalyst.models.Location;

import static ro.nila.catalyst.utils.Calculator.*;

public class Application {

    private static List<Location> locationList = new ArrayList<>();
    private static List<CompareLocations> compareLocationsList = new ArrayList<>();
    private static List<CalculatedRoute> calculatedRouteList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        try {
            DataExtractor.extractDataFromFile("src/main/resources/locations.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        locationList = DataExtractor.getLocationList();
        compareLocationsList = DataExtractor.getCompareLocationsList();
        calculatedRouteList = DataExtractor.getCalculatedRouteList();

        if (compareLocationsList.size() == 1 && calculatedRouteList.isEmpty()){
            System.out.println("Level one exercise. Calculate Hyperloop Time");
                getHyperLoopTime(compareLocationsList.get(0).getLocationA(), compareLocationsList.get(0).getLocationB());
        }
        if (compareLocationsList.size() == 2){
            System.out.println("Level two exercise. Calculate Journey Time");
            getJourneyTime(compareLocationsList.get(0), compareLocationsList.get(1));
        }
        if (compareLocationsList.size() == 1 && !calculatedRouteList.isEmpty()){
            System.out.println("Level three exercise. Calculate Connection evaluation");
            getEvaluationStatus(calculatedRouteList, compareLocationsList.get(0));
        }


    }
}
