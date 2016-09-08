/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author zhan
 */
public class ConfigManager {
    private String propFile = "E:/tmp/props.properties";
    
    private Properties props;
    
    public ConfigManager() {
        props = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(propFile);
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
    
    public String get(String key) {
        return props.getProperty(key);
    }
}
