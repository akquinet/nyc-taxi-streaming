package com.akquinet.pipeline.streaming.Filter;

import com.akquinet.pipeline.streaming.Utils.Utils;
import com.akquinet.pipeline.streaming.model.TaxiRide;
import org.apache.flink.api.common.functions.FilterFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NYCFilter implements FilterFunction<TaxiRide> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NYCFilter.class);

    @Override
    public boolean filter(TaxiRide taxiRide) throws Exception {

        Boolean test = Utils.isInNYC(taxiRide.getPickupLat(), taxiRide.getPickupLong()) &&
                Utils.isInNYC(taxiRide.getDropoffLat(), taxiRide.getDropoffLong());

        return test;
    }
}
