package com.shyam.dto;

public class Result {
    private String hi;
    private String it;
    private String ta;
    private String te;
    private String fr;

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getTa() {
        return ta;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public String getTe() {
        return te;
    }

    public void setTe(String te) {
        this.te = te;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    @Override
    public String toString() {
        return "Result [hi=" + hi + ", it=" + it + ", ta=" + ta + ", te=" + te + ", fr=" + fr + "]";
    }
}
