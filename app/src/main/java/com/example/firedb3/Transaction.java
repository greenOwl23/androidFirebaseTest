package com.example.firedb3;

import java.text.DateFormat;
import java.util.Date;

public class Transaction {
    protected double value;
    protected DateFormat datetime;
    protected int type;

    public Transaction(){}

    public Transaction(double value,int type){ //1 = income && 0 = expense
        this.datetime = DateFormat.getDateTimeInstance();
        this.value =value;
        this.type = type;
    }

}
