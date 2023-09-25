package com.example.band_schadule.kafka;

import com.example.band_schadule.domain.request.MemberUpdateRequest;
import com.example.band_schadule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleConsumer {
    private final ScheduleService scheduleService;

    @KafkaListener(topics = TopicConfig.scheduleUpdate)
    public void listen(MemberUpdateRequest memberUpdateRequest) throws Exception {
        scheduleService.updateBoardMember(memberUpdateRequest);
    }

}
