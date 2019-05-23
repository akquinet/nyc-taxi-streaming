package com.akquinet.pipeline.streaming.Route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchConsumer extends RouteBuilder {

    @Value("${camel.elastic.kafka}")
    private String inputKafkaPath;

    @Value("${camel.elastic.error}")
    private String outputErrorPath;

    @Value("${camel.elastic.search}")
    private String outputElasticPath;

    @Override
    public final void configure() throws Exception {

        errorHandler(deadLetterChannel(outputErrorPath)
                .useOriginalMessage()
                .retryAttemptedLogLevel(LoggingLevel.WARN).log("An Error has occurred while processing " +
                        "${headers.CamelFileName} , please check the " +
                        "ErrorLog Folder to view the offending file"));

        from(inputKafkaPath).routeId("Write to Elastic Search")
                .noStreamCaching()
                .to(outputElasticPath);
    }
}
