package org.fineract.messagegateway.sms.providers.impl.africastalking;
import org.fineract.messagegateway.sms.domain.SMSMessage;
import org.fineract.messagegateway.sms.repository.SmsOutboundMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import infobip.api.model.sms.mt.reports.SMSReport;
import infobip.api.model.sms.mt.reports.SMSReportResponse;

@RestController
@RequestMapping("/africastalking")
public class AfricasTalkingApiResource {
    private static final Logger logger = LoggerFactory.getLogger(InfoBipApiResource.class);
    private final SmsOutboundMessageRepository smsOutboundMessageRepository ;

    @Autowired
    public AfricasTalkingApiResource(final SmsOutboundMessageRepository smsOutboundMessageRepository) {
        this.smsOutboundMessageRepository = smsOutboundMessageRepository ;
    }

    @RequestMapping(value = "/report/{messageId}", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Void> updateDeliveryStatus(@PathVariable("messageId") final Long messageId, @RequestBody final SMSReportResponse payload) {
        SMSMessage message = this.smsOutboundMessageRepository.findOne(messageId) ;
        if(message != null) {
            SMSReport report = payload.getResults().get(0) ;
            logger.debug("Status Callback received from AfricasTalking for "+messageId+" with status:"+report.getStatus());
            message.setDeliveryStatus(AfricasTalkingStatus.smsStatus(report.getStatus().getGroupId()).getValue());
            this.smsOutboundMessageRepository.save(message) ;
        }else {
            logger.info("Message with Message id "+messageId+" Not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}