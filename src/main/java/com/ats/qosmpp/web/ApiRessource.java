package com.ats.qosmpp.web;

import com.ats.qosmpp.config.SmscConfig;
import com.ats.qosmpp.core.SmscSender;
import com.ats.qosmpp.domain.Requests;
import com.ats.qosmpp.domain.Responses;
import com.ats.qosmpp.domain.Users_Profile;
import com.ats.qosmpp.repository.UserProfileRepository;
import com.ats.qosmpp.service.OperationServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ApiRessource {
    @Autowired
    private UserProfileRepository userProfileRepository;
    private static final Logger logger = LoggerFactory.getLogger(ApiRessource.class);
    private SmscConfig smscConfig;
    @Autowired
    @Lazy
    private final OperationServices operationServices;

    public ApiRessource(OperationServices operationServices) {
        this.operationServices = operationServices;
    }

    @Autowired
    public void setSmscConfig(SmscConfig smscConfig) {
        this.smscConfig = smscConfig;
    }

    @GetMapping("sender")
    public String sendSms(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("text") String text) {
        logger.info("Parameter sent with Get from {} to {} text {} ", from, to, text);
        SmscSender sender = new SmscSender(smscConfig);
        Requests requests = null;
        Responses result = null;
        if (getTxkey().isEmpty()) {
            requests = operationServices.saveRequest(from, to, text, smscConfig.getCharset());
            result = sender.send(from, to, text);
        } else {
            requests = operationServices.saveRequest(getTxkey(), to, text, smscConfig.getCharset());
            result = sender.send(getTxkey(), to, text);
        }
        result.setRequests(requests);
        operationServices.add(result);
        return "success";
    }

    @GetMapping("test")
    public String test() {

        String app = smscConfig.toString();

        return "test\t" + app + "\n" + getTxkey();
    }

    @PostMapping("sender")
    public String sendSmsPost(@RequestBody SmsParam smsParam) {
        logger.info("Parameter sent Post  from {} to {} text {} ", smsParam.getFrom(), smsParam.getTo(), smsParam.getText());
        Requests requests = null;
        Responses result = null;
        SmscSender sender = new SmscSender(smscConfig);
        if (getTxkey().isEmpty()) {
            requests = operationServices.saveRequest(smsParam.getFrom(), smsParam.getTo(), smsParam.getText(), smscConfig.getCharset());
            result = sender.send(smsParam.getFrom(), smsParam.getTo(), smsParam.getText());
        } else {
            requests = operationServices.saveRequest(getTxkey(), smsParam.getTo(), smsParam.getText(), smscConfig.getCharset());
            result = sender.send(getTxkey(), smsParam.getTo(), smsParam.getText());
        }
        result.setRequests(requests);
        operationServices.add(result);
        return "success";
    }

    String getTxkey() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Users_Profile users_profile = userProfileRepository.findByUsername(currentPrincipalName);
        logger.info("get Trxnkey {} ", users_profile.getTrxnkey());
        if (users_profile != null) {
            return users_profile.getTrxnkey();
        } else return "";
    }

}

class SmsParam {
    private String from;
    private String to;
    private String text;

    public SmsParam() {
    }

    @Override
    public String toString() {
        return "SmsParam{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public SmsParam(String from, String to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
