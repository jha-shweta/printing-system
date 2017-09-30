package main.java.model;

import main.java.service.PrinterService;

/**
 * Created by saurabh.jha on 26/06/16.
 */
public class Printer implements Runnable{

    private int printerId;

    private PrinterService printingService;

    public Printer(PrinterService printingService){
        this.printingService = printingService;
    }

    public int getPrinterId() {
        return printerId;
    }

    public void setPrinterId(int printerId) {
        this.printerId = printerId;
    }


    @Override
    public void run() {
        System.out.println(printingService.read());
    }
}
