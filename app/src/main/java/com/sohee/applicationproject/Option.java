package com.sohee.applicationproject;

import java.io.Serializable;

class Option implements Serializable {

    String phone;
    String addr;

    public Option() {

    }
    public Option(String phone, String addr) {
        this.phone = phone;
        this.addr = addr;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone){
        this.phone = phone;
    }

    public String getAddr () {
        return addr;
    }

    public void setAddr (String addr){
        this.addr = addr;
    }
}