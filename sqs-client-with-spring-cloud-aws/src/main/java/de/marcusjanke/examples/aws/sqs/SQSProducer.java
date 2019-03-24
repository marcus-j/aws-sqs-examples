package de.marcusjanke.examples.aws.sqs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static de.marcusjanke.examples.aws.sqs.SQSConfig.QUEUE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSProducer {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    public void produceMessage() {
        Message<String> message = MessageBuilder.withPayload(LocalDateTime.now().toString()).build();
        queueMessagingTemplate.send(QUEUE_NAME, message);
        log.info(String.format("Sent message '%s'", message.getPayload()));
    }
}
