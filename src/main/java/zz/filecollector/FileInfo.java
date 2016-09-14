/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author zhan
 */
public class FileInfo {
    public static final int PHOTO = 1;
    public static final int VIDEO = 2;
    public static final int UNKNOWN = 0;
    
    private long size;
    private String extension;
    private int dupIndex;
    private int fileType = UNKNOWN;
    private Date createDate;
    private Calendar calendar;
    private String device;

    public int getYear() {
        return this.calendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return this.calendar.get(Calendar.MONTH) + 1;
    }

    public int getDate() {
        return this.calendar.get(Calendar.DAY_OF_MONTH);
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
        this.calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"), Locale.CHINA);
        this.calendar.setTime(createDate);
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA);
    
    /**
     *  yyyyMMdd-HHmmss (1).jpg
     * @return 
     */
    public String getNormalizedName() {
        StringBuilder name = new StringBuilder();
        name.append(DATE_FORMAT.format(createDate));
        if(this.getDupIndex() > 0) {
            name.append(" (").append(this.getDupIndex()).append(")");
        }
        name.append(".").append(this.getExtension());
        return name.toString();
    }
    
    public boolean isSameFile(FileInfo info) {
        return this.getSize() == info.getSize() &&
                this.getCreateDate().equals(info.getCreateDate());
    }
    
    public boolean isValid() {
        return this.fileType != UNKNOWN && 
                this.createDate != null &&
                this.calendar != null;
    }
}
