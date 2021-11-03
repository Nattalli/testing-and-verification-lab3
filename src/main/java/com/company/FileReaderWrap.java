package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderWrap implements FileSystemDataService {

    public FileReaderWrap(){}

    public String getData(String path) throws IOException, FileNotFoundException {

        var text = "";
        FileReader reader = new FileReader(path);
        StringBuilder builder = new StringBuilder();
        int i;

        while ((i = reader.read()) != -1)
            builder.append((char) i);
        text = builder.toString();

        return text;
    }

}
