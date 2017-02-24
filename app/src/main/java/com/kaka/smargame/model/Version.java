package com.kaka.smargame.model;

/**
 * Created by Jerry on 2015/6/28 0028.
 */
public class Version extends BaseModel {
    private static final int REQUIRED_FORCED = 1;

    private boolean isLasted;
    private String address;
    private String code;
    private String msg;
    private String intro;
    private double versionNum;
    private int required;

    public boolean isForcedUpdate(){
        return required == REQUIRED_FORCED;
    }

    public boolean isUpadte(double currentVersionNum){
        return currentVersionNum < versionNum;
    }

    public void setIsLasted(boolean isLasted) {
        this.isLasted = isLasted;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setVersionNum(double versionNum) {
        this.versionNum = versionNum;
    }

    public boolean isIsLasted() {
        return isLasted;
    }

    public String getAddress() {
        return address;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getIntro() {
        return intro;
    }

    public double getVersionNum() {
        return versionNum;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }
}
