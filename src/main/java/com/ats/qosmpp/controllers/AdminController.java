package com.ats.qosmpp.controllers;

import com.ats.qosmpp.domain.Requests;
import com.ats.qosmpp.domain.Responses;
import com.ats.qosmpp.repository.RequestRepository;
import com.ats.qosmpp.repository.ResponsesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
public class AdminController {
    private RequestRepository requestRepository;
    private ResponsesRepository responsesRepository;
    private static final int PAGE_SIZE = 15;
    @Autowired
    private Environment environment;

    public AdminController(RequestRepository requestRepository, ResponsesRepository responsesRepository) {
        this.requestRepository = requestRepository;
        this.responsesRepository = responsesRepository;
    }
    @RequestMapping("/t")
    public String index() {
        return "dashboard/index";
    }

    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("smsHost", getProp("smpp.host"));
        model.addAttribute("smsPort", getProp("smpp.ports"));
        model.addAttribute("smsUser", getProp("smpp.users"));
        model.addAttribute("smsCharset", getProp("smpp.charset"));
        model.addAttribute("dbUrl", getProp("spring.datasource.url"));
        model.addAttribute("dbUserName", getProp("spring.datasource.username"));
        return "dashboard/index";
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public String list() {
        /*int pageNumber = 0;
        Page<Requests> page = null;
        if (request.getParameter("pageNumber") != null && !request.getParameter("pageNumber").isEmpty()) {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber")) - 1;
        }

        if (request.getParameter("fromResponse") != null && !request.getParameter("fromResponse").isEmpty()) {
            pageNumber = Integer.parseInt(request.getParameter("fromResponse")) ;
            page = (Page<Requests>) requestRepository.findById( Long.parseLong(request.getParameter("fromResponse")), new PageRequest(pageNumber, PAGE_SIZE));
        }else {
            page = requestRepository.findAll(new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.DESC, "id"));
        }


        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = 1;
        if (page.getTotalPages() != 0) {
            end = Math.min(begin + 10, page.getTotalPages());
        }

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);*/

        return "requests/list";

    }

    @RequestMapping(value = "/responses", method = RequestMethod.GET)
    public String response(HttpServletRequest request, Model model) {
        int pageNumber = 0;
        if (request.getParameter("pageNumber") != null && !request.getParameter("pageNumber").isEmpty()) {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber")) - 1;
        }
        Page<Responses> page = responsesRepository.findAll(new PageRequest(pageNumber, PAGE_SIZE, Sort.Direction.DESC, "id"));

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = 1;
        if (page.getTotalPages() != 0) {
            end = Math.min(begin + 10, page.getTotalPages());
        }

        System.out.println(" be" + begin + "\t end " + end + "\t p" + page);
        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "responses/list";

    }

    public String getProp(String prop) {
        return environment.getProperty(prop) != null ? environment.getProperty(prop) : "";
    }
    @RequestMapping(value="/logfile/download", method=RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadFile() {
//        Product product = productRepo.findOne(id);
        return new FileSystemResource(new File(getProp("logging.file")));
    }

}
