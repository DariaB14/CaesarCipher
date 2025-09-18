package com.javarush;

import com.javarush.exceptions.AppException;
import com.javarush.exceptions.ValidationException;
import com.javarush.services.CaesarCipherManager;

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        CaesarCipherManager manager = new CaesarCipherManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printOptions();
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Входной файл: ");
                        String input = sc.nextLine();
                        System.out.print("Выходной файл: ");
                        String output = sc.nextLine();
                        System.out.print("Ключ: ");
                        int key = sc.nextInt();
                        manager.encryptFile(input, output, key);
                        System.out.println("Шифрование завершено!");
                        System.out.println("Результат сохранен в: " + output);
                        break;
                    case 2:
                        System.out.print("Зашифрованный файл: ");
                        String input2 = sc.nextLine();
                        System.out.print("Выходной файл: ");
                        String output2 = sc.nextLine();
                        System.out.print("Ключ: ");
                        int key2 = sc.nextInt();
                        manager.decryptFile(input2, output2, key2);
                        System.out.println("Расшифровка завершена!");
                        System.out.println("Результат сохранен в: " + output2);
                        break;
                    case 3:
                        System.out.print("Зашифрованный файл: ");
                        String input3 = sc.nextLine();
                        System.out.print("Выходной файл: ");
                        String output3 = sc.nextLine();
                        int key3= manager.bruteForce(input3, output3);
                        System.out.println("Найденный ключ: " + key3);
                        System.out.println("Результат сохранен в: " + output3);
                        break;
                    case 4:
                        System.out.print("Зашифрованный файл: ");
                        String input4 = sc.nextLine();
                        System.out.print("Эталонный файл: ");
                        String sample4 = sc.nextLine();
                        System.out.print("Выходной файл: ");
                        String output4 = sc.nextLine();
                        int key4= manager.statisticalDecrypt(input4, sample4, output4);
                        System.out.println("Найденный ключ: " + key4);
                        System.out.println("Результат сохранен в: " + output4);
                        break;
                    case 0:
                        System.out.println("Выход...");
                        return;

                    default:
                        System.out.println("Неверный выбор");
                }
            } catch (ValidationException e) {
                System.out.println("Ошибка валидации: "+e);
            } catch (IOException e) {
                System.out.println("Ошибка ввода-вывода: "+e);
            } catch (AppException e) {
                System.out.println("Ошибка приложения: "+e);
            } catch (Exception e) {
                System.out.println("Неожиданная ошибка: "+e);
            }
        }
    }

    private static void printOptions() {
        System.out.println("\n=== Шифр Цезаря ===");
        System.out.println("1. Зашифровать файл");
        System.out.println("2. Расшифровать файл (с ключом)");
        System.out.println("3. Взлом brute force");
        System.out.println("4. Взлом cтатическим анализом");
        System.out.println("0. Выход");
        System.out.print("Выберите действие. Введите целое число: ");
    }
}