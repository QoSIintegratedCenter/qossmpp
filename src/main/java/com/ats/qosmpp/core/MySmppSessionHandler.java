package com.ats.qosmpp.core;

import com.cloudhopper.smpp.SmppConstants;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import com.cloudhopper.smpp.pdu.DeliverSm;
import com.cloudhopper.smpp.pdu.PduRequest;
import com.cloudhopper.smpp.pdu.PduResponse;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;
@Slf4j
public class MySmppSessionHandler extends DefaultSmppSessionHandler {
//    public static Logger log = LoggerFactory.getLogger(MySmppSessionHandler.class);

    @Override
    public PduResponse firePduRequestReceived(PduRequest pduRequest) {
        if (
                pduRequest.isRequest()
                        && pduRequest.getClass() == DeliverSm.class
        ) {
            log.info("Got DELIVER_SM");

            DeliverSm dlr = (DeliverSm)pduRequest;

            log.info("Msg id={}", dlr.getOptionalParameter(SmppConstants.TAG_RECEIPTED_MSG_ID));
            log.debug("Status={}", dlr.getOptionalParameter(SmppConstants.TAG_MSG_STATE));

            return pduRequest.createResponse();
        }

        return super.firePduRequestReceived(pduRequest);
    }
}