package com.example.demo.transcript;

import lombok.Builder;

@Builder
public class BanksTranscript {

    private String[] code;
    private String[] bankName;
    private String[] longCode;

    public String[] getCode() {
        return code;
    }

    public void setCode(String[] code) {
        this.code = code;
    }

    public String[] getBankName() {
        return bankName;
    }

    public void setBankName(String[] bankName) {
        this.bankName = bankName;
    }

    public String[] getLongCode() {
        return longCode;
    }

    public void setLongCode(String[] longCode) {
        this.longCode = longCode;
    }

}
