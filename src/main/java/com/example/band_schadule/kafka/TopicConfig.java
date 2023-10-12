package com.example.band_schadule.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TopicConfig {
    public final static String memberUpdate = "memberUpdate";

    public final static String memberDelete = "memberDelete";



}
