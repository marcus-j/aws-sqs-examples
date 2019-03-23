package de.marcusjanke.examples.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Slf4j
@Configuration
public class SQSConfig {

    @Bean
    public AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder.defaultClient();
    }

    @Bean
    public String queueUrl(AmazonSQS amazonSQS) {
        String queueName = "testQueue" + new Date().getTime();
        CreateQueueRequest createQueueRequest = new CreateQueueRequest(queueName)
                .addAttributesEntry("DelaySeconds", "0")
                .addAttributesEntry("MessageRetentionPeriod", "86400");
        CreateQueueResult createQueueResult = amazonSQS.createQueue(createQueueRequest);
        String queueUrl = createQueueResult.getQueueUrl();
        log.info(String.format("Created test queue with URL '%s'", queueUrl));
        return queueUrl;
    }

}
