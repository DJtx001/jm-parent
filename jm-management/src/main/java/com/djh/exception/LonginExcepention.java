package com.djh.exception;

public class LonginExcepention extends RuntimeException{
    public LonginExcepention(){
        super();
    }
    public LonginExcepention(String message){
        super(message);
    }
}
