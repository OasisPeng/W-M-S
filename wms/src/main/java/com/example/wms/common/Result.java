package com.example.wms.common;

import lombok.Data;

//后端返回的数据封装,List<user>->Result
@Data
public class Result {
    private int code;//编码 200/400
    private String msg;//成功/失败
    private Long total;//
    private Object data;

    public static Result fail(){
        return result(400,"失败",0L,null);
    }
    public static Result suc(Long total,Object data){
        return result(200,"成功",total,data);
    }
    private static Result result(int code,String msg,Long total,Object data){//相当于构造器
        Result res=new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        res.setData(data);
        return res;
    }
}
