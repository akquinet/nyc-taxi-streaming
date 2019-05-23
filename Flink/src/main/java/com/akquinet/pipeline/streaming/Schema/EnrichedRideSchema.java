package com.akquinet.pipeline.streaming.Schema;

import com.akquinet.pipeline.streaming.model.EnrichedRide;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EnrichedRideSchema implements DeserializationSchema<EnrichedRide>, SerializationSchema<EnrichedRide> {

    private ObjectMapper mapper = new ObjectMapper(); //com.fasterxml.jackson.databind.ObjectMapper;

    @Override
    public byte[] serialize(EnrichedRide element) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            mapper.setDateFormat(df);
            return mapper.writeValueAsBytes(element);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Error: Skipped Try Catch Block in Taxi Ride Schema".getBytes();
    }

    @Override
    public EnrichedRide deserialize(byte[] bytes) throws IOException {
        return mapper.readValue( bytes, EnrichedRide.class );
    }

    @Override
    public boolean isEndOfStream(EnrichedRide enrichedRide) {
        return false;
    }

    @Override
    public TypeInformation<EnrichedRide> getProducedType() {
        return TypeInformation.of(new TypeHint<EnrichedRide>(){});
    }
}
