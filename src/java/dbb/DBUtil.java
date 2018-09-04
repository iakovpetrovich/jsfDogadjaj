/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbb;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Jaša
 */
public class DBUtil {
    
    Properties props;

    public DBUtil() throws IOException {
        props= new Properties();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("dbb/db.properties");
        props.load(is);
       
    }
     public String vratiURL(){
         return props.getProperty(DBKonstante.URL);
     }
     
     public String vratiKorisnika(){
         return props.getProperty(DBKonstante.USER);
     }
     
     public String vratiSifru(){
         return props.getProperty(DBKonstante.PASSWORD);
     }
    
    
    
}
