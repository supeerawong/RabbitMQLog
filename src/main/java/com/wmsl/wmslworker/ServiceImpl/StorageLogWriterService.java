/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmsl.wmslworker.ServiceImpl;

import com.wmsl.wmslworker.Service.ILogWriterService;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 *
 * @author LuciferPM
 */
public class StorageLogWriterService implements ILogWriterService{
    @Override
    public void CleaneFile(String PATH,String FILENAME){
        try{
            FileWriter sfw = new FileWriter(PATH + FILENAME+".txt",false);
            BufferedWriter sbw = new BufferedWriter(sfw);
            sbw.close();
            sfw.close();
        }catch(Exception e){
            e.printStackTrace();
        }  
    }
    
    @Override
    public void WriteLogWithPath(String PATH,String FILENAME, String message){
        try{
            
            //for(int i = 0;i<message.size();i++){
                String filename = message.substring(message.indexOf("\"Level\"")+10, message.indexOf("\"Level\"")+11);
                FileWriter fw = new FileWriter(PATH + FILENAME+"_"+filename+".txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(message+"\n");
                System.out.println(" [x] Writelog : '" + message + "'");
                bw.close();
                fw.close();
                System.out.println("Write Storage Success");
            //}
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void WriteLogWithURL(String URL, String TYPE, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
