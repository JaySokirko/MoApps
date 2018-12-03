package com.jay.moappstest.model.request;

public class AppsListRequest {

    private int skip;
    private int take;
    private int osType;
    private String userToken;

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public void setOsType(int osType) {
        this.osType = osType;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
