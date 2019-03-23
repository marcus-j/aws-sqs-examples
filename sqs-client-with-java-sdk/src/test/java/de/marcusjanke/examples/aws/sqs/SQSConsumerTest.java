package de.marcusjanke.examples.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SQSConsumer.class, SQSProducerTest.TestConfig.class})
@EnableScheduling
public class SQSConsumerTest {

    @Autowired
    private AmazonSQS amazonSQS;
    @Autowired
    private String queueUrl;

    @Before
    public void setUp() {
        when(amazonSQS.receiveMessage(any(ReceiveMessageRequest.class))).thenReturn(new ReceiveMessageResult());
    }

    @Test
    public void shouldConsumeScheduledMessages() {
        ReceiveMessageRequest request = new ReceiveMessageRequest()
                .withMaxNumberOfMessages(10)
                .withQueueUrl(queueUrl);
        verify(amazonSQS, timeout(2000)).receiveMessage(eq(request));
    }

    @TestConfiguration
    static class TestConfig {

        @MockBean
        private AmazonSQS amazonSQS;

        @Bean
        public String queueUrl() {
            return "test";
        }
    }
}