/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector.fileprocessor;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import zz.filecollector.FileInfo;

/**
 *
 * @author zhan
 */
public class NormalizedFileProcessor implements FileProcessor {

    public static final String NAME = "Archived";
    
    @Override
    public FileInfo extractFileInfo(File file) {
        FileInfo info = new FileInfo();
        info.setDevice(NAME);
        String name = file.getName();
        
        int year = Integer.parseInt(name.substring(0,4));
        info.setYear(year);
        
        int month = Integer.parseInt(name.substring(4,6));
        info.setMonth(month);
        
        int date = Integer.parseInt(name.substring(6,8));
        info.setDate(date);
        
        String timeStr = name.substring(9,15);
        info.setTimeStr(timeStr);
        
        info.setSize(FileUtils.sizeOf(file));
        String extension = FilenameUtils.getExtension(name).toLowerCase();
        info.setExtension(extension);
        switch(extension) {
            case "jpg":
            case "jpeg":
                info.setFileType(FileInfo.PHOTO);
                break;
            case "mp4":
            case "avi":
                info.setFileType(FileInfo.VIDEO);
                break;
        }
        
        info.setDupIndex(FileProcessor.getDupIndex(name));
        return info;
    }

    /**
     *   20151108-105510.jpg
     *   20151108-105510 (1).jpg
     *   20151108-105510.mp4
     */
    @Override
    public boolean applies(File file) {
        String name = file.getName();
        return name.matches("(?m)\\d{8}-\\d{6}(\\s\\(\\d+\\))?\\.\\w+");
    }
    
}
