/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector;

import java.io.File;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import zz.filecollector.fileprocessor.FileProcessorRegister;

/**
 *
 * @author zhan
 */
public class FileWorkerThread extends Thread {
    private static final String[] exts = {"jpg", "jpeg", "JPG", "JPEG", "mp4", "MP4"};
    
    private File srcDir;
    private File destDir;
    private final PhotoCollector photoCollector;
    private boolean keepDoing = true;
    private boolean isCopy = false;
    private int filesToCopy;
    private int filesCopied;
    
    public FileWorkerThread(File srcDir, File destDir, PhotoCollector photoCollector) {
        this.srcDir = srcDir;
        this.destDir = destDir;
        this.photoCollector = photoCollector;
    }
    
    @Override
    public void run() {
        this.info("开始处理" + srcDir.getAbsolutePath());
        this.photoCollector.disableButtons();
        Collection<File> srcFiles = FileUtils.listFiles(srcDir, exts, true);
        for(File file : srcFiles) {
            if(keepDoing) {
                processFile(file, this.isCopy);
            }
            else {
                this.info("Aborted!");
                break;
            }
        }
        this.info(String.format("处理完毕. %d files to copy. %d files copied.", this.filesToCopy, this.filesCopied));
        this.photoCollector.enableButtons();
    }
    
    private void processFile(File file, boolean isCopy) {
        FileInfo fileInfo = FileProcessorRegister.extractFileInfo(file);
        if(fileInfo != null) {
            this.info(file.getAbsolutePath());
            
            fileInfo.setDupIndex(0);
            String newName = getNewFilePath(this.destDir.getAbsolutePath(), fileInfo);
            File newFile = new File(newName);
            while(newFile.exists()) {
                FileInfo newInfo = FileProcessorRegister.extractFileInfo(newFile);
                if(newInfo.isSameFile(fileInfo)) {
                    this.info("Skip.");
                    return;
                }
                else {
                    newInfo.setDupIndex(newInfo.getDupIndex() + 1);
                    newName = getNewFilePath(this.destDir.getAbsolutePath(), newInfo);
                    newFile = new File(newName);
                }
            }
            this.info("Copy " + file.getAbsolutePath() + " to " + newFile.getAbsolutePath());
            this.filesToCopy++;

            if(isCopy) {
                try {
                    FileUtils.copyFile(file, newFile, true);
                    this.filesCopied++;
                    this.info("Done.");
                } catch (Exception ex) {
                    this.error(ex.getMessage());
                }
            }
        }
        else {
            this.error("Unknown file - " + file.getAbsolutePath());
        }
    }
    
    private String getNewFilePath(String basePath, FileInfo fileInfo) {
        StringBuilder path = new StringBuilder();
        switch(fileInfo.getFileType()) {
            case FileInfo.PHOTO:
                path.append("照片/");
                break;
            case FileInfo.VIDEO:
                path.append("视频/");
                break;
            default:
                path.append("其他/");
        }
        path.append(fileInfo.getYear()).append("/");
        if(fileInfo.getFileType() == FileInfo.PHOTO) {
            path.append(fileInfo.getMonth()).append("月/");
        }
        path.append(fileInfo.getNormalizedName());
        return FilenameUtils.concat(basePath, path.toString());
    }
    
    public void stopWorking() {
        this.keepDoing = false;
    }
    
    public void setCopy(boolean isCopy) {
        this.isCopy = isCopy;
    }
    
    private void info(String msg) {
        this.photoCollector.displayInfo(msg);
    }
    
    private void error(String msg) {
        this.photoCollector.displayInfo("ERROR: " + msg);
    }
}
