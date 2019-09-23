package com.ats.qosmpp.web;


import com.ats.qosmpp.domain.Requests;
import com.ats.qosmpp.domain.Responses;
import com.ats.qosmpp.repository.data.RequestDataRepository;
import com.ats.qosmpp.repository.data.ResponseDataRepository;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AdminRessource {

    private RequestDataRepository requestDataRepository;
    private ResponseDataRepository responseDataRepository;

    public AdminRessource(RequestDataRepository requestDataRepository, ResponseDataRepository responseDataRepository) {
        this.requestDataRepository = requestDataRepository;
        this.responseDataRepository = responseDataRepository;
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public DataTablesOutput<Requests> list(@Valid DataTablesInput input) {
        return requestDataRepository.findAll(input);
    }

    @RequestMapping(value = "/responses", method = RequestMethod.POST)
    public DataTablesOutput<Responses> listPOST(@Valid @RequestBody DataTablesInput input) {
        return responseDataRepository.findAll(input);
    }

}
