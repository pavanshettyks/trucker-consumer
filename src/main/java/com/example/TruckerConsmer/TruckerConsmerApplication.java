package com.example.TruckerConsmer;

import com.example.TruckerConsmer.aws.VehicleAlertsListnerSQS;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TruckerConsmerApplication {

	public static void main(String[] args) throws JsonProcessingException {
		ApplicationContext applicationContext = SpringApplication.run(TruckerConsmerApplication.class, args);
		VehicleAlertsListnerSQS vehicleAlertsListnerSQS = applicationContext.getBean(VehicleAlertsListnerSQS.class);
		vehicleAlertsListnerSQS.startListeningToMessages();

	}

}
