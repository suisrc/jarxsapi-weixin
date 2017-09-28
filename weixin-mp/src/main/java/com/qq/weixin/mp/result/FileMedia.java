package com.qq.weixin.mp.result;

import java.io.InputStream;

import javax.ws.rs.FormParam;

/**
 * 文件内容
 * 
 * form-data中媒体文件标识，有filename、filelength、content-type等信息
 * 
 * @author Y13
 *
 */
public class FileMedia {
    
    /**
     * 文件名字
     */
    @FormParam("filename")
    private String filename;
    
    /**
     * 文件大小
     */
    @FormParam("filelength")
    private Long filelength;
    
    /**
     * 文件内容
     */
    @FormParam("media")
    private InputStream inStream;

    /**
     * 获取文件名字
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 设定文件名字
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 获取文件大小
     * @return the filelength
     */
    public Long getFilelength() {
        return filelength;
    }

    /**
     * 设定文件大小
     * @param filelength the filelength to set
     */
    public void setFilelength(Long filelength) {
        this.filelength = filelength;
    }

    /**
     * 获取文件内容
     * @return the inStream
     */
    public InputStream getInStream() {
        return inStream;
    }

    /**
     * 设定文件内容
     * @param inStream the inStream to set
     */
    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

}
