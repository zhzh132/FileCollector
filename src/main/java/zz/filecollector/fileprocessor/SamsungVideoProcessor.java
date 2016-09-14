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
public class SamsungVideoProcessor implements FileProcessor {
    
    public static final String NAME = "SAMSUNG";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);

    @Override
    public void extractFileInfo(File file, FileInfo info) {
        FileProcessor.getBasicFileInfo(file, info);
        if(info.getDevice() == null) {
            info.setDevice(NAME);
        }
        if(info.getFileType() == FileInfo.UNKNOWN) {
            info.setFileType(FileInfo.VIDEO);
        }
        
        if(info.getCreateDate() == null) {
            try {
                String name = file.getName();
                Date date = dateFormat.parse(name.substring(6, 25));
                info.setCreateDate(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     *  video-2014-05-25-14-35-59.mp4
     */
    @Override
    public boolean accept(File file) {
        String name = file.getName();
        return name.matches("(?m)video-\\d{4}(-\\d{2}){5}\\.mp4");
    }
    
}
