package com.example.twinlineTask.dto;

public class ResponseDto {

    private String status;
    private Object data;
    private String statusMessage;


    public ResponseDto(String status, Object data, String message) {
        this.status = status;
        this.data = data;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
