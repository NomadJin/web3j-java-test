package com.skcc.mydata.consentmanagement.service;

import com.skcc.mydata.consentmanagement.model.Consent;


import java.math.BigInteger;
import java.util.List;

public interface ConsentService {
    public abstract void addConsents(List<Consent> consents);
    public abstract void updateConsents(List<Consent> consents);
    public abstract void withdrawConsent(String consentId, BigInteger withdrawalDtm);
    public abstract Consent getConsent(String consentId);
}
