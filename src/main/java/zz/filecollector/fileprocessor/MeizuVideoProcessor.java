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
public class MeizuVideoProcessor implements FileProcessor {

    public static final String NAME = "MEIZU";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA);
    
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
                String timeStr = "201" + name.substring(1, 13);
                Date date = dateFormat.parse(timeStr);
                info.setCreateDate(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     *  V51222-205940.jpg
     *  V51222-205940 (1).jpg
     */
    @Override
    public boolean accept(File file) {
        String name = file.getName();
        return name.matches("(?m)V\\d{5}-\\d{6}(\\s\\(\\d+\\))?\\.(mp4|MP4)");
    }
    
}
