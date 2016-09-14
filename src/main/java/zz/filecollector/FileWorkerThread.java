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
import org.apache.commons.io.filefilter.TrueFileFilter;
import zz.filecollector.fileprocessor.FileProcessorRegister;

/**
 *
 * @author zhan
 */
public class FileWorkerThread extends Thread {
    public static final int ACTION_PREVIEW = 0;
    public static final int ACTION_MOVE = 1;
    public static final int ACTION_COPY = 2;
    public static final int ACTION_MOVEDEL = 3;
    
    private File srcDir;
    private File destDir;
    private final FileCollector photoCollector;
    private int actionType;
    private volatile boolean keepDoing = true;
    
    private int filesCopied;
    private int filesMoved;
    private int filesDeleted;
    private int filesTotal;
    
    public FileWorkerThread(FileCollector photoCollector) {
        this.srcDir = photoCollector.getSrcDir();
        this.destDir = photoCollector.getDestDir();
        this.photoCollector = photoCollector;
    }
    
    @Override
    public void run() {
        this.infoln("从" + srcDir.getAbsolutePath() + " 归档到 " + destDir.getAbsolutePath());
        this.photoCollector.disableButtons();
        Collection<File> srcFiles = FileUtils.listFiles(srcDir, ExtensionRegister.getInstance().getFileFilter(), TrueFileFilter.TRUE);
        for(File file : srcFiles) {
            if(keepDoing) {
                processFile(file);
            }
            else {
                this.infoln("终止!");
                break;
            }
        }
        this.infoln(String.format("共找到了 %d 个文件. 复制了 %d 个文件. 移动了 %d 个文件. 删除了 %d 个文件", 
                this.filesTotal, this.filesCopied, this.filesMoved, this.filesDeleted));
        this.photoCollector.enableButtons();
    }
    
    private void processFile(File file) {
        FileInfo fileInfo = FileProcessorRegister.extractFileInfo(file);
        if(fileInfo.isValid()) {
            this.filesTotal++;
            this.info(file.getAbsolutePath());
            
            fileInfo.setDupIndex(0);
            String newName = getNewFilePath(this.destDir.getAbsolutePath(), fileInfo);
            File newFile = new File(newName);
            while(newFile.exists()) {
                FileInfo newInfo = FileProcessorRegister.extractFileInfo(newFile);
                if(newInfo.isSameFile(fileInfo)) {
                    handleDuplicatedFile(file);
                    return;
                }
                else {
                    newInfo.setDupIndex(newInfo.getDupIndex() + 1);
                    newName = getNewFilePath(this.destDir.getAbsolutePath(), newInfo);
                    newFile = new File(newName);
                }
            }
            handleNewFile(file, newFile);
        }
        else {
            this.error("无法处理的文件 - " + file.getAbsolutePath());
        }
    }
    
    private void handleDuplicatedFile(File file) {
        this.infoln("... 重复的文件.");
        if(actionType == ACTION_MOVEDEL) {
            try {
                if(file.delete()) {
                    this.filesDeleted++;
                    this.infoln("已删除.");
                }
                else {
                    this.infoln("删除失败.");
                }
            } catch (Exception e) {
                this.error(e.getMessage());
            }
        }
    }
    
    private void handleNewFile(File file, File newFile) {
        this.infoln(" --> " + newFile.getAbsolutePath());

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
            case ACTION_MOVEDEL:
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
