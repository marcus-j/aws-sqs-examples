package de.marcusjanke.examples.aws.sqs;

import org.elasticmq.rest.sqs.SQSRestServer;
import org.elasticmq.rest.sqs.SQSRestServerBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SQSConsumer.class)
public class SQSConsumerTest {

    private static SQSRestServer sqsRestServer;

    @BeforeClass
    public static void setUp() {
        sqsRestServer = SQSRestServerBuilder.start();
        sqsRestServer.waitUntilStarted();
    }

    @AfterClass
    public static void tearDown() {
        sqsRestServer.stopAndWait();
    }

    @Test
    public void shouldConsumeScheduledMessages() {
        //TODO: Figure out a good way to integration test this without AWS or local cloud instance
        // This might help: http://codedevstuff.blogspot.com/2017/12/setup-local-aws-sqs-service-with.html
    }

}