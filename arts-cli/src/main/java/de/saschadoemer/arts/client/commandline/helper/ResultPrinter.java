package de.saschadoemer.arts.client.commandline.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ResultPrinter {

    public void print(String result) {
        System.out.println("************************************************************************************");
        System.out.println(result);
        System.out.println("************************************************************************************");
    }

    public void print(Object object) {
        System.out.println("************************************************************************************");
        GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        System.out.println(gson.toJson(object));
        System.out.println("************************************************************************************");
    }

}
