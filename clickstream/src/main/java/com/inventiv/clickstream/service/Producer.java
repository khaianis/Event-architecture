package com.inventiv.clickstream.service;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Service
public class Producer {

    private static final String TOPIC = "users_click";
    public static final Logger logger = LoggerFactory.getLogger(Producer.class) ;
    @Autowired
    private KafkaTemplate<String,Message<String>> kafkaTemplate ;

    public void sendMessage(String data) {

        for(int i = 0 ; i <  10 ; i++)
        {
        Message<String> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .setHeader(KafkaHeaders.MESSAGE_KEY, i)
                .setHeader(KafkaHeaders.PARTITION_ID, 0)
                .setHeader("X-Custom-Header", "custom Header with Spring Kafka")
                .build();
        logger.info(String.format("#### -> Producing message -> %s", message));
        kafkaTemplate.send(TOPIC, message ) ;
         }
    }

}
