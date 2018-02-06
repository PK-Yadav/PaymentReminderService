package com.payment.reminder.service.app.serviceimpl;

import com.payment.reminder.service.app.daysenum.NotificationDaysEnum;
import com.payment.reminder.service.app.entity.ReminderResponse;
import com.payment.reminder.service.app.responsecode.ResponseString;
import com.payment.reminder.service.app.serviceintf.IPaymentReminderService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("paymentService")
public class PaymentReminderService implements IPaymentReminderService {

	public ReminderResponse processPaymentReminderStatusService(Map<String, String> queryParam) throws Exception {
		ReminderResponse response = getReminderResponse(queryParam);
		if (ResponseString.UNPAID.equals(response.getPaymentStatus())) {
			getReminderResponseStatus(response);
		} else {
			response.setNotification(ResponseString.PAYMENT_RECEIVED);
		}
		return response;
	}

	private void getReminderResponseStatus(ReminderResponse reminderResponse) {
		int days = calculateBillGeneratedDays(reminderResponse.getBillDate());
		reminderResponse.setNotification(getReminderNotification(days));
	}

	private String getReminderNotification(int days) {
		if (days >= 0 && days < 3)
			return ResponseString.BILL_GENERATED;
		else if (days >= 3 && days < 7)
			return ResponseString.BILL_REMINDER_ONE;
		else if (days >= 7 && days < 15)
			return ResponseString.BILL_REMINDER_TWO;

		return ResponseString.SERVICE_BLOCKED;
	}

	private ReminderResponse getReminderResponse(Map<String, String> queryParam) {
		ReminderResponse response = new ReminderResponse();
		response.setBillDate(new Date(Long.parseLong(queryParam.get("billDate"))));
		response.setMobNumber(queryParam.get("mobileNumber"));
		response.setPaymentStatus(queryParam.get("paymentStatus"));
		return response;
	}

	private int calculateBillGeneratedDays(Date billDate) {
		Date today = new Date();
		long diffInMillies = Math.abs(today.getTime() - billDate.getTime());
		return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}