package com.ats.qosmpp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "qos_smpp_request")
public class Requests implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Converter(autoApply = true)
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    public LocalDateTime resquestDate;
    private String fromParam;
    private String toParam;
    private String textParam;
    private String charset;

    public Requests() {
    }

    public Requests(LocalDateTime resquestDate, String fromParam, String toParam, String textParam, String charset) {
        this.resquestDate = resquestDate;
        this.fromParam = fromParam;
        this.toParam = toParam;
        this.textParam = textParam;
        this.charset = charset;
    }

    @Override
    public String toString() {
        return "Requests{" +
                "id=" + id +
                ", fromParam='" + fromParam + '\'' +
                ", toParam='" + toParam + '\'' +
                ", textParam='" + textParam + '\'' +
                ", charset='" + charset + '\'' +
                '}';
    }
}
