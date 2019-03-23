package de.marcusjanke.examples.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSProducer {

    private final AmazonSQS amazonSQS;
    private final String queueUrl;

    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    public void produceMessage() {
        String body = LocalDateTime.now().toString();
        SendMessageResult sendMessageResult = amazonSQS.sendMessage(queueUrl, body);
        log.info(String.format("Sent message with body '%s'", body));
    }
}
