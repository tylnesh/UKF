/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebCamAlarm;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import static org.apache.commons.io.FileUtils.copyFile;
import org.openide.util.Exceptions;
/**
 *
 * @author Michal Kohútek
 */
public class CopyImages implements Runnable {  //copies and archives images by timestamp - easy peasy
    
    
    
    private static String getDateTime()
{
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
    df.setTimeZone(TimeZone.getTimeZone("GMT+1"));
    return df.format(new Date());
}

    @Override
    public void run() {
       while(true)
       {
        try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Exceptions.printStackTrace(ex);
            }
        
        
        try{
            String t = getDateTime();
            
            String archT = "res/img/archive/" + t +".jpg";
        File fOld = new File("res/img/old.jpg");
        File fNew = new File("res/img/new.jpg");
        
        
        if (!fOld.isFile()) copyFile(fNew,fOld);
        
        File fArch = new File(archT);
        
       
        copyFile(fOld,fArch);
      //  File fNew = new File("res/img/new.jpg");
        
        
        copyFile(fNew, fOld);
        
        
        }catch(IOException ex){}
    
    }}
}
