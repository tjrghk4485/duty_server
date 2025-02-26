package com.duty_b.duty_server.model;

import lombok.Data;

import java.util.Date;

@Data
public class Nurse {

    private String parentId;  // 사용자 ID
    private String nurseId;  // 사용자 이름
    private String nurseNm;  // 사용자 이메일
    private String startDate;  // 사용자 비밀번호
    private Integer shifyTy;
    private Date regDtm;
    private Date updDtm;

    // 기본 생성자 (필수는 아니지만 필요할 수 있음)
    public Nurse() {}

    public Nurse(String parentId, String nurseId, String nurseNm, String startDate, Integer shifyTy, Date regDtm, Date updDtm) {
        this.parentId = parentId;
        this.nurseId = nurseId;
        this.nurseNm = nurseNm;
        this.startDate = startDate;
        this.shifyTy = shifyTy;
        this.regDtm = regDtm;
        this.updDtm = updDtm;
    }

    public String getparentId() {
        return parentId;
    }

    public void setparentId(String parentId) {
        this.parentId = parentId;
    }

    public String getnurseId() {
        return nurseId;
    }

    public void setnurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getnurseNm() {
        return nurseNm;
    }

    public void setnurseNm(String nurseNm) {
        this.nurseNm = nurseNm;
    }

    public String getstartDate() {
        return startDate;
    }

    public void setstartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getshifyTy() {
        return shifyTy;
    }

    public void setshifyTy(Integer shifyTy) {
        this.shifyTy = shifyTy;
    }

    public Date getregDtm() {
        return regDtm;
    }

    public void setregDtm(Date regDtm) {
        this.regDtm = regDtm;
    }

    public Date getupdDtm() {
        return updDtm;
    }

    public void setupdDtm(Date updDtm) {
        this.updDtm = updDtm;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "parentId='" + parentId + '\'' +
                ", nurseId='" + nurseId + '\'' +
                ", nurseNm='" + nurseNm + '\'' +
                ", startDate='" + startDate + '\'' +
                ", shifyTy=" + shifyTy +
                ", regDtm='" + regDtm + '\'' +
                ", updDtm='" + updDtm + '\'' +
                '}';
    }
}



