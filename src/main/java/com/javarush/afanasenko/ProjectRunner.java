package com.javarush.afanasenko;

//Тебе нужно разобраться как работает шифр Цезаря

import java.util.Scanner;

public class ProjectRunner {
    public static void main(String[] args) {
     while(true) {
         printMenu();
         Scanner sc = new Scanner(System.in);
         String choice = sc.nextLine();
         switch (choice) {
             case "1":
                 try {
                     Encoder encoder = createEncoder(sc, "text\\text.txt");
                     encoder.encode("encode","text\\encodedFile.txt");
                     System.out.println("Файл успешно закодирован.");
                 } catch (CryptoException ex) {
                     System.out.println(ex.getMessage());
                 }
                 break;
             case "2":
                 try {
                     Encoder encoder = createEncoder(sc, "text\\encodedFile.txt");
                     encoder.encode("decode", "text\\decodedFile.txt");
                     System.out.println("Файл успешно декодирован.");
                 } catch (CryptoException ex) {
                     System.out.println(ex.getMessage());
                 }
                 break;
             case "3":
                 break;
             case "4":
                 return;
             default:
                 System.out.println("Такой символ не поддерживается программой");
         }
     }
    }

    public static void printMenu() {
        System.out.println("Добро пожаловать в криптограф Цезаря, чем я могу вам помочь?");
        System.out.println("Введите на клавиатуре символ, который соотвествует вашему запросу.");
        System.out.println("1 - зашифровать текст");
        System.out.println("2 - расшифровать текст с помощью ключа");
        System.out.println("3 - взлом шифра(Brute Force)");
        System.out.println("4 - выход из программы");
    }

    public static Encoder createEncoder(Scanner sc, String defaultPath) throws CryptoException{
        System.out.println("Введите путь к файлу или используйте путь по умолчанию(введите 0)");
        String pathString = sc.nextLine();
        System.out.println("Введите ключ кодировки");
        int key = sc.nextInt();
        if(pathString.equals("0")) pathString = defaultPath;
        try {
            PathBuilder builder = new PathBuilder(pathString);
            return new Encoder((new TextHandler(builder.getPath())),key);
        }catch(CryptoException ex){
            ex.getMessage();
        }
            throw  new CryptoException("Путь к файлу введен некорректно, попробуйте еще раз");
    }
}