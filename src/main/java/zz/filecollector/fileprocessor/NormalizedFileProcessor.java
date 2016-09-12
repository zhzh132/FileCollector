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
import zz.filecollector.FileInfo;

/**
 *
 * @author zhan
 */
public class NormalizedFileProcessor implements FileProcessor {

    public static final String NAME = "Archived";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA);
    
    @Override
    public void extractFileInfo(File file, FileInfo info) {
        FileProcessor.getBasicFileInfo(file, info);
        if(info.getDevice() == null) {
            info.setDevice(NAME);
        }
        if(info.getFileType() == FileInfo.UNKNOWN) {
            String extension = info.getExtension();
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
        }
        
        if(info.getCreateDate() == null) {
            try {
                String name = file.getName();
                Date date = dateFormat.parse(name.substring(0, 15));
                info.setCreateDate(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
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
