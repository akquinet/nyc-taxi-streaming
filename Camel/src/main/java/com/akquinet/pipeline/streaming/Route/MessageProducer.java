package com.akquinet.pipeline.streaming.Route;

import com.akquinet.pipeline.streaming.Model.TaxiRide;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

/*
    Author - Aaron Regan

    For this Streaming pipeline we are using a hand made stream to emulate a streaming environment. A CSV file is
    picked up and split line by line and stream to kafka. Although this may be a bottle neck in the architecture
    its main purpose is to represent a streaming source.
 */

@Component
public class MessageProducer extends RouteBuilder {

    @Override
    public final void configure() throws Exception {

        JacksonDataFormat jacksonDataFormat = new JacksonDataFormat();
        jacksonDataFormat.setPrettyPrint(true);

        errorHandler(deadLetterChannel("file:../Data/data_error")
                .useOriginalMessage()
                .retryAttemptedLogLevel(LoggingLevel.WARN).log("An Error has occurred while processing " +
                        "${headers.CamelFileName} , please check the " +
                        "ErrorLog Folder to view the offending file"));

        from("file:../Data/?noop=true&maxMessagesPerPoll=1&delay=5000")
                .streamCaching()
                .split(body().tokenize("\n")).streaming()
                .unmarshal().bindy(BindyType.Csv, TaxiRide.class)
                .routeId("NYC Taxi Streamer")
                .marshal().json(JsonLibrary.Jackson, TaxiRide.class)
                .to("kafka:rawTaxi?brokers=localhost:9092");
    }
}
