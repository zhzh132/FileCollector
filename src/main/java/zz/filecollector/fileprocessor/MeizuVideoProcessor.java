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
public class MeizuVideoProcessor implements FileProcessor {

    public static final String NAME = "MEIZU";
    
    @Override
    public FileInfo extractFileInfo(File file) {
        FileInfo info = new FileInfo();
        info.setDevice(NAME);
        info.setFileType(FileInfo.VIDEO);
        String name = file.getName();
        
        int year = Integer.parseInt(name.substring(1,2));
        info.setYear(2010 + year);
        
        int month = Integer.parseInt(name.substring(2,4));
        info.setMonth(month);
        
        int date = Integer.parseInt(name.substring(4,6));
        info.setDate(date);
        
        String timeStr = name.substring(7,13);
        info.setTimeStr(timeStr);
        
        info.setSize(FileUtils.sizeOf(file));
        info.setExtension(FilenameUtils.getExtension(name).toLowerCase());
        info.setDupIndex(FileProcessor.getDupIndex(name));
        return info;
    }

    /**
     *  V51222-205940.jpg
     *  V51222-205940 (1).jpg
     */
    @Override
    public boolean applies(File file) {
        String name = file.getName();
        return name.matches("(?m)V\\d{5}-\\d{6}(\\s\\(\\d+\\))?\\.(mp4|MP4)");
    }
    
}
