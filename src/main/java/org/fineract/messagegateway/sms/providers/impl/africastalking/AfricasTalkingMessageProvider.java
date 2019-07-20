package org.fineract.messagegateway.sms.providers.impl.africastalking;
import java.util.Collections;
import java.util.HashMap;

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

import infobip.api.client.SendMultipleTextualSmsAdvanced;
import infobip.api.config.BasicAuthConfiguration;
import infobip.api.model.Destination;
import infobip.api.model.sms.mt.send.Message;
import infobip.api.model.sms.mt.send.SMSResponse;
import infobip.api.model.sms.mt.send.SMSResponseDetails;
import infobip.api.model.sms.mt.send.textual.SMSAdvancedTextualRequest;

@Service(value = "africastalking")
public class AfricastalkingMessageProvider extends SMSProvider {

    private static final Logger logger = LoggerFactory.getLogger(AfricastalkingMessageProvider.class);

    private HashMap<String, SendMultipleTextualSmsAdvanced> restClients = new HashMap<>() ; //tenantId, twilio clients
    private final String callBackUrl ;

    private final StringBuilder builder ;

    @Autowired
    public AfricastalkingMessageProvider(final HostConfig hostConfig) {
        callBackUrl = String.format("%s://%s:%d/africastalking/report/", hostConfig.getProtocol(),  hostConfig.getHostName(), hostConfig.getPort());
        logger.info("Registering call back to africastalking:"+callBackUrl);
        builder = new StringBuilder() ;
    }

    @Override
    public void sendMessage(SMSBridge smsBridgeConfig, SMSMessage message) throws MessageGatewayException {
        // Send Message
    }

}
