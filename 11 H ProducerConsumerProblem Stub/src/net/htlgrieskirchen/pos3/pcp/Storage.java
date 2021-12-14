/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.concurrent.ArrayBlockingQueue;

public class Storage { 
    private  ArrayBlockingQueue<Integer> queue;
    
    private int fetchedCounter;
    private int storedCounter;
    private int underflowCounter;
    private int overflowCounter;
    private boolean productionComplete;

    public Storage(ArrayBlockingQueue<Integer> queue, int fetchedCounter, int storedCounter, int underflowCounter, int overflowCounter, boolean productionComplete) {
        this.queue = queue;
        this.fetchedCounter = fetchedCounter;
        this.storedCounter = storedCounter;
        this.underflowCounter = underflowCounter;
        this.overflowCounter = overflowCounter;
        this.productionComplete = productionComplete;
    }

    public Storage() {
    }

    
    public synchronized boolean put(Integer data) throws InterruptedException {
        boolean b = true;
        queue.add(data);
        storedCounter++;
        for (int i = 0; i <= 10; i++) {
           if(i <= 10)
           {
                overflowCounter++;
                b=false;
           }
        }
        return b;
        
    }
 
    public synchronized Integer get() {
        int ret = queue.poll();
        fetchedCounter++;
        return fetchedCounter;
    }

    public boolean isProductionComplete() {
        return productionComplete;
    }

    public void setProductionComplete() {
        productionComplete = true;
    }

    public int getFetchedCounter() {
        return fetchedCounter;
    }

    public int getStoredCounter() {
        return storedCounter;
    }

    public int getUnderflowCounter() {
       return underflowCounter;
    }

    public int getOverflowCounter() {
        return overflowCounter;
    }
}
