package com.akquinet.pipeline.streaming.Route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ElasticSearchConsumer extends RouteBuilder {

    @Override
    public final void configure() throws Exception {

        errorHandler(deadLetterChannel("file:../Data/data_error/elastic")
                .useOriginalMessage()
                .retryAttemptedLogLevel(LoggingLevel.WARN).log("An Error has occurred while processing " +
                        "${headers.CamelFileName} , please check the " +
                        "ErrorLog Folder to view the offending file"));

        from("kafka:cookedTaxi?brokers=localhost:9092")
                .routeId("Write to Elastic Search")
                .noStreamCaching()
                .to("elasticsearch-rest://elasticsearch_aaron?operation=INDEX&indexName=taxi_nyc&indexType=trip");
    }
}
