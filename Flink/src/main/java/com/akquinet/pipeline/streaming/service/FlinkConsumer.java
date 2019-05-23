package com.akquinet.pipeline.streaming.service;

import com.akquinet.pipeline.streaming.Schema.TaxiRideSchema;
import com.akquinet.pipeline.streaming.model.TaxiRide;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class FlinkConsumer {
    public static FlinkKafkaConsumer<TaxiRide> createStringConsumerForTopic(
            String topic, String kafkaAddress, String groupId) {

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", kafkaAddress);
        props.setProperty("group.id", groupId);
        FlinkKafkaConsumer<TaxiRide> consumer = new FlinkKafkaConsumer<>(
                topic, new TaxiRideSchema(), props);

        return consumer;
    }
}
