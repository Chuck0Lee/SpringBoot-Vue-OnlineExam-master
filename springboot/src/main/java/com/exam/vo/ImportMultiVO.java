package com.exam.vo;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


@Data
public class ImportMultiVO {
    // 自定义状态码
    private Integer status;
    // 自定义响应消息内容
    private String msg;
    // 自定义返回的对象
    private Object obj;

    public static ImportMultiVO build() {
        return new ImportMultiVO();
    }

    // 响应成功返回 200 正确消息 msg
    public static ImportMultiVO ok(String msg) {
        return new ImportMultiVO(200,msg,null);
    }

    // 相应成功返回 200 正确消息 msg 以及要返回的对象 obj
    public static ImportMultiVO ok(String msg,Object obj) {
        return new ImportMultiVO(200,msg,obj);
    }

    // 响应成功返回 500 错误消息 msg
    public static ImportMultiVO error(String msg) {
        return new ImportMultiVO(500,msg,null);
    }

    // 相应成功返回 500 错误消息 msg 以及要返回的对象 obj
    public static ImportMultiVO error(String msg,Object obj) {
        return new ImportMultiVO(500,msg,obj);
    }

    private ImportMultiVO() {

    }

    private ImportMultiVO(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public ImportMultiVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ImportMultiVO setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public ImportMultiVO setObj(Object obj) {
        this.obj = obj;
        return this;
    }

}
