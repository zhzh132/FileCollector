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
public class FaceUVideoProcessor implements FileProcessor {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
    
    @Override
    public void extractFileInfo(File file, FileInfo info) {
        FileProcessor.getBasicFileInfo(file, info);
        if(info.getFileType() == FileInfo.UNKNOWN) {
            info.setFileType(FileInfo.VIDEO);
        }
        
        if(info.getCreateDate() == null) {
            try {
                String name = file.getName();
                Date date = dateFormat.parse(name.substring(8, 22));
                info.setCreateDate(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     *  faceu_0_20181224114454.mp4
     */
    @Override
    public boolean accept(File file) {
        String name = file.getName();
        return name.matches("(?m)faceu_0_\\d{14}\\.mp4");
    }
}
