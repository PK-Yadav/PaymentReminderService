# PaymentReminderService

To test this project, you have to call this url using rest client or postman..

http://localhost:8080/remainder/response?mobileNumber=9643606096&paymentStatus=unpaid&billDate=1517423400000

if status is unpaid then it will check for different notification status other wise it will return Payment received.

just run generated jar in taget directory using 

## java -jar payment-reminder-service.0.0.1-SNAPSHOT.jar

