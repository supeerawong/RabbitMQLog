/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmsl.wmslworker.ServiceImpl;

import com.wmsl.wmslworker.Service.ILogReaderService;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitManagementTemplate;

/**
 *
 * @author LuciferPM
 */
public class RabbitMQLogReaderService implements ILogReaderService{
    List<String> remessage = new ArrayList<>();
    @Override
    public void ReceiveLogs (String EXCHANGE_NAME,String FILEPATH, String SET_HOST){ 
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(SET_HOST);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            
            channel.queueDeclare(EXCHANGE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. ");
            
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                }
            };
            channel.basicConsume(EXCHANGE_NAME, true, consumer);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (TimeoutException ex) {
            ex.printStackTrace();
        }  
   }
    
    @Override
    public List<String> SReceiveLogs (String QUEUE_NAME, String SET_HOST) throws Exception {
    	ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(SET_HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
            System.out.println(" [*] Waiting for messages. ");
			GetResponse gr = channel.basicGet(QUEUE_NAME, true);
			if(gr != null) {
				String message = new String(gr.getBody());
				ReceiveSuccess(message);
				System.out.println(message);
			}
            	
        channel.close();
        connection.close();
        return remessage;
    }
    
    @Override
    public void ReceiveSuccess(String message){
        remessage.add(message);
    }
}
