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
public class MeizuPhotoProcessor implements FileProcessor {

    public static final String NAME = "MEIZU";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA);
    
    @Override
    public void extractFileInfo(File file, FileInfo info) {
        FileProcessor.getBasicFileInfo(file, info);
        if(info.getDevice() == null) {
            info.setDevice(NAME);
        }
        if(info.getFileType() == FileInfo.UNKNOWN) {
            info.setFileType(FileInfo.PHOTO);
        }
        
        if(info.getCreateDate() == null) {
            try {
                String name = file.getName();
                String timeStr = "201" + name.substring(1, 13);
                Date date = dateFormat.parse(timeStr);
                info.setCreateDate(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     *  P51222-205940.jpg
     *  P51222-205940 (1).jpg
     *  P50828-174826_1.jpg
     */
    @Override
    public boolean accept(File file) {
        String name = file.getName();
        return name.matches("(?m)P\\d{5}-\\d{6}(\\D.*)?\\.(jpg|JPG)");
    }
}
