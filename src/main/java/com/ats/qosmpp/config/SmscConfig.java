package com.ats.qosmpp.config;

import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


//@ConfigurationProperties()
@Component
//@PropertySource("file:conf/application.yml")
//@PropertySource("file:../conf/application.yml")
public class SmscConfig {

    @Value("${smpp.host}")
    private String hostAdresse;
    @Value("${smpp.ports}")
    private String port = "120";
    @Value("${smpp.users}")
    private String users;
    @Value("${smpp.password}")
    private String passeword;
    @Value("${smpp.charset}")
    private String charset;

    SmppSessionConfiguration sessionConfig = new SmppSessionConfiguration();
    public static SmscConfig instance = new SmscConfig();

    public SmscConfig() {
    }

    public static SmscConfig getInstance(){
        if(instance == null){
            return new SmscConfig();
        }
        return instance;
    }
    @Autowired
    public static void setInstance(SmscConfig instance) {
        SmscConfig.instance = instance;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getHostAdresse() {
        return hostAdresse;
    }

    public void setHostAdresse(String hostAdresse) {
        this.hostAdresse = hostAdresse;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String sytemId) {
        this.users = sytemId;
    }

    public String getPasseword() {
        return passeword;
    }

    public void setPasseword(String passeword) {
        this.passeword = passeword;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SmscConfig{");
        sb.append("hostAdresse='").append(hostAdresse).append('\'');
        sb.append(", port=").append(port);
        sb.append(", Users='").append(users).append('\'');
        sb.append(", passeword='").append(passeword).append('\'');
        sb.append(", charset='").append(charset).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static SmscConfig getSmsConfig(){
        SmscConfig  config = new SmscConfig();
//        config.setHostAdresse();//
        return config;

    }

    // session config configurer la session
    public  SmppSessionConfiguration getSessionConfg(){
        System.out.println(toString());
        sessionConfig.setType(SmppBindType.TRANSCEIVER);
        sessionConfig.setHost(hostAdresse);
        sessionConfig.setSystemId(users);
        sessionConfig.setPort(Integer.parseInt(port));
        sessionConfig.setPassword(passeword);
        return sessionConfig;
    }

}
