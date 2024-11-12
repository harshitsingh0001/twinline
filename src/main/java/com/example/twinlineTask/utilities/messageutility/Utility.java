package com.example.twinlineTask.utilities.messageutility;

import com.example.twinlineTask.dto.ResponseDto;

public class Utility {

    public static ResponseDto createSuccessResponse(String message, Object data) {
        return new ResponseDto("success", data, message);
    }

    public static ResponseDto createFailureResponse(String message, Object data) {
        return new ResponseDto("failure", data, message);
    }

}
