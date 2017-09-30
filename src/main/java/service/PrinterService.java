package main.java.service;

import main.java.commons.Constants;
import main.java.model.Resource;

/**
 * Created by saurabh.jha on 26/06/16.
 */
public class PrinterService {

    private Resource[] resources = new Resource[Constants.MAX_RESOURCES];

    public int readCount = 0;

    public void write(String s){
        if (s == null || s.isEmpty()){
            System.out.println("empty string, existing write");
            return;
        }
        int index = 0;
        while (true){
            if (index == Constants.MAX_RESOURCES)
                index = 0;
            if (resources[index].isAvailable() && resources[index].getVal() == null){
                synchronized (resources[index]){
                    if (resources[index].isAvailable() && resources[index].getVal() == null){
                        resources[index].setAvailable(false);
                        resources[index].setVal(s);
                        System.out.println("Writing to resource " + index + " : " + s);
                        resources[index].setAvailable(true);
                        break;
                    }
                }
            }
            index++;
        }
    }

    public String read(){
        int index = 0;
        while (true){
            if (readCount == Constants.MAX_READ){
                return Constants.RESOURCE_READ_COMPLETE;
            }
            if (index == Constants.MAX_RESOURCES)
                index = 0;
            if (resources[index].isAvailable() && resources[index].getVal() != null){
                synchronized (resources[index]){
                    if (resources[index].isAvailable() && resources[index].getVal() != null){
                        resources[index].setAvailable(false);
                        String data = resources[index].getVal();
                        System.out.println("Printing from resource " + index + " : " +data);
                        increamentReadCoun();
                        resources[index].setVal(null);
                        resources[index].setAvailable(true);
                    }
                }
            }
            index++;
        }
    }

    public void init(){
        for (int i = 0; i < Constants.MAX_RESOURCES; i++){
            Resource resource = new Resource();
            resource.setId(i);
            resource.setAvailable(true);
            resources[i] = resource;
        }
    }

    private synchronized void increamentReadCoun(){
        readCount++;
    }
}
