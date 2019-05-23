package com.akquinet.pipeline.streaming.Schema;

import com.akquinet.pipeline.streaming.model.TaxiRide;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TaxiRideSchema implements DeserializationSchema<TaxiRide>, SerializationSchema<TaxiRide> {

    private ObjectMapper mapper = new ObjectMapper(); //com.fasterxml.jackson.databind.ObjectMapper;

    @Override
    public byte[] serialize(TaxiRide element) {
        try {
            return mapper.writeValueAsBytes(element);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error: Skipped Try Catch Block in Taxi Ride Schema".getBytes();
    }

    @Override
    public TaxiRide deserialize(byte[] bytes) throws IOException {
        return mapper.readValue( bytes, TaxiRide.class );
    }

    @Override
    public boolean isEndOfStream(TaxiRide taxiRide) {
        return false;
    }

    @Override
    public TypeInformation<TaxiRide> getProducedType() {
        return TypeInformation.of(new TypeHint<TaxiRide>(){});
    }
}
