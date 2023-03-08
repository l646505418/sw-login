package com.schedulework.login.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:Li Jinming
 * @Description:
 * @date:2023-02-13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class backResponse {
    private  long code;
    private String Message;
    private Object object;
    public  static backResponse getResponse(responseEnum responseEnum,Object object){
        return new backResponse(responseEnum.getStatusCode(),responseEnum.getStatusDescription(), object);
    }
    public  static backResponse getResponse(responseEnum responseEnum){
        return new backResponse(responseEnum.getStatusCode(),responseEnum.getStatusDescription(), null);
    }
}
