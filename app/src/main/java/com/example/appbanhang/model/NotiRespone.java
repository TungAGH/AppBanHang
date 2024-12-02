package com.example.appbanhang.model;

public class NotiRespone {
    private long multicast_id;
    private int success,failure,camonical_ids;

    public NotiRespone() {
    }

    public long getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(long multicast_id) {
        this.multicast_id = multicast_id;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public int getCamonical_ids() {
        return camonical_ids;
    }

    public void setCamonical_ids(int camonical_ids) {
        this.camonical_ids = camonical_ids;
    }
}
