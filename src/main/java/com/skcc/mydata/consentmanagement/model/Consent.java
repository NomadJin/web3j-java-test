package com.skcc.mydata.consentmanagement.model;

import java.math.BigInteger;

public class Consent {

    private String consentId;
    private String userId;

    private String serviceId;
    private String serviceVersion;
    private String dataSinkId;
    private String groupInfos;

    private BigInteger startDtm;
    private BigInteger endDtm;
    private BigInteger registDtm;
    private BigInteger withdrawalDtm;

    public Consent(String consentId, String userId, String serviceId, String serviceVersion, String dataSinkId, String groupInfos,
                   BigInteger startDtm, BigInteger endDtm, BigInteger registDtm, BigInteger withdrawDtm) {
        this.consentId = consentId;
        this.userId = userId;

        this.serviceId = serviceId;
        this.serviceVersion = serviceVersion;
        this.dataSinkId = dataSinkId;
        this.groupInfos = groupInfos;

        this.startDtm = startDtm;
        this.endDtm = endDtm;
        this.registDtm = registDtm;
        this.withdrawalDtm = withdrawDtm;
    }

    public Consent(String consentId, String userId, String serviceId, String serviceVersion, String dataSinkId, String groupInfos,
                   BigInteger startDtm, BigInteger endDtm, BigInteger registDtm) {
        this.consentId = consentId;
        this.userId = userId;

        this.serviceId = serviceId;
        this.serviceVersion = serviceVersion;
        this.dataSinkId = dataSinkId;
        this.groupInfos = groupInfos;

        this.startDtm = startDtm;
        this.endDtm = endDtm;
        this.registDtm = registDtm;
        this.withdrawalDtm = new BigInteger("0");
    }

    public Consent() {

    }

    public BigInteger getWithdrawalDtm() {
        return withdrawalDtm;
    }

    public void setWithdrawalDtm(BigInteger withdrawalDtm) {
        this.withdrawalDtm = withdrawalDtm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConsentId() {
        return consentId;
    }

    public void setConsentId(String consentId) {
        this.consentId = consentId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getDataSinkId() {
        return dataSinkId;
    }

    public void setDataSinkId(String dataSinkId) {
        this.dataSinkId = dataSinkId;
    }

    public String getGroupInfos() {
        return groupInfos;
    }

    public void setGroupInfos(String groupInfos) {
        this.groupInfos = groupInfos;
    }

    public BigInteger getStartDtm() {
        return startDtm;
    }

    public void setStartDtm(BigInteger startDtm) {
        this.startDtm = startDtm;
    }

    public BigInteger getEndDtm() {
        return endDtm;
    }

    public void setEndDtm(BigInteger endDtm) {
        this.endDtm = endDtm;
    }

    public BigInteger getRegistDtm() {
        return registDtm;
    }

    public void setRegistDtm(BigInteger registDtm) {
        this.registDtm = registDtm;
    }



//    private String userId = "0xcfe1b029bf174b4a713f540e19044536c7f36261";
//    private String consentId = "0xcfe1b029bf174b4a713f540e19044536c7f36261";
//    private String serviceId = "1";
//    private String serviceVersion = "2";
//    private String dataSinkId = "3";
//    private String groupInfos = "4";
//    private BigInteger startDtm = new BigInteger("11");
//    private BigInteger endDtm = new BigInteger("12");
//    private BigInteger registDtm = new BigInteger("13");

    //public Strig toStri printConsent()

    @Override
    public String toString() {
        return "Consent{" +
                "userId='" + userId + '\'' +
                ", consentId='" + consentId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", dataSinkId='" + dataSinkId + '\'' +
                ", groupInfos='" + groupInfos + '\'' +
                ", startDtm=" + startDtm +
                ", endDtm=" + endDtm +
                ", registDtm=" + registDtm +
                ", withdrawalDtm=" + withdrawalDtm +
                '}';
    }
}
