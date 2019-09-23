package com.ats.qosmpp.core;

import com.ats.qosmpp.QosSmppApplication;
import com.ats.qosmpp.config.SmscConfig;
import com.ats.qosmpp.domain.Requests;
import com.ats.qosmpp.domain.Responses;
import com.ats.qosmpp.repository.RequestRepository;
import com.ats.qosmpp.repository.ResponsesRepository;
import com.ats.qosmpp.service.OperationServices;
import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.pdu.DeliverSm;
import com.cloudhopper.smpp.pdu.SubmitSm;
import com.cloudhopper.smpp.pdu.SubmitSmResp;
import com.cloudhopper.smpp.type.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
//@Slf4j
public class SmscSender {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(QosSmppApplication.class);

  /*  @Autowired
    @Lazy
    private RequestRepository requestRepository;
    @Autowired
    @Lazy
    private ResponsesRepository responsesRepository;
    */
    @Autowired
//    @Lazy
    private OperationServices operationServices;

    DefaultSmppClient client = new DefaultSmppClient();
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SmscSender.class);
    SmscConfig smscConfig;


    public SmscSender(SmscConfig smscConfig) {
        this.smscConfig = smscConfig;

    }

    public Responses send(String from, String to, String sms) {
        Responses responses = new Responses();
        boolean send = false;

        try {
//            Requests requests = operationServices.saveRequest(from, to, sms, smscConfig.getCharset());
//            logger.info("Request ", requests.getFromParam());
            logger.info("create session final param to {} from {} txt {} ", to, from, sms);
            SmppSession session = client.bind(smscConfig.getSessionConfg());

            SubmitSm sm = createSubmitSm(from, to, sms, smscConfig.getCharset());

            System.out.println("Try to send message");

            SubmitSmResp resp = session.submit(sm, TimeUnit.SECONDS.toMillis(60));

            System.out.println("Message sent");
            Logger.getLogger(SmscSender.class.getName()).log(Level.CONFIG, null, send);
            System.out.println("Wait 10 seconds");

            TimeUnit.SECONDS.sleep(10);
            try {
                responses.setName(resp.getName());
                responses.setCommandId(resp.getCommandId());
                responses.setCommandStatus(resp.getCommandStatus());
                responses.setConnexionSize(client.getConnectionSize());
                responses.setMessageId(resp.getMessageId());
                responses.setResultMessage(resp.getResultMessage());
//                responses.setResponseDate(LocalDateTime.now().toString());

//                operationServices.saveResponse(client.getConnectionSize(), resp, requests);
                logger.info("Operation saved");
            } catch (Exception e) {
                logger.info("Data not save in database");
            }

            System.out.println("Destroy session");
            /**
             * reponse
             */


            session.close();
            session.destroy();

            System.out.println("Destroy client");

            client.destroy();

            System.out.println("Bye!");
        } catch (SmppTimeoutException | UnrecoverablePduException | InterruptedException | RecoverablePduException | SmppChannelException ex) {
            Logger.getLogger(SmscSender.class.getName()).log(Level.SEVERE, null, ex);
        }

        return responses;
    }    public Responses sendWithDelyver(String from, String to, String sms) {
        Responses responses = new Responses();
        boolean send = false;
        DeliverSm deliverSm = new DeliverSm();


        try {
//            Requests requests = operationServices.saveRequest(from, to, sms, smscConfig.getCharset());
//            logger.info("Request ", requests.getFromParam());
            logger.info("create session final param to {} from {} txt {} ", to, from, sms);
            SmppSession session = client.bind(smscConfig.getSessionConfg());

            SubmitSm sm = createSubmitSm(from, to, sms, smscConfig.getCharset());

            System.out.println("Try to send message");

            SubmitSmResp resp = session.submit(sm, TimeUnit.SECONDS.toMillis(60));

            System.out.println("Message sent");
            Logger.getLogger(SmscSender.class.getName()).log(Level.CONFIG, null, send);
            System.out.println("Wait 10 seconds");

            TimeUnit.SECONDS.sleep(10);
            try {
                responses.setName(resp.getName());
                responses.setCommandId(resp.getCommandId());
                responses.setCommandStatus(resp.getCommandStatus());
                responses.setConnexionSize(client.getConnectionSize());
                responses.setMessageId(resp.getMessageId());
                responses.setResultMessage(resp.getResultMessage());
//                responses.setResponseDate(LocalDateTime.now().toString());

//                operationServices.saveResponse(client.getConnectionSize(), resp, requests);
                logger.info("Operation saved");
            } catch (Exception e) {
                logger.info("Data not save in database");
            }

            System.out.println("Destroy session");
            /**
             * reponse
             */


            session.close();
            session.destroy();

            System.out.println("Destroy client");

            client.destroy();

            System.out.println("Bye!");
        } catch (SmppTimeoutException | UnrecoverablePduException | InterruptedException | RecoverablePduException | SmppChannelException ex) {
            Logger.getLogger(SmscSender.class.getName()).log(Level.SEVERE, null, ex);
        }

        return responses;
    }
/*
  public boolean send(String from, String to, String sms) {
        boolean send = false;

        try {
//            Requests requests = operationServices.saveRequest(from, to, sms, smscConfig.getCharset());
//            logger.info("Request ", requests.getFromParam());
            logger.info("create session final param to {} from {} txt {} ", to, from, sms);
            SmppSession session = client.bind(smscConfig.getSessionConfg());

            SubmitSm sm = createSubmitSm(from, to, sms, smscConfig.getCharset());

            System.out.println("Try to send message");

            SubmitSmResp resp = session.submit(sm, TimeUnit.SECONDS.toMillis(60));
            try {

//                operationServices.saveResponse(client.getConnectionSize(), resp, requests);
                logger.info("Operation saved");
            } catch (Exception e) {
                logger.info("Data not save in database");
            }
            System.out.println("Message sent");
            Logger.getLogger(SmscSender.class.getName()).log(Level.CONFIG, null, send);
            System.out.println("Wait 10 seconds");

            TimeUnit.SECONDS.sleep(10);

            System.out.println("Destroy session");
            */
/**
             * reponse
             *//*



            session.close();
            session.destroy();

            System.out.println("Destroy client");

            client.destroy();

            System.out.println("Bye!");
        } catch (SmppTimeoutException ex) {
            Logger.getLogger(SmscSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SmppChannelException ex) {
            Logger.getLogger(SmscSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SmppBindException ex) {
            Logger.getLogger(SmscSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverablePduException ex) {
            Logger.getLogger(SmscSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SmscSender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RecoverablePduException ex) {
            Logger.getLogger(SmscSender.class.getName()).log(Level.SEVERE, null, ex);
        }

        return send;
    }
*/

    public static SubmitSm createSubmitSm(String src, String dst, String text, String charset) throws SmppInvalidArgumentException {
        SubmitSm sm = new SubmitSm();
        logger.debug("SMS Param: source" + src + " dest:" + dst + " text:" + text + "charset : " + charset);
        // For alpha numeric will use
        // TON=5
        // NPI=0
        sm.setSourceAddress(new Address((byte) 5, (byte) 1, src));

        // For national numbers will use
        // TON=1
        // NPI=1
        sm.setDestAddress(new Address((byte) 1, (byte) 1, dst));

        // Set datacoding to UCS-2
        // sm.setDataCoding((byte)8);

        // Encode text
        sm.setShortMessage(CharsetUtil.encode(text, charset));

        return sm;
    }





  /*  public Requests saveRequest(String src, String dst, String text, String charset) {
        log.info("save request {} ", src);
        requestRepository.findAll().forEach(requests1 -> {
            System.out.println(requests1.getFromParam());
            System.out.println(requests1.getResquestDate());
        });
        Requests requests = new Requests();
        requests.setResquestDate(LocalDateTime.now());
        requests.setFromParam(src);
        requests.setToParam(dst);
        requests.setCharset(charset);
        requests.setTextParam(text);
        log.info("save request 2 {} ", requests);
        return requestRepository.save(requests);
    }


    public Responses saveResponse(int connextionSize, SubmitSmResp smResp, Requests requests) {
        log.info("save response");
        requestRepository.findAll().forEach(requests1 -> {
            System.out.println(requests1.getFromParam());
            System.out.println(requests1.getResquestDate());
        });
        Responses responses = new Responses();
        responses.setName(smResp.getName());
        responses.setCommandId(smResp.getCommandId());
        responses.setCommandStatus(smResp.getCommandStatus());
        responses.setConnexionSize(connextionSize);
        responses.setMessageId(smResp.getMessageId());
        responses.setResultMessage(smResp.getResultMessage());
        responses.setResponseDate(LocalDateTime.now());
        responses.setRequests(requests);
        return responsesRepository.save(responses);
    }

*/
}