package com.akquinet.pipeline.streaming.Route;

import com.akquinet.pipeline.streaming.Model.TaxiRide;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer extends RouteBuilder {

    @Value("${camel.message-consumer.kafka}")
    private String inputKafkaPath;

    @Value("${camel.message-consumer.error}")
    private String outputErrorPath;

    @Value("${camel.message-consumer.datalake}")
    private String outputDataLakePath;

    @Override
    public final void configure() throws Exception {

        errorHandler(deadLetterChannel(outputErrorPath)
                .useOriginalMessage()
                .retryAttemptedLogLevel(LoggingLevel.WARN).log("An Error has occurred while processing " +
                        "${headers.CamelFileName} , please check the " +
                        "ErrorLog Folder to view the offending file"));

        from(inputKafkaPath).routeId("Write to Data Lake")
                .streamCaching()
                .unmarshal(new JacksonDataFormat(TaxiRide.class))
                .to(outputDataLakePath);
    }
}
