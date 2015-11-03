package com.cmcc.syw.enums;

/**
 * Created by sunyiwei on 2015/11/3.
 */
public enum Gender {
    MALE("M"),
    FEMALE("F");

    private Gender(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static Gender fromCode(String code){
        for (Gender gender : Gender.values()) {
            if(gender.getCode().equalsIgnoreCase(code)){
                return gender;
            }
        }

        return null;
    }
}
