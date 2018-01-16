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
    public List<String> SReceiveLogs (String QUEUE_NAME, String SET_HOST) {
        try{
            
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(SET_HOST);
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. ");
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, consumer);
            
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            while(delivery != null){
                String message = new String(delivery.getBody());
                ReceiveSuccess(message);
                Thread.sleep(10);
                delivery = consumer.nextDelivery(0);
            }
            channel.close();
            connection.close();
                
        }catch (Exception e){  
        	e.printStackTrace();
        }
        return remessage;
    }
    
    @Override
    public void ReceiveSuccess(String message){
        remessage.add(message);
    }
}
