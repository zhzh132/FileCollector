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
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import zz.filecollector.FileInfo;

/**
 *
 * @author zhan
 */
public class JpgPhotoProcessor implements FileProcessor {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.CHINA);
    
    @Override
    public void extractFileInfo(File file, FileInfo info) {
        FileProcessor.getBasicFileInfo(file, info);
        if(info.getFileType() == FileInfo.UNKNOWN) {
            info.setFileType(FileInfo.PHOTO);
        }
        
        try {
            ImageMetadata metadata = Imaging.getMetadata(file);
            if (metadata instanceof JpegImageMetadata) {
                final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
                
                if(info.getCreateDate() == null) {
                    try {
                        TiffField dateTime = jpegMetadata.findEXIFValueWithExactMatch(TiffTagConstants.TIFF_TAG_DATE_TIME);
                        String str = dateTime.getStringValue();  // 2014:02:06 12:11:26
                        if(StringUtils.isNotEmpty(str)) {
                            Date date = dateFormat.parse(str);
                            info.setCreateDate(date);
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                
                if(info.getDevice() == null) {
                    TiffField model = jpegMetadata.findEXIFValueWithExactMatch(TiffTagConstants.TIFF_TAG_MODEL);
                    byte[] bytes = model.getByteArrayValue();
                    String modelStr = new String(bytes).trim();
                    if(StringUtils.isNotEmpty(modelStr)) {
                        info.setDevice(modelStr);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public boolean applies(File file) {
        String ext = FilenameUtils.getExtension(file.getName()).toLowerCase();
        return "jpg".equals(ext) || "jpeg".equals(ext);
    }
    
}
