package com.jay.moappstest.model.network.entity.response;

import java.util.Date;

public class AppsList {

    private String applicationToken;
    private boolean isPayment;
    private boolean applicationStatus;
    private String applicationName;
    private Date endOfPayment;
    private String applicationIcoUrl;
    private String applicationUrl;

    public String getApplicationToken() {
        return applicationToken;
    }

    public void setApplicationToken(String applicationToken) {
        this.applicationToken = applicationToken;
    }

    public boolean isPayment() {
        return isPayment;
    }

    public void setPayment(boolean payment) {
        isPayment = payment;
    }

    public boolean isApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(boolean applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }


    public Date getEndOfPayment() {
        return endOfPayment;
    }

    public void setEndOfPayment(Date endOfPayment) {
        this.endOfPayment = endOfPayment;
    }

    public String getApplicationIcoUrl() {
        return applicationIcoUrl;
    }

    public void setApplicationIcoUrl(String applicationIcoUrl) {
        this.applicationIcoUrl = applicationIcoUrl;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}
