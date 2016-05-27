package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author glangsdorf18
 */
public class RingBuffer {
    private int size, first, last = 0;
    private double[] lod = new double[0];
    public RingBuffer(int capacity){
        lod=new double[capacity];
    }
    public int length(){return lod.length;}
    public int size(){ return size;}
    public boolean isEmpty(){
        if(size==0)
            return true;
        return false;
    }
    public boolean isFull(){
        if(size==lod.length)
            return true;
        return false;
    }
    public void enqueue(double x){
        if(isFull())
            throw new RuntimeException("The ring buffer is full");
        lod[last]=x;
        size++;
        last++;
        if(last==lod.length)
            last=0;
    }
    public double dequeue(){
        if(!isEmpty()){
        double d=lod[first];
        lod[first]=0.0;
        size--;
        first++;
        if(first==lod.length)
            first=0;
        return d;
        }
        
        return 0;
    }
    public double peek(){
        return lod[first];
    }


}
