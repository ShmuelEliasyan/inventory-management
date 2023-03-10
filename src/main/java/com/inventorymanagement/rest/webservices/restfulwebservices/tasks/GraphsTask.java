package com.inventorymanagement.rest.webservices.restfulwebservices.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class GraphsTask {

    private static final Logger log = LoggerFactory.getLogger(GraphsTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0 0 0 * * *")
    public void generateGraphsContent() {
        log.info("Starting generateGraphsContent - {}", dateFormat.format(new Date()));

    }
}