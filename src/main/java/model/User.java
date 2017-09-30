package main.java.model;

import main.java.service.PrinterService;

/**
 * Created by saurabh.jha on 26/06/16.
 */
public class User implements Runnable{

    private int userId;
    private String userName;

    private PrinterService printingService;

    public User(PrinterService printingService){
        this.printingService = printingService;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public void run() {
        printingService.write("this is data from user : " + userId);
    }
}
