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
        new JpgPhotoProcessor(),
        new MeizuPhotoProcessor(),
        new MeizuVideoProcessor(),
        new HuaweiPhotoProcessor(),
        new HuaweiVideoProcessor(),
        new SamsungPhotoProcessor(),
        new SamsungVideoProcessor(),
        new CameraVideoProcessor(),
        new FaceUPhotoProcessor(),
        new FaceUVideoProcessor(),
        new NormalizedFileProcessor()
    };
    
    public static FileInfo extractFileInfo(File file) {
        FileInfo info = new FileInfo();
        for(FileProcessor fp : processors) {
            if(fp.accept(file)) {
                fp.extractFileInfo(file, info);
            }
        }
        return info;
    }
}
