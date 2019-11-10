package com.ats.qosmpp.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;


@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="userid")
    private int userId;

    @Column(name="clientid")
    private String clientId;

    @Column(name="companyname")
    private String companyname;

    @Column(name="mtnphonenumber")
    private String mtnphonenumber;

    @Column(name="country")
    private String country;

    @Column(name="email")
    @Email(message="Adresse email incorrecte")
    private String email;

    @Column(name="password")
    private String password;

    @Transient
    private String confirmPassword;
    @Column(name="documenturl")
    private String documentUrl;

  /*  @Column(name="state", nullable=false)
    private String state=State.ACTIVE.getState();*/

    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "roleId") })
    private Set<Role> roles = new HashSet<Role>();*/

    @Column(name="termsandcondition")
    private boolean termsandcondition;

    @Transient
    private int availableAmount;

    @Transient
    private int lastWeekAvailableAmount;

    @Transient
    private int totalRequestPayment;

    @Transient
    private int totalDeposit;

    @Transient
    private int totalRefund;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getMtnphonenumber() {
        return mtnphonenumber;
    }

    public void setMtnphonenumber(String mtnphonenumber) {
        this.mtnphonenumber = mtnphonenumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

/*    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }*/

   /* public Set<Role> getRoles() {
        return roles;
    }*/

    /* public void setRoles(Set<Role> roles) {
         this.roles = roles;
     }
 */
    public boolean isTermsandcondition() {
        return termsandcondition;
    }

    public void setTermsandcondition(boolean termsandcondition) {
        this.termsandcondition = termsandcondition;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public int getLastWeekAvailableAmount() {
        return lastWeekAvailableAmount;
    }

    public void setLastWeekAvailableAmount(int lastWeekAvailableAmount) {
        this.lastWeekAvailableAmount = lastWeekAvailableAmount;
    }

    public int getTotalRequestPayment() {
        return totalRequestPayment;
    }

    public void setTotalRequestPayment(int totalRequestPayment) {
        this.totalRequestPayment = totalRequestPayment;
    }

    public int getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(int totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public int getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(int totalRefund) {
        this.totalRefund = totalRefund;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", clientId='" + clientId + '\'' +
                ", companyname='" + companyname + '\'' +
                ", mtnphonenumber='" + mtnphonenumber + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", documentUrl='" + documentUrl + '\'' +

                ", termsandcondition=" + termsandcondition +
                ", availableAmount=" + availableAmount +
                ", lastWeekAvailableAmount=" + lastWeekAvailableAmount +
                ", totalRequestPayment=" + totalRequestPayment +
                ", totalDeposit=" + totalDeposit +
                ", totalRefund=" + totalRefund +
                '}';
    }

// Getter and Setter
}
