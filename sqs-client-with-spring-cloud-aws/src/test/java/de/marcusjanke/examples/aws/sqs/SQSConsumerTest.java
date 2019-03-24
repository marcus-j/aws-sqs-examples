package de.marcusjanke.examples.aws.sqs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SQSConsumer.class)
public class SQSConsumerTest {

    @Test
    public void shouldConsumeScheduledMessages() {
        //TODO: Figure out a good way to integration test this without AWS or local cloud instance
    }

}