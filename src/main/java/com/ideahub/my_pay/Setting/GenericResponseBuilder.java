package com.ideahub.my_pay.Setting;

public class GenericResponseBuilder {
    private boolean isSuccess;
    private String msg;
    private String status;
    private Integer statusCode;
    private Object error;
    private Object data;

    public GenericResponseBuilder isSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
        return this;
    }

    public GenericResponseBuilder msg(String msg) {
        this.msg = msg;
        return this;
    }

    public GenericResponseBuilder status(String status) {
        this.status = status;
        return this;
    }

    public GenericResponseBuilder statusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public GenericResponseBuilder error(Object error) {
        this.error = error;
        return this;
    }

    public GenericResponseBuilder data(Object data) {
        this.data = data;
        return this;
    }

    public GenericResponse build() {
        GenericResponse response = new GenericResponse();
        response.setSuccess(isSuccess);
        response.setMsg(msg);
        response.setStatusCode(statusCode);
        response.setStatus(status);
        response.setError(error);
        response.setData(data);
        return response;
    }

}
