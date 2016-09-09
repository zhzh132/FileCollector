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
public class HuaweiPhotoProcessor implements FileProcessor {

    public static final String NAME = "HUAWEI_P6";
    
    @Override
    public FileInfo extractFileInfo(File file) {
        FileInfo info = new FileInfo();
        info.setDevice(NAME);
        info.setFileType(FileInfo.PHOTO);
        String name = file.getName();
        
        int year = Integer.parseInt(name.substring(4,8));
        info.setYear(year);
        
        int month = Integer.parseInt(name.substring(8,10));
        info.setMonth(month);
        
        int date = Integer.parseInt(name.substring(10,12));
        info.setDate(date);
        
        String timeStr = name.substring(13,19);
        info.setTimeStr(timeStr);
        
        info.setSize(FileUtils.sizeOf(file));
        String extension = FilenameUtils.getExtension(name).toLowerCase();
        info.setExtension(extension);
        info.setDupIndex(FileProcessor.getDupIndex(name));
        return info;
    }

    /**
     *  IMG_20150216_215521.jpg
     */
    @Override
    public boolean applies(File file) {
        String name = file.getName();
        return name.matches("(?m)IMG_\\d{8}_\\d{6}\\.jpg");
    }
    
}
