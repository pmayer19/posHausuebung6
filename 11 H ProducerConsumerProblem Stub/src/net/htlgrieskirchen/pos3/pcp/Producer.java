/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable {
    private final String name;
    private final Storage storage;
    private final int sleepTime;
    
    private  List<Integer> sent;
    private final int numberOfItems;

    public Producer(String name, Storage storage, int sleepTime, int numberOfItems) {
        this.name = name;
        this.storage = storage;
        this.sleepTime = sleepTime;
        this.numberOfItems = numberOfItems;
    }
    
    
 
    @Override
    public void run() {
        Storage s = new Storage();
        for (int i = 0; i < numberOfItems; i++) {
            try {
                while(s.put(i)==true)
                {
                    sent.add(numberOfItems);
                    if(s.put(i)==false)
                    {
                        s.wait(10000);
                    }
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Integer> getSent() {
        return sent;
    }

    
    
}
