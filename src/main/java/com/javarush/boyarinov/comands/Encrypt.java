package com.javarush.boyarinov.comands;

import com.javarush.boyarinov.AppException;
import com.javarush.boyarinov.CreatePath;
import com.javarush.boyarinov.constats.Constant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;

public class Encrypt {

    public void encode(String input, String output, int key) {
        CreatePath path = new CreatePath();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path.getPath(input));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(path.getPath(output))) {
            while (bufferedReader.ready()) {
                int read = bufferedReader.read();
                int setKey = key % Constant.ALPHABET.length;
                char symbol = Character.toLowerCase((char) read);
                for (int i = 0; i < Constant.ALPHABET.length; i++) {
                    if (symbol == Constant.ALPHABET[i]) {
                        if ((i + setKey) > Constant.ALPHABET.length - 1) {
                            int i2 = (i + setKey) - Constant.ALPHABET.length;
                            symbol = Constant.ALPHABET[i2];
                        } else {
                            symbol = Constant.ALPHABET[i + setKey];
                        }
                        break;
                    }

                }
                bufferedWriter.write(symbol);
            }

        } catch (Exception e) {
            throw new AppException(e);
        }
    }
}
