package com.example.band_schadule.kafka;

import com.example.band_schadule.domain.request.MemberUpdateRequest;
import com.example.band_schadule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleConsumer {
    private final ScheduleService scheduleService;

    @RetryableTopic
    @KafkaListener(topics = TopicConfig.memberUpdate)
    public void listen(MemberUpdateRequest memberUpdateRequest) throws Exception {
        scheduleService.updateBoardMember(memberUpdateRequest);
    }

    @RetryableTopic
    @KafkaListener(topics = TopicConfig.memberDelete)
    public void listenMemberDelete(MemberUpdateRequest memberUpdateRequest) throws Exception {
        scheduleService.deleteMemberHandler(memberUpdateRequest.getMemberId());
    }

    @DltHandler
    public void processDltMessage(String dltMessage) {
        // DLT 토픽에서 메시지를 처리합니다. (예: 로깅 또는 추가 조사)
        System.out.println("DLT에서 메시지 수신: " + dltMessage);
    }

}
