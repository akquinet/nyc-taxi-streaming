package com.akquinet.pipeline.streaming.service;

import com.akquinet.pipeline.streaming.Schema.EnrichedRideSchema;
import com.akquinet.pipeline.streaming.model.EnrichedRide;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

public class FlinkProducer {

    public static FlinkKafkaProducer<EnrichedRide> createStringProducer(
            String topic, String kafkaAddress){

        return new FlinkKafkaProducer<>(kafkaAddress,
                topic, new EnrichedRideSchema());
    }
}
