/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmsl.wmslworker.ServiceImpl;

import com.wmsl.wmslworker.Service.ILogWriterService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author LuciferPM
 */
public class ElasticLogWriterService implements ILogWriterService{

    @Override
    public void CleaneFile(String PATH, String FILENAME) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void WriteLogWithURL(String URL, String TYPE, String message) {
        try{
            //for(int i =0;i<message.size();i++){
                HttpPost post = new HttpPost(URL+"/"+ TYPE +"/");
                post.setHeader("Content-Type", "application/json");
                HttpResponse response=null;
                String line = "";
                StringBuffer result = new StringBuffer();
                post.setEntity(new StringEntity(message));
                HttpClient client = HttpClientBuilder.create().build();
                response = client.execute(post);
                System.out.println("Post parameters : " + message+"\n At : "+ URL+"/"+ TYPE +"/");
                //System.out.println("Response Code : " +response.getStatusLine().getStatusCode());
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                while ((line = reader.readLine()) != null){ result.append(line); }
                System.out.println("Post result : "+result.toString());
            //}  
        }catch(Exception e){    
        }
    }

    @Override
    public void WriteLogWithPath(String PATH, String FILENAME, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
