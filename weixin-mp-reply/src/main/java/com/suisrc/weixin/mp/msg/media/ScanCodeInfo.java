package com.suisrc.weixin.mp.msg.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 扫描信息
 * <ScanCodeInfo>
 *   <ScanType><![CDATA[qrcode]]></ScanType>
 *   <ScanResult><![CDATA[1]]></ScanResult>
 * </ScanCodeInfo>
 * @author Y13
 *
 */
public class ScanCodeInfo {

    /**
     * 扫描类型，一般是qrcode
     * <ScanType><![CDATA[qrcode]]></ScanType>
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "ScanType")
    @JsonProperty("ScanType")
    private String scanType;

    /**
     * 扫描结果，即二维码对应的字符串信息
     * <ScanResult><![CDATA[1]]></ScanResult>
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "ScanResult")
    @JsonProperty("ScanResult")
    private String scanResult;

    /**
     * 获取扫描类型，一般是qrcode <ScanType><![CDATA[qrcode]]>< ScanType>
     * @return the scanType
     */
    public String getScanType() {
        return scanType;
    }

    /**
     * 设定扫描类型，一般是qrcode <ScanType><![CDATA[qrcode]]>< ScanType>
     * @param scanType the scanType to set
     */
    public void setScanType(String scanType) {
        this.scanType = scanType;
    }

    /**
     * 获取扫描结果，即二维码对应的字符串信息 <ScanResult><![CDATA[1]]>< ScanResult>
     * @return the scanResult
     */
    public String getScanResult() {
        return scanResult;
    }

    /**
     * 设定扫描结果，即二维码对应的字符串信息 <ScanResult><![CDATA[1]]>< ScanResult>
     * @param scanResult the scanResult to set
     */
    public void setScanResult(String scanResult) {
        this.scanResult = scanResult;
    }
    
}
