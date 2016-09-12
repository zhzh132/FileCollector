/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector.fileprocessor;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import zz.filecollector.FileInfo;

/**
 *
 * @author zhan
 */
public interface FileProcessor {
    void extractFileInfo(File file, FileInfo info);
    boolean applies(File file);
    
    static int getDupIndex(String name) {
        int fr = name.lastIndexOf("(") + 1;
        if(fr > 0) {
            int to = name.lastIndexOf(")");
            if(fr < to) {
                String str = name.substring(fr, to);
                if(StringUtils.isNumeric(str)) {
                    return Integer.parseInt(str);
                }
            }
        }
        return 0;
    }
    
    static void getBasicFileInfo(File file, FileInfo info) {
        if(info.getSize() == 0) {
            info.setSize(FileUtils.sizeOf(file));
        }
        
        String name = file.getName();
        if(info.getExtension() == null) {
            String extension = FilenameUtils.getExtension(name).toLowerCase();
            info.setExtension(extension);
        }
        if(info.getDupIndex() == 0) {
            info.setDupIndex(FileProcessor.getDupIndex(name));
        }
    }
}
