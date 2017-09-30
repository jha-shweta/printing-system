package main.java;

import main.java.commons.Constants;
import main.java.model.Printer;
import main.java.model.User;
import main.java.service.PrinterService;

/**
 * Created by saurabh.jha on 26/06/16.
 */
public class PrinterManager {

    private static Printer[] printers = new Printer[Constants.MAX_PRINTERS];
    private static User[] users = new User[Constants.MAX_USERS];

    private static PrinterService printerService = new PrinterService();

    private static void init(){
        printerService.init();
        initPrinters();
        initUsers();
    }

    private static void initPrinters(){
        for (int i = 0; i < Constants.MAX_PRINTERS; i++){
            Printer printer = new Printer(printerService);
            printer.setPrinterId(i);
            printers[i] = printer;
        }

    }

    private static void initUsers(){
        for (int i = 0; i < Constants.MAX_USERS; i++){
            User user = new User(printerService);
            user.setUserId(i);
            user.setUserName(Constants.USER_PREFIX + i);
            users[i] = user;
        }
    }

    public static void main(String args[]) {
        init();
        for (User user : users) {
            new Thread(user).start();
        }

        for (Printer printer : printers) {
            new Thread(printer).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("total records printed : " + printerService.readCount);
    }
}
