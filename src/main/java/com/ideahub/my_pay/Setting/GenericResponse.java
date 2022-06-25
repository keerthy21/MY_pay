package com.ideahub.my_pay.Setting;

public class GenericResponse {
    private boolean isSuccess;
    private String msg;
    private String status;
    private Integer statusCode;
    private Object error;
    private Object data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public GenericResponse setSuccess(boolean success) {
        isSuccess = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public GenericResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public GenericResponse setStatus(String status) {
        this.status = status;
        return this;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public GenericResponse setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Object getError() {
        return error;
    }

    public GenericResponse setError(Object error) {
        this.error = error;
        return this;
    }

    public Object getData() {
        return data;
    }

    public GenericResponse setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "isSuccess=" + isSuccess +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                ", statusCode=" + statusCode +
                ", error=" + error +
                ", data=" + data +
                '}';
    }
    public static GenericResponseBuilder builder(){
        return new GenericResponseBuilder();
    }
}
