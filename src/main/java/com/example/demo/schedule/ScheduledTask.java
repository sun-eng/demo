package com.example.demo.schedule;

import com.example.demo.entity.Relation;
import com.example.demo.repository.RelationRepository;
import com.example.demo.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class ScheduledTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    RelationRepository relationRepository;

    @Autowired
    RedisUtil redisUtil;

    @Scheduled(cron = "0/5 * * * * *")
    public void run() throws InterruptedException {
        Thread.sleep(6000);
        List<Relation> list = relationRepository.findAll();
        redisUtil.set("demo_stuFindAll", list);
        LOGGER.info("ScheduledTask run() with data:" + list.toString());
    }
}
