package cn.zjnktion.his.server.util;

import cn.zjnktion.his.server.model.Datagram;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zjnktion
 */
public class RequestBean<T> {

    private String socialInsuranceCode;
    private String socialInsurancePsw = "000000";
    private String transTime;
    private String transType;
    private String transVersion;
    private String serialNumber;
    private String cardArea;
    private String hospitalCode;
    private String verifyCode;
    private Map<String, T> params = new HashMap<>();

    public String getSocialInsuranceCode() {
        return socialInsuranceCode;
    }

    public void setSocialInsuranceCode(String socialInsuranceCode) {
        this.socialInsuranceCode = socialInsuranceCode;
    }

    public String getSocialInsurancePsw() {
        return socialInsurancePsw;
    }

    public void setSocialInsurancePsw(String socialInsurancePsw) {
        this.socialInsurancePsw = socialInsurancePsw;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransVersion() {
        return transVersion;
    }

    public void setTransVersion(String transVersion) {
        this.transVersion = transVersion;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCardArea() {
        return cardArea;
    }

    public void setCardArea(String cardArea) {
        this.cardArea = cardArea;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Map<String, T> getParams() {
        return params;
    }

    public void setParams(Map<String, T> params) {
        this.params = params;
    }

    public Datagram con2Basegram(HttpServletRequest request) {
        Datagram datagram = new Datagram();
        datagram.setTransTime(transTime);
        datagram.setTransType(transType);
        datagram.setTransVersion(transVersion);
        datagram.setSerialNumber(serialNumber);
        datagram.setCardArea(cardArea);
        datagram.setHospitalCode(hospitalCode);
        datagram.setVerifyCode(verifyCode);
        // TODO: 2018/5/12 获取登陆的用户信息
        return datagram;
    }
}
