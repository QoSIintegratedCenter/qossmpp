package com.ats.qosmpp.service;

import com.ats.qosmpp.core.SmscSender;
import com.ats.qosmpp.domain.Requests;
import com.ats.qosmpp.domain.Responses;
import com.ats.qosmpp.repository.RequestRepository;
import com.ats.qosmpp.repository.ResponsesRepository;
import com.cloudhopper.smpp.pdu.SubmitSmResp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OperationServices {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(SmscSender.class);
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ResponsesRepository responsesRepository;

  /*  @Autowired
    public OperationServices(RequestRepository requestRepository, ResponsesRepository responsesRepository) {
        this.requestRepository = requestRepository;
        this.responsesRepository = responsesRepository;
    }*/

    public  Requests saveRequest(String src, String dst, String text, String charset) {
        log.info("save request {} ", src);
        Requests requests = new Requests();
        requests.setResquestDate(LocalDateTime.now());
        requests.setFromParam(src);
        requests.setToParam(dst);
        requests.setTextParam(text);
        requests.setCharset(charset);
        log.info("save request 2 {} ", requests);
        return requestRepository.save(requests);
    }


 /*   public Responses saveResponse(int connextionSize, SubmitSmResp smResp, Requests requests) {
        log.info("save response");
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
    public  Responses add(Responses responses){
        log.info("save Response {} ", responses);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        responses.setResponseDate(LocalDateTime.now());
        return responsesRepository.save(responses);
    }
}
