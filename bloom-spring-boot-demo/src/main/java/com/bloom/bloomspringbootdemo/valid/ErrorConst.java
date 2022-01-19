package com.bloom.bloomspringbootdemo.valid;

/**
 * @author taosy
 * Created by on 2022-01-13 下午2:27
 */
public enum ErrorConst {
                        /** success*/
                        SUCCESS("00000", "成功"),

                        INVALID_PARAM("00001", "非法参数"),

                        SESSION_TIME_OUT("99997", "登录超时，请重新登录"),

                        BIZ_SERVICE_ERROR("99998", "业务端返回错误"),

                        SYSTEM_ERROR("99999", "系统错误");

    private String code;

    private String desc;

    private ErrorConst(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}