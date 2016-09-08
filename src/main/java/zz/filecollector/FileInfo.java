/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author zhan
 */
public class FileInfo {
    public static final int PHOTO = 1;
    public static final int VIDEO = 2;
    
    private int year;
    private int month;
    private int date;
    private String timeStr;
    private long size;
    private String extension;
    private int dupIndex;
    private int fileType;
    
    private String device;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getDupIndex() {
        return dupIndex;
    }

    public void setDupIndex(int dupIndex) {
        this.dupIndex = dupIndex;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
    /**
     *  yyyyMMdd-hhmmss (1).jpg
     * @return 
     */
    public String getNormalizedName() {
        StringBuilder name = new StringBuilder();
        String baseName = String.format("%d%02d%02d-%s", this.getYear(), this.getMonth(), this.getDate(), 
                this.getTimeStr());
        name.append(baseName);
        if(this.getDupIndex() > 0) {
            name.append(" (").append(this.getDupIndex()).append(")");
        }
        name.append(".").append(this.getExtension());
        return name.toString();
    }
    
    public boolean isSameFile(FileInfo info) {
        return this.getSize() == info.getSize() &&
                this.getTimeStr().equals(info.getTimeStr()) &&
                this.getDate() == info.getDate() &&
                this.getMonth() == info.getMonth() &&
                this.getYear() == info.getYear();
    }
}
