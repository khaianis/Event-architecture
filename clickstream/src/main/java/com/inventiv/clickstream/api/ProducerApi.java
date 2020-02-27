package com.inventiv.clickstream.api;

import com.inventiv.clickstream.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/producer")
@RestController
public class ProducerApi {
    private final Logger logger = LoggerFactory.getLogger(ProducerApi.class) ;
    private Producer producer ;
    public ProducerApi(Producer producer)
    {
        this.producer = producer ;
    }
    @RequestMapping("/send")
    @PostMapping
    public void produceMessage(@RequestParam("message") String message)
    {

       producer.sendMessage(message);
       logger.info("message was send ==="+message);
    }
}
