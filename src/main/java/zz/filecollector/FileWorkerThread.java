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
    public static final int ACTION_MOVE = 1;
    public static final int ACTION_COPY = 2;
    public static final int ACTION_PREVIEW = 3;
    
    private static final String[] exts = {"jpg", "jpeg", "JPG", "JPEG", "mp4", "MP4"};
    
    private File srcDir;
    private File destDir;
    private final FileCollector photoCollector;
    private boolean keepDoing = true;
    private int filesToCopy;
    private int filesCopied;
    private int filesMoved;
    private int actionType;
    
    public FileWorkerThread(FileCollector photoCollector) {
        this.srcDir = photoCollector.getSrcDir();
        this.destDir = photoCollector.getDestDir();
        this.photoCollector = photoCollector;
    }
    
    @Override
    public void run() {
        this.infoln("从" + srcDir.getAbsolutePath() + " 归档到 " + destDir.getAbsolutePath());
        this.photoCollector.disableButtons();
        Collection<File> srcFiles = FileUtils.listFiles(srcDir, exts, true);
        for(File file : srcFiles) {
            if(keepDoing) {
                processFile(file);
            }
            else {
                this.infoln("终止!");
                break;
            }
        }
        this.infoln(String.format("%d 个文件需要处理. 复制了 %d 个文件. 移动了 %d 个文件", this.filesToCopy, this.filesCopied, this.filesMoved));
        this.photoCollector.enableButtons();
    }
    
    private void processFile(File file) {
        FileInfo fileInfo = FileProcessorRegister.extractFileInfo(file);
        if(fileInfo.getFileType() != FileInfo.UNKNOWN) {
            this.info(file.getAbsolutePath());
            
            fileInfo.setDupIndex(0);
            String newName = getNewFilePath(this.destDir.getAbsolutePath(), fileInfo);
            File newFile = new File(newName);
            while(newFile.exists()) {
                FileInfo newInfo = FileProcessorRegister.extractFileInfo(newFile);
                if(newInfo.isSameFile(fileInfo)) {
                    this.infoln("... 忽略.");
                    return;
                }
                else {
                    newInfo.setDupIndex(newInfo.getDupIndex() + 1);
                    newName = getNewFilePath(this.destDir.getAbsolutePath(), newInfo);
                    newFile = new File(newName);
                }
            }
            this.infoln(" --> " + newFile.getAbsolutePath());
            this.filesToCopy++;

            switch (actionType) {
                case ACTION_COPY:
                    try {
                        FileUtils.copyFile(file, newFile, true);
                        this.filesCopied++;
                        this.infoln("完成.");
                    } catch (Exception ex) {
                        this.error(ex.getMessage());
                    }   break;
                case ACTION_MOVE:
                    try {
                        FileUtils.moveFile(file, newFile);
                        this.filesMoved++;
                        this.infoln("完成.");
                    } catch (Exception ex) {
                        this.error(ex.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        else {
            this.error("无法处理的文件 - " + file.getAbsolutePath());
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

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }
    
    private void info(String msg) {
        this.photoCollector.displayInfo(msg);
    }
    
    private void infoln(String msg) {
        this.photoCollector.displayInfo(msg + "\n");
    }
    
    private void error(String msg) {
        this.photoCollector.displayInfo("ERROR: " + msg);
        this.photoCollector.displayInfo("\n");
    }
}
