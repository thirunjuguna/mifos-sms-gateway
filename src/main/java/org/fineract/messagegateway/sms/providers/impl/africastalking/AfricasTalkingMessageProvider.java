package org.fineract.messagegateway.sms.providers.impl.africastalking;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import org.fineract.messagegateway.configuration.HostConfig;
import org.fineract.messagegateway.constants.MessageGatewayConstants;
import org.fineract.messagegateway.exception.MessageGatewayException;
import org.fineract.messagegateway.sms.domain.SMSBridge;
import org.fineract.messagegateway.sms.domain.SMSMessage;
import org.fineract.messagegateway.sms.providers.SMSProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service(value = "africastalking")
public class AfricasTalkingMessageProvider extends SMSProvider {

    private static final Logger logger = LoggerFactory.getLogger(AfricasTalkingMessageProvider.class);

    private final String callBackUrl ;

    private final StringBuilder builder ;

    @Autowired
    public AfricasTalkingMessageProvider(final HostConfig hostConfig) {
        callBackUrl = String.format("%s://%s:%d/africastalking/report/", hostConfig.getProtocol(),  hostConfig.getHostName(), hostConfig.getPort());
        logger.info("Registering call back to africastalking:"+callBackUrl);
        builder = new StringBuilder() ;
    }

    @Override
    public void sendMessage(SMSBridge smsBridgeConfig, SMSMessage message) throws MessageGatewayException, IOException {
        String statusCallback = callBackUrl+message.getId() ;
        //Based on message id, register call back. so that we get notification from Infobip about message status


        builder.setLength(0);
        builder.append(smsBridgeConfig.getCountryCode()) ;
        builder.append(message.getMobileNumber()) ;
        String mobile = builder.toString() ;
        logger.info("Sending SMS to " + mobile + " ...");

        String username = smsBridgeConfig.getConfigValue(MessageGatewayConstants.PROVIDER_ACCOUNT_ID) ;
        String apiKey = smsBridgeConfig.getConfigValue(MessageGatewayConstants.PROVIDER_AUTH_TOKEN) ;


        AfricasTalking.initialize(username, apiKey);

        SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

        List<Recipient> response = sms.send(String.valueOf(message), new String[] {mobile}, true);

//        message.setExternalId(response.getMessageId());
//        message.setDeliveryStatus(AfricasTalkingStatus.smsStatus(response.getStatus().getGroupId()).getValue());
//        logger.debug("AfricasTalkingMessageProvider.sendMessage():"+AfricasTalkingStatus.smsStatus(response.getStatus().getGroupId()).getValue());
    }




}
