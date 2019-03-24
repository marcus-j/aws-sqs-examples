package de.marcusjanke.examples.aws.sqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import static de.marcusjanke.examples.aws.sqs.SQSConfig.QUEUE_NAME;

@Slf4j
@Component
class SQSConsumer {

    @SqsListener(QUEUE_NAME)
    private void consumeMessage(String message) {
        log.info(String.format("Received message '%s'", message));
    }
}
