package org.fineract.messagegateway.sms.providers.impl.africastalking;


import org.fineract.messagegateway.sms.util.SmsMessageStatusType;


public class AfricasTalkingStatus {

    public static SmsMessageStatusType smsStatus(final String africastalkingStatus) {
        SmsMessageStatusType smsStatus = SmsMessageStatusType.PENDING;
        switch(africastalkingStatus) {
            case "PENDING":
            case "ACCEPTED":
                smsStatus = SmsMessageStatusType.WAITING_FOR_REPORT ;
                break ;
            case "sent" :
                smsStatus = SmsMessageStatusType.SENT ;
                break ;
            case "DELIVERED":
                smsStatus = SmsMessageStatusType.DELIVERED;
                break ;
            case "UNDELIVERABLE":
            case "EXPIRED" :
            case "REJECTED":
                smsStatus = SmsMessageStatusType.FAILED ;
                break ;
        }
        return smsStatus ;
    }

    public static SmsMessageStatusType smsStatus(final Integer africastalking) {
        SmsMessageStatusType smsStatus = SmsMessageStatusType.PENDING;
        switch(africastalking) {
            case 0:
                smsStatus = SmsMessageStatusType.WAITING_FOR_REPORT ;
            case 1:
                smsStatus = SmsMessageStatusType.SENT ;
                break ;
            case 2:
            case 4:
            case 5:
                smsStatus = SmsMessageStatusType.FAILED;
                break ;

            case 3:
                smsStatus = SmsMessageStatusType.DELIVERED;
                break ;
        }
        return smsStatus ;
    }
}