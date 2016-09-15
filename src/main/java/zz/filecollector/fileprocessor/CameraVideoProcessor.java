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
public class CameraVideoProcessor implements FileProcessor {

    public static final String NAME = "CAMERA";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm", Locale.CHINA);
    
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
                name = name.replace("CIMG", "");
                Date date = dateFormat.parse(name.substring(0, 13));
                info.setCreateDate(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     *  CIMG20140124_2109.AVI
     *  20140428_1814.MOV
     */
    @Override
    public boolean accept(File file) {
        String name = file.getName();
        return name.matches("(?m)(CIMG)?\\d{8}_\\d{4}\\.(AVI|MOV)");
    }
}
