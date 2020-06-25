package com.example.TruckerConsmer.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.example.TruckerConsmer.model.Alerts;
import com.example.TruckerConsmer.model.SQSMsg;
import com.example.TruckerConsmer.service.AlertsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class VehicleAlertsListnerSQS {


    @Value("${sqs.url}")
    private String sqsUrl;

    @Value("${cloud.aws.credentials.accessKey}")
    private String awsAccessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String awsSecretKey;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    private ObjectMapper objectMapper ;

    private AlertsService alertsService;

    private AmazonSQS amazonSQS;

    @Autowired
    VehicleAlertsListnerSQS(@Value("${sqs.url}") String sqsUrl,
                            @Value("${cloud.aws.credentials.accessKey}") String awsAccessKey,
                            @Value("${cloud.aws.credentials.secretKey}") String awsSecretKey,
                            @Value("${cloud.aws.region.static}")String awsRegion,
                            ObjectMapper objectMapper,
                            AlertsService alertsService,
                            AmazonSQS amazonSQS){
        this.sqsUrl=sqsUrl;
        this.awsAccessKey=awsAccessKey;
        this.awsSecretKey=awsSecretKey;
        this.awsRegion=awsRegion;

        this.alertsService = alertsService;
        this.amazonSQS =amazonSQS;
        this.objectMapper =objectMapper;

    }
    @PostConstruct
    private void postConstructor() {


        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
        );

        this.amazonSQS = AmazonSQSClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion)
                .build();
    }

    private void deleteMessage(Message messageObject) {

        final String messageReceiptHandle = messageObject.getReceiptHandle();
        amazonSQS.deleteMessage(new DeleteMessageRequest(sqsUrl, messageReceiptHandle));

    }

    public void startListeningToMessages()  throws JsonProcessingException {
        final ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(sqsUrl)
                .withMaxNumberOfMessages(1)
                .withWaitTimeSeconds(3);

        while (true) {

            final List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();

            for (Message messageObject : messages) {
                String message = messageObject.getBody();
                // this is processing
                SQSMsg sqsMsg = objectMapper.readValue(message,SQSMsg.class);
                System.out.println(sqsMsg);
                Alerts alerts = objectMapper.readValue(sqsMsg.getMessage(), Alerts.class);
                System.out.println(alerts);

                alertsService.save(alerts );
                deleteMessage(messageObject);
            }
        }


    }


}
