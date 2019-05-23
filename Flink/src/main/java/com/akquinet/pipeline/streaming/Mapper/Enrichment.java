package com.akquinet.pipeline.streaming.Mapper;

import com.akquinet.pipeline.streaming.model.EnrichedRide;
import com.akquinet.pipeline.streaming.model.TaxiRide;
import org.apache.flink.api.common.functions.MapFunction;

public class Enrichment implements MapFunction<TaxiRide, EnrichedRide> {
    @Override
    public EnrichedRide map(TaxiRide taxiRide) throws Exception {
        return new EnrichedRide(taxiRide);
    }
}
