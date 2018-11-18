package com.java.smart.enums;

public enum ResultCode {

    susscess("000000","交易成功"),
    failed("999999","交易失败"),
    NotFoundMEssage("900004","未查询到记录!"),
    FiledIsNull("900005","必输字段未输入"),


    //用户校验错误信息,
    LoginFailed("200001","登录失败，请确认用户或密码是否正确"),
    NotFountUser("200002","未找到用户信息"),
    PasswordWrong("200003","密码错误！"),

    //数据库错误信息
    DeleteFailed("100005","删除失败!"),
    SelectFailed("100006","查询失败!"),
    UpdateFailed("100006","修改失败!"),
    InsertFailed("100007","插入失败!"),
    InsertFailedWithRepeat("100008","唯一字段重复!"),
    SqlException("100009","数据库操作异常");



    private String code;

    private String msg;

    private ResultCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString(){
        return this.code;
    }
}
