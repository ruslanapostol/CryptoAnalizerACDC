package com.javarush.kudra.commands;

import com.javarush.kudra.Result;
import com.javarush.kudra.constants.Constant;
import com.javarush.kudra.exceptions.AppException;

import java.util.Scanner;

public class Decode extends AbstractActions {
    @Override
    public Result execute(Scanner console) {
        String encodedFile;
        String decodedFile;
        int key;
        System.out.printf(Constant.SOURCE_FILE, "encrypted.txt");
        try {
            encodedFile = console.nextLine();
            if (encodedFile.isEmpty()){
                encodedFile = "encrypted.txt";
            }
            System.out.printf(Constant.DEST_FILE, "decrypted.txt");
            decodedFile = console.nextLine();
            if (decodedFile.isEmpty()){
                decodedFile = "decrypted.txt";
            }
            System.out.println(Constant.INPUT_KEY);
            String keyValue = String.valueOf(Integer.parseInt(console.nextLine()));
            if (keyValue.isEmpty()) {
                key = 1;
            }
            else {
                key = Integer.parseInt(keyValue);
            }
        } catch (Exception e) {
            throw new AppException(Constant.INPUT_EXCEPTION, e);
        }
        return changeWithKey(encodedFile,decodedFile,key*-1);
    }
}
