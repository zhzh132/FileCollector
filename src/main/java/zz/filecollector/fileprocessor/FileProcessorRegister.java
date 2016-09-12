/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector.fileprocessor;

import java.io.File;
import zz.filecollector.FileInfo;

/**
 *
 * @author zhan
 */
public class FileProcessorRegister {
    public static final FileProcessor[] processors = {
//        new MeizuPhotoProcessor(),
//        new NormalizedFileProcessor(),
//        new MeizuVideoProcessor(),
//        new HuaweiPhotoProcessor(),
//        new SamsungPhotoProcessor(),
//        new SamsungVideoProcessor(),
        new JpgPhotoProcessor()
    };
    
    public static FileProcessor find(File file) {
        for(FileProcessor fp : processors) {
            if(fp.applies(file)) {
                return fp;
            }
        }
        return null;
    }
    
    public static FileInfo extractFileInfo(File file) {
        FileProcessor fp = find(file);
        if(fp != null) {
            return fp.extractFileInfo(file);
        }
        return null;
    }
}
