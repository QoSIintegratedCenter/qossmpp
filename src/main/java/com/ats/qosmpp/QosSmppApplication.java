package com.ats.qosmpp;

import com.ats.qosmpp.domain.Requests;
import com.ats.qosmpp.domain.Responses;
import com.ats.qosmpp.repository.RequestRepository;
import com.ats.qosmpp.repository.ResponsesRepository;
import com.ats.qosmpp.service.OperationServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
//@EnableAutoConfiguration
//@EnableConfigurationProperties
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
public class QosSmppApplication extends SpringBootServletInitializer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(QosSmppApplication.class);
    private static ApplicationContext applicationContext = null;
    @Autowired
    private ObjectMapper objectMapper;
 /*   @Autowired
    @Lazy
    private RequestRepository requestRepository;
    @Autowired
    @Lazy
    private ResponsesRepository responsesRepository;
    @Autowired*/
 @Autowired
 @Lazy
    private OperationServices operationServices;

    public static void main(String[] args) {
        String mode = args != null && args.length > 0 ? args[0] : null;

        if (logger.isDebugEnabled()) {
            logger.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application mode:" + mode + " context:" + applicationContext);
        }
        if (applicationContext != null && mode != null && "stop".equals(mode)) {
            System.exit(SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
                @Override
                public int getExitCode() {
                    return 0;
                }
            }));
        } else {
            SpringApplication app = new SpringApplication(QosSmppApplication.class);
            applicationContext = app.run(args);
            if (logger.isDebugEnabled()) {
                logger.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application started context:" + applicationContext);
            }
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(QosSmppApplication.class);
    }

    @Bean(value = "datasource")
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Override
    public void run(String... strings) throws Exception {
//        requestRepository.deleteAll();
        List<Requests> requestsList = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            Requests requests = new Requests();
            requests.setCharset("GSM");
            requests.setFromParam("+229" + i);
            requests.setToParam("668" + i);
            requests.setResquestDate(LocalDateTime.now());
            Responses responses = new Responses();
            responses.setCommandId(i);
            responses.setCommandStatus(20+i);
            responses.setMessageId("ok"+i);
//            responses.setResponseDate(LocalDateTime.now().toString());

//            responses.setRequests(requestRepository.save(requests));
            requestsList.add(requests);
//            responsesRepository.save(responses);

        }
//        operationServices.saveRequest("test","ten","ddd", "ddd");
//        requestRepository.save(requestsList);
     /*   requestRepository.findAll().forEach(requests1 -> {
            System.out.println(requests1.getFromParam());
            System.out.println(requests1.getResquestDate());
        });*/
    }

    @PostConstruct
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }
}
