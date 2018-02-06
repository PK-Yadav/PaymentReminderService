package com.payment.reminder.service.app.daysenum;

public enum NotificationDaysEnum{
	ZERO(0),
	THREE(3),
	FIVE(7),
	FIFTEEN(15);

	private int days;

	private NotificationDaysEnum (int days) {
		this. days = days;
	}

}