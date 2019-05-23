package com.akquinet.pipeline.streaming.Route;

import com.akquinet.pipeline.streaming.Model.TaxiRide;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer extends RouteBuilder {

    @Override
    public final void configure() throws Exception {

        errorHandler(deadLetterChannel("file:../Data/data_error/mongo")
                .useOriginalMessage()
                .retryAttemptedLogLevel(LoggingLevel.WARN).log("An Error has occurred while processing " +
                        "${headers.CamelFileName} , please check the " +
                        "ErrorLog Folder to view the offending file"));

        from("kafka:rawTaxi?brokers=localhost:9092")
                .routeId("Write to Data Lake")
                .streamCaching()
                .unmarshal(new JacksonDataFormat(TaxiRide.class))
                .to("mongodb3://mongoBean?database=taxi&collection=nyc&" +
                        "operation=insert&createCollection=true");
    }
}
