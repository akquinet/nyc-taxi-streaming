package com.akquinet.pipeline.streaming.service;

import com.akquinet.pipeline.streaming.Filter.NYCFilter;
import com.akquinet.pipeline.streaming.Mapper.Enrichment;
import com.akquinet.pipeline.streaming.model.EnrichedRide;
import com.akquinet.pipeline.streaming.model.TaxiRide;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.akquinet.pipeline.streaming.service.FlinkConsumer.createStringConsumerForTopic;
import static com.akquinet.pipeline.streaming.service.FlinkProducer.createStringProducer;

@Component
public class FlinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlinkService.class);

    @Value("${flink.prop.kafka-input}")
    private String inputTopic;

    @Value("${flink.prop.kafka.output}")
    private String outputTopic;

    @Value("${flink.prop.address}")
    private String address;

    @Value("${flink.prop.group-id}")
    private String groupId;

    @PostConstruct
    public void init() {
        LOGGER.info("Initialising Stream Enviroment");

        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        FlinkKafkaConsumer<TaxiRide> flinkKafkaConsumer = createStringConsumerForTopic(
                inputTopic, address, groupId);

        DataStream<TaxiRide> stringInputStream = environment.addSource(flinkKafkaConsumer);

        FlinkKafkaProducer<EnrichedRide> flinkKafkaProducer = createStringProducer(
                outputTopic, address);

        DataStream<TaxiRide> filterNYCRide = stringInputStream.filter(new NYCFilter());

        DataStream<EnrichedRide> enrichedNYCRides = filterNYCRide.map(new Enrichment());

        enrichedNYCRides.addSink(flinkKafkaProducer);


        try {
            environment.execute();
        } catch (Exception e) {
            System.out.println("Error executing flink job: " + e.getMessage());
        }
    }
}
