package com.nure.siop.handler;

import com.nure.siop.Bot;
import com.nure.siop.command.ParsedCommand;
import com.nure.siop.textprocessing.NurePsychologist;
import org.telegram.telegrambots.api.objects.Update;

public abstract class AbstractHandler {
    Bot bot;
    NurePsychologist nurePsychologist;

    AbstractHandler(Bot bot, NurePsychologist nurePsychologist) {
        this.bot = bot;
        this.nurePsychologist = nurePsychologist;
    }

    public abstract String operate(String chatId, ParsedCommand parsedCommand, Update update);
}