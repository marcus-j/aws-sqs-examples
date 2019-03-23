package de.marcusjanke.examples.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSConsumer {

    private final AmazonSQS amazonSQS;
    private final String queueUrl;

    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    public void consumeMessage() {
        ReceiveMessageRequest request = new ReceiveMessageRequest()
                .withMaxNumberOfMessages(10)
                .withQueueUrl(queueUrl);
        ReceiveMessageResult receiveMessageResult = amazonSQS.receiveMessage(request);
        if (nonNull(receiveMessageResult)) {
            receiveMessageResult.getMessages().forEach(
                    message -> log.info(String.format("Received message with body '%s'", message.getBody()))
            );
        }
    }
}
