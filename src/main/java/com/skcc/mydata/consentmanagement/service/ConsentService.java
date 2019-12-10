package com.skcc.mydata.consentmanagement.service;

import com.skcc.mydata.consentmanagement.model.Consent;

import java.util.Collection;
import java.util.List;

public interface ConsentService {
    public abstract void addConsents(List<Consent> consents);
    public abstract void updateConsents();
    public abstract void withdrawConsents();
    public abstract Consent getConsent(String userId, String consentId);
}
