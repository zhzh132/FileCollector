/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector.fileprocessor;

import java.io.File;
import org.apache.commons.lang3.StringUtils;
import zz.filecollector.FileInfo;

/**
 *
 * @author zhan
 */
public interface FileProcessor {
    FileInfo extractFileInfo(File file);
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
}
