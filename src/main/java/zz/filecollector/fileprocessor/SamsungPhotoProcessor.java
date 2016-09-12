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
public class SamsungPhotoProcessor implements FileProcessor {

    public static final String NAME = "SAMSUNG";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss", Locale.CHINA);
    
    @Override
    public FileInfo extractFileInfo(File file) {
        FileInfo info = new FileInfo();
        info.setDevice(NAME);
        info.setFileType(FileInfo.PHOTO);
        String name = file.getName();
        
        try {
            Date date = dateFormat.parse(name.substring(0, 19));
            info.setCreateDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        info.setSize(FileUtils.sizeOf(file));
        String extension = FilenameUtils.getExtension(name).toLowerCase();
        info.setExtension(extension);
        info.setDupIndex(FileProcessor.getDupIndex(name));
        return info;
    }

    /**
     *  2014-02-06 12.11.26.jpg
     */
    @Override
    public boolean applies(File file) {
        String name = file.getName();
        return name.matches("(?m)\\d{4}(-\\d{2}){2}\\s(\\d{2}\\.){3}jpg");
    }
    
}
