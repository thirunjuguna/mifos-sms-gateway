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
    public void sendMessage(SMSBridge smsBridgeConfig, SMSMessage message) throws MessageGatewayException {
        // Send Message
    }

}
