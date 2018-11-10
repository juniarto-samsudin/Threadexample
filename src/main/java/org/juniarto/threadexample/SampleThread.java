/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.juniarto.threadexample;

import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 *
 * @author samsudinj
 */


class Runner extends Thread{
    @Override
    public void run(){
        
    }
}

class Sprinter implements Runnable{
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        for (int i=0;i < 10; i++){
            System.out.println("Hello " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sprinter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}


class Consumer implements Runnable{

    @Override
    public void run() {
        System.out.println("Test Run");
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.20.116.17:9092,172.20.116.18:9092,172.20.116.19:9092");
        props.put("group.id","TestGroup");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("HeartBeat"));
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(10000);
            if (records.isEmpty()){
                System.out.println("No Record");
            }
            else{
                System.out.println("We have record");
            }
        }
        
    }
    
}
public class SampleThread {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Thread t1 = new Thread(new Consumer());
        t1.start();
    }
    
}
