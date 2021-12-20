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

    public Storage()
    {
        queue = new ArrayBlockingQueue<>(10);
    }

    public boolean put(Integer data) throws InterruptedException {
        boolean b = true;
        if(queue.remainingCapacity() == 0)
        {
            overflowCounter++;
            productionComplete = false;
        }
        else
        {
            queue.put(data);
            storedCounter++;
        }
        return b;
    }
 
    public Integer get() {
        if(queue.isEmpty())
        {
            underflowCounter++;
            return null;
        }
        else
        {
            queue.poll();
            fetchedCounter++;
            return fetchedCounter;
        }
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
