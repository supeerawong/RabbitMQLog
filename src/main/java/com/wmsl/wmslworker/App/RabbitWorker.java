/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmsl.wmslworker.App;

import java.util.List;
import com.wmsl.wmslworker.Service.*;
import com.wmsl.wmslworker.ServiceImpl.*;
/**
 *
 * @author LuciferPM
 */
public class RabbitWorker {
    public static void main(String[] args) throws Exception {
        String PATH = args[5];
        String QUEUE_NAME = args[0];
        String SET_HOST = args[1];
        String INDEX = args[3];
        String URL = args[2]+INDEX;
        String TYPE = args[4];
        ILogReaderService RS = new RabbitMQLogReaderService();
        ILogWriterService WS = new StorageLogWriterService();
        ILogWriterService EW = new ElasticLogWriterService();
        //WS.CleaneFile(PATH, QUEUE_NAME);
        List<String> message = RS.SReceiveLogs(QUEUE_NAME, SET_HOST);
        while(true){
            if(message.isEmpty()){
                message = RS.SReceiveLogs(QUEUE_NAME, SET_HOST);
            }
            for(int i=0;i<message.size();i++) {
            	//WS.WriteLogWithPath(PATH, QUEUE_NAME, message.get(i));
                EW.WriteLogWithURL(URL, TYPE, message.get(i));
            }
            message.clear(); 
        }
    }
}
