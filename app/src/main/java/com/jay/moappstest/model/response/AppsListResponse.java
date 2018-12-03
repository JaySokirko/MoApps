package com.jay.moappstest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppsListResponse {

    @SerializedName("data")
    @Expose
    private List<AppsList> data = null;

    public List<AppsList> getData() {
        return data;
    }

    public void setData(List<AppsList> data) {
        this.data = data;
    }
}
