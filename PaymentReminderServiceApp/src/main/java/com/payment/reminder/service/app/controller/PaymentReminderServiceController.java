package com.payment.reminder.service.app.controller;

import com.payment.reminder.service.app.entity.ReminderResponse;
import com.payment.reminder.service.app.serviceintf.IPaymentReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/")
public final class PaymentReminderServiceController {

	@Autowired
	@Qualifier("paymentService")
	private IPaymentReminderService paymentReminderService;

	@RequestMapping(value = "remainder/response", method = RequestMethod.GET, produces ={ MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReminderResponse> getReminderResponse(@RequestParam Map<String, String> queryParam){
		ReminderResponse reminderResponse  = null;
		try{
			reminderResponse = paymentReminderService.processPaymentReminderStatusService(queryParam);
		}catch (Exception ex){
			new ResponseEntity<ReminderResponse>(reminderResponse, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ReminderResponse>(reminderResponse, HttpStatus.OK);
	}

}