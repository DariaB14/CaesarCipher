package com.javarush.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public String readFile(String filePath) throws IOException {
        Path path= Paths.get(filePath);
        StringBuilder context=new StringBuilder();

        try(BufferedReader reader= Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String line;
            while((line=reader.readLine()) != null){
                context.append(line).append(System.lineSeparator());
            }
        }
        if(context.length()>0){
            context.setLength(context.length()-System.lineSeparator().length());
        }
        return context.toString();
    }

    public void writeFile(String content, String filePath) throws IOException {
        Path path =Paths.get(filePath);
        Files.createDirectories(path.getParent());

        try(BufferedWriter writer=Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
            writer.write(content);
        }
    }
}
