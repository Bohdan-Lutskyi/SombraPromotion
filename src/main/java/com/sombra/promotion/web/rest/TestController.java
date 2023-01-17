package com.sombra.promotion.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * REST controller for managing {@link com.sombra.promotion.domain.Course}.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger log = LoggerFactory.getLogger(TestController.class);

    private static AtomicInteger x = new AtomicInteger(1);


    @GetMapping("/t")
    public void t() throws InterruptedException {
        x.incrementAndGet();
        log.error("Started");
//        Thread.sleep(2000);
        if (x.get()%4 == 0) {
            Thread.sleep(10000);
            x.set(1);
        }
        log.error("Finished");
    }
    @GetMapping("/t2")
    public void t2() throws InterruptedException {
//        x.incrementAndGet();
        log.error("Started");
//        Thread.sleep(2000);
//        if (x.get()%4 == 0) {
            Thread.sleep(10000);
//            x.set(1);
//        }
        log.error("Finished");
    }
}
