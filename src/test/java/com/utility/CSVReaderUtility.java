package com.utility;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;
import com.ui.pojo.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility
{
    public static Iterator<User> readCSV(String fileName )
    {
        File csvFile = new File(System.getProperty("user.dir") + "//testData//" + fileName);
        FileReader fileReader = null;
        CSVReader csvReader;
        String[] line;
        List<User> userList = null;
        User userData;
        try {
            fileReader = new FileReader(csvFile);
            csvReader = new CSVReader(fileReader);
            csvReader.readNext();
            userList = new ArrayList<User>();

                while((line = csvReader.readNext()) != null)
                {
                    userData = new User(line[0], line[1]);
                    userList.add(userData);
                }
            } catch (IOException | CsvValidationException ex) {
            ex.printStackTrace();
        }
        return userList.iterator();
    }
}
