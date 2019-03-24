package de.marcusjanke.examples.aws.sqs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import static de.marcusjanke.examples.aws.sqs.SQSConfig.QUEUE_NAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SQSProducer.class, SQSProducerTest.TestConfig.class})
@EnableScheduling
public class SQSProducerTest {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Test
    public void shouldProduceScheduledMessages() {
        verify(queueMessagingTemplate, timeout(2000)).send(eq(QUEUE_NAME), any());
    }

    @TestConfiguration
    static class TestConfig {

        @MockBean
        private QueueMessagingTemplate queueMessagingTemplate;
    }
}