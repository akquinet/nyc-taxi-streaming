package com.akquinet.pipeline.streaming.Utils;

import com.akquinet.pipeline.streaming.model.TaxiRide;

public class Utils {
    // geo boundaries of the area of NYC
    public static double LON_EAST = -73.7;
    public static double LON_WEST = -74.05;
    public static double LAT_NORTH = 41.0;
    public static double LAT_SOUTH = 40.5;

    public static boolean isInNYC(double lat, double lon) {
        return !(lon > LON_EAST || lon < LON_WEST) &&
                !(lat > LAT_NORTH || lat < LAT_SOUTH);
    }
}
