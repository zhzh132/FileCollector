/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector.fileprocessor;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import zz.filecollector.FileInfo;

/**
 *
 * @author zhan
 */
public class NormalizedFileProcessor implements FileProcessor {

    public static final String NAME = "Archived";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA);
    
    @Override
    public FileInfo extractFileInfo(File file) {
        FileInfo info = new FileInfo();
        info.setDevice(NAME);
        String name = file.getName();
        
        try {
            Date date = dateFormat.parse(name.substring(0, 15));
            info.setCreateDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
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
