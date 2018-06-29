package com.bass;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by a.makarov on 24.05.2018.
 */
public class CSVListMP3{
    CSVReader reader;
    HashMap<String, String> trackNames;
    String[] stringLines;

    public CSVListMP3(String path) throws IOException {
        trackNames = new HashMap<String, String>();
        reader = new CSVReader(new FileReader(path), ',' , '"' , 1);
    }
    public HashMap<String, String> getLines() throws IOException{
        while ((stringLines = reader.readNext()) != null) {
            if (stringLines != null) {
                trackNames.put(stringLines[1], stringLines[2].replaceAll("[\\W]{1,}", " ").trim());
            }
        }
        System.out.println("Count tracks: " + trackNames.size());
        return trackNames;
    }


}
