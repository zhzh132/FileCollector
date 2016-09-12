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
        new MeizuPhotoProcessor(),
        new NormalizedFileProcessor(),
        new MeizuVideoProcessor(),
        new HuaweiPhotoProcessor(),
        new SamsungPhotoProcessor(),
        new SamsungVideoProcessor(),
        new JpgPhotoProcessor()
    };
    
    public static FileInfo extractFileInfo(File file) {
        FileInfo info = new FileInfo();
        for(FileProcessor fp : processors) {
            if(fp.applies(file)) {
                fp.extractFileInfo(file, info);
            }
        }
        return info;
    }
}
