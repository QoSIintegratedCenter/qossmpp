package com.ats.qosmpp.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="qos_smpp_users")
public class Users_Profile implements Serializable {

    @Id
    private int id;
    private String username;
    private String password;
    private String ipaddress;
    private String msisdn;
    private String trxnkey;
    private String email;
    private String name;
    private String status;
    private String dateofregistration;
    private String sp_account;
    private String sp_password;
    private String serviceid;
    private String callback_url;
    private String class_of_service;
    private String allowedoperations;
    private String country;
    private boolean state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getTrxnkey() {
        return trxnkey;
    }

    public void setTrxnkey(String trxnkey) {
        this.trxnkey = trxnkey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateofregistration() {
        return dateofregistration;
    }

    public void setDateofregistration(String dateofregistration) {
        this.dateofregistration = dateofregistration;
    }

    public String getSp_account() {
        return sp_account;
    }

    public void setSp_account(String sp_account) {
        this.sp_account = sp_account;
    }

    public String getSp_password() {
        return sp_password;
    }

    public void setSp_password(String sp_password) {
        this.sp_password = sp_password;
    }

    public String getServiceid() {
        return serviceid;
    }

    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getClass_of_service() {
        return class_of_service;
    }

    public void setClass_of_service(String class_of_service) {
        this.class_of_service = class_of_service;
    }

    public String getAllowedoperations() {
        return allowedoperations;
    }

    public void setAllowedoperations(String allowedoperations) {
        this.allowedoperations = allowedoperations;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}