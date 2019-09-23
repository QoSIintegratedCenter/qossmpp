package com.ats.qosmpp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "qos_smpp_response")
@Data
public class Responses implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private int  connexionSize;
    private int  commandId;
    private int  commandStatus;
    private String  messageId;
    private String  name;
    private String  resultMessage;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    private LocalDateTime responseDate;
    @OneToOne
    private Requests requests;
}
