package de.marcusjanke.examples.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageResult;
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

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SQSProducer.class, SQSProducerTest.TestConfig.class})
@EnableScheduling
public class SQSProducerTest {

    @Autowired
    private AmazonSQS amazonSQS;
    @Autowired
    private String queueUrl;

    @Before
    public void setUp() {
        when(amazonSQS.sendMessage(eq(queueUrl), any())).thenReturn(new SendMessageResult().withMessageId(UUID.randomUUID().toString()));
    }

    @Test
    public void shouldProduceScheduledMessages() {
        verify(amazonSQS, timeout(2000)).sendMessage(eq(queueUrl), any());
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