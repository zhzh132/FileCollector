/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author zhan
 */
public class ConfigManager {
    private static final String NAME = "props.properties";
    private String workingDir;
    private File propFile;
    
    private Properties props;
    
    public ConfigManager() {
        try {
            this.workingDir = System.getProperty("user.dir");
            
            propFile = new File(FilenameUtils.concat(workingDir, NAME));
            props = new Properties();
            
            if(propFile.createNewFile()) {
                saveProperties();
            }
            else {
                loadProperties(propFile);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadProperties(File file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null) { in.close(); }
            } catch (IOException ex) {
            }
        }
    }
    
    public void saveProperties() {
        OutputStream out = null;
        try {
            out = new FileOutputStream(propFile);
            props.store(out, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(out != null) out.close();
            } catch (IOException ex) {
            }
        }
    }
    
    public String get(String key) {
        return props.getProperty(key);
    }
    
    public void set(String key, String value) {
        this.props.setProperty(key, value);
    }
}
