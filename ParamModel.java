package com.example.getwithparam;

import com.google.gson.annotations.SerializedName;

public class ParamModel {
    @SerializedName("msg")
    public String msg;
    @SerializedName("status")
    public int status;

    @Override
    public String toString() {
        return "ParamModel{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
