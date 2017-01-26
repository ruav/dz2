package ru.inno.utils;

/**
 * Created by ruav on 04.01.17.
 */
public class MyException extends Exception {

    public MyException(String s) {
        super(s);
    }

    @Override
    public void printStackTrace() {
//        super.printStackTrace();
        System.out.println(this.getClass().getName() + " " + this.getMessage());

    }
}