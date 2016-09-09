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
public class SamsungVideoProcessor implements FileProcessor {
    
    public static final String NAME = "SAMSUNG";

    @Override
    public FileInfo extractFileInfo(File file) {
        FileInfo info = new FileInfo();
        info.setDevice(NAME);
        info.setFileType(FileInfo.VIDEO);
        String name = file.getName();
        
        int year = Integer.parseInt(name.substring(6,10));
        info.setYear(year);
        
        int month = Integer.parseInt(name.substring(11,13));
        info.setMonth(month);
        
        int date = Integer.parseInt(name.substring(14,16));
        info.setDate(date);
        
        String timeStr = name.substring(17,25).replaceAll("-", "");
        info.setTimeStr(timeStr);
        
        info.setSize(FileUtils.sizeOf(file));
        info.setExtension(FilenameUtils.getExtension(name).toLowerCase());
        info.setDupIndex(FileProcessor.getDupIndex(name));
        return info;
    }

    /**
     *  video-2014-05-25-14-35-59.mp4
     */
    @Override
    public boolean applies(File file) {
        String name = file.getName();
        return name.matches("(?m)video-\\d{4}(-\\d{2}){5}\\.mp4");
    }
    
}
