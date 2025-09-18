package com.javarush.validation;

import com.javarush.exceptions.FileValidationException;
import com.javarush.exceptions.KeyValidationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {

    public static void isValidKey(int key) {
        if(key<0){
            throw new KeyValidationException("Ключ не может быть отрицательным");
        }
    }

    public static void isInputFileExistsAndReadable(String filePath) {
        Path path=validatingPath(filePath);

        if(!Files.exists(path)) throw new FileValidationException("Файл не существует");

        if(!Files.isRegularFile(path)) throw new FileValidationException("Путь не является файлом");

        if(!Files.isReadable(path)) throw new FileValidationException("Файл невозможно прочитать");

        try {
            if(Files.size(path)==0) throw new FileValidationException("Файл пустой");
        } catch (IOException e){
            throw new FileValidationException("Не удалось определить размер файла");
        }
    }

    public static void isOutputFileExistsAndWriteable(String filePath) {
        Path path=validatingPath(filePath);
        Path dir = path.getParent();

        try{
            if(dir!= null) Files.createDirectories(dir);
        } catch (IOException e){
            throw new FileValidationException("Невозможно создать директорию");
        }

        if(Files.exists(path)) {
            if (!Files.isRegularFile(path)) throw new FileValidationException("Путь не является файлом");

            if (!Files.isWritable(path)) throw new FileValidationException("Файл недоступен для записи");
        }
//        } else {
//            Path dir=path.getParent();
//            if (dir != null && !Files.exists(dir)) throw new IOException("The directory doesn`t exist");
//
//            if(dir != null && !Files.isWritable(dir)) throw new IOException("Impossible to create file in the directory");
//        }
    }


    private static Path validatingPath(String filePath) {
        try{
            return Paths.get(filePath).toAbsolutePath().normalize();
        } catch (InvalidPathException e){
            throw new FileValidationException("Некорректный путь");
        }
    }


}
