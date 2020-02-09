package ro.nila.catalyst.utils;

import ro.nila.catalyst.models.CalculatedRoute;
import ro.nila.catalyst.models.CompareLocations;
import ro.nila.catalyst.models.Location;

import java.util.List;

public class Calculator {

    private static final double HYPERLOOP_TRAVEL_SPEED = 250.0;
    private static final double WAIT_AT_EACH_STOP = 200.0;
    private static final double DRIVE_TRAVEL_SPEED = 15.0;
    private static int counter = 0;

    private static double calculateHyperLoopTime(Location locationA, Location locationB) {
        return calculateDistance(locationA, locationB) / HYPERLOOP_TRAVEL_SPEED + WAIT_AT_EACH_STOP;
    }

    private static double calculateJourneyTime(Location start, Location end, Location hyperLoop1, Location hyperLoop2) {
        double result = 0.0;
        double drivingTime1 = calculateDrivingTime(start, hyperLoop2);
        double drivingTime2 = calculateDrivingTime(start, hyperLoop1);
        double drivingTime3 = 0.0;
        double hyperLoopTime = calculateHyperLoopTime(hyperLoop1, hyperLoop2);
        if (drivingTime1 > drivingTime2) {
            drivingTime3 = calculateDrivingTime(hyperLoop2, end);
            result = drivingTime2 + hyperLoopTime + drivingTime3;
        } else {
            drivingTime3 = calculateDrivingTime(hyperLoop1, end);
            result = drivingTime1 + hyperLoopTime + drivingTime3;
        }
        return result;
    }

    private static double evaluateConnection(List<CalculatedRoute> calculatedRoutes, CompareLocations compareLocations) {
        calculatedRoutes.forEach(calculatedRoute -> {
            double result = calculateJourneyTime(calculatedRoute.getLocationA(), calculatedRoute.getLocationB(), compareLocations.getLocationA(), compareLocations.getLocationB());
            if (result < calculatedRoute.getCurrentTime()) {
                System.out.println(calculatedRoute.getLocationA().getName() + " -> " + calculatedRoute.getLocationB().getName());
                System.out.println("Faster with Hyper Loop");
                counter++;
            } else if (result > calculatedRoute.getCurrentTime()) {
                System.out.println(calculatedRoute.getLocationA().getName() + " -> " + calculatedRoute.getLocationB().getName());
                System.out.println("Slower with Hyper Loop");
            } else {
                System.out.println(calculatedRoute.getLocationA().getName() + " -> " + calculatedRoute.getLocationB().getName());
                System.out.println("Travel time is equal");
            }
        });
        return counter;
    }

    private static double calculateDistance(Location locationA, Location locationB) {
        return Math.sqrt((locationB.getY() - locationA.getY()) * (locationB.getY() - locationA.getY()) + (locationB.getX() - locationA.getX()) * (locationB.getX() - locationA.getX()));
    }

    private static double calculateDrivingTime(Location locationA, Location locationB) {
        return calculateDistance(locationA, locationB) / DRIVE_TRAVEL_SPEED;
    }

    public static void getHyperLoopTime(Location locationA, Location locationB) {
        System.out.println("Hyper Loop time between " + locationA.getName() + " " + locationB.getName() + " = " + Math.round(calculateHyperLoopTime(locationA, locationB) * 10.0) / 10.0);
    }

    public static void getJourneyTime(CompareLocations travelRoute, CompareLocations hyperLoopRoute) {
        System.out.println("Journey time between " + travelRoute.getLocationA().getName() + " and "
                + travelRoute.getLocationB().getName() + " using hyper loop "
                + hyperLoopRoute.getLocationA().getName() + " " + hyperLoopRoute.getLocationB().getName() + " = " +
                Math.round(calculateJourneyTime(travelRoute.getLocationA(), travelRoute.getLocationB(), hyperLoopRoute.getLocationA(), hyperLoopRoute.getLocationB()) * 10.0) / 10.0);
    }

    public static void getEvaluationStatus(List<CalculatedRoute> calculatedRoutes, CompareLocations compareLocations) {
        System.out.println("Number of faster journeys = " + Math.round(evaluateConnection(calculatedRoutes, compareLocations)));
    }
}
