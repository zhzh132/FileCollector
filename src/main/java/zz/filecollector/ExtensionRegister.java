/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector;

import java.io.File;
import java.util.HashSet;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author zhan
 */
public class ExtensionRegister {
    private static final String[] EXT_PHOTO = {"jpg", "jpeg"};
    private static final String[] EXT_VIDEO = {"mp4", "avi", "mov"};
    
    private static final ExtensionRegister instance = new ExtensionRegister();
    
    private final HashSet<String> photos;
    private final HashSet<String> videos;
    private boolean caseSensitive = false;
    
    private ExtensionRegister() {
        this.photos = new HashSet<String>();
        this.loadExt(photos, EXT_PHOTO);
        this.videos = new HashSet<String>();
        this.loadExt(videos, EXT_VIDEO);
    }
    
    private void loadExt(HashSet<String> set, String[] exts) {
        for(String s : exts) {
            set.add(s);
        }
    }
    
    public static ExtensionRegister getInstance() {
        return instance;
    }
    
    public IOFileFilter getFileFilter() {
        return new IOFileFilter() {
            @Override
            public boolean accept(File file) {
                if(file != null) {
                    return this.accept(file.getParentFile(), file.getName());
                }
                return false;
            }

            @Override
            public boolean accept(File dir, String name) {
                if(StringUtils.isNoneEmpty(name)) {
                    String ext = FilenameUtils.getExtension(name);
                    if(StringUtils.isNoneEmpty(ext)) {
                        return isPhoto(ext) || isVideo(ext);
                    }
                }
                return false;
            }
        };
    }

    public boolean isPhoto(String ext) {
        if(!caseSensitive) {
            ext = ext.toLowerCase();
        }
        return this.photos.contains(ext);
    }
    
    public boolean isVideo(String ext) {
        if(!caseSensitive) {
            ext = ext.toLowerCase();
        }
        return this.videos.contains(ext);
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }
}
