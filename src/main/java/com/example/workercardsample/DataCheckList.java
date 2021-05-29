package com.example.workercardsample;

public class DataCheckList {
    private String email;
    private String checkName;
    private boolean checkFlag;

//    public DataCheckList(String checkName) {
//        this.checkName = checkName;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }
}
