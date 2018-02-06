package com.payment.reminder.service.app.serviceintf;

import com.payment.reminder.service.app.entity.ReminderResponse;

import java.util.Map;

public interface IPaymentReminderService{

	ReminderResponse processPaymentReminderStatusService(Map<String, String> queryParam) throws Exception;
}