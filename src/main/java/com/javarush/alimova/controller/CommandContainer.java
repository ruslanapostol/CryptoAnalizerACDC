package com.javarush.alimova.controller;

import com.javarush.alimova.commands.Coding;
import com.javarush.alimova.commands.Command;
import com.javarush.alimova.exception.AppException;

public enum CommandContainer {

    CODING(new Coding());

    private final Command command;

    CommandContainer(Command command) {
        this.command = command;
    }

    public static Command getCommand(String commandName) {
        try {
            CommandContainer instance = CommandContainer.valueOf(commandName.toUpperCase());
            return instance.command;
        } catch (IllegalArgumentException e) {
            System.out.println("Невалидное наименование");
            throw new AppException();
        }
    }

}
