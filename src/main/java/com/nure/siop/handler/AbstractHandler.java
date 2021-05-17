package com.nure.siop.handler;

import com.nure.siop.Bot;
import com.nure.siop.command.ParsedCommand;
import com.nure.siop.textprocessing.CoviBot;
import org.telegram.telegrambots.api.objects.Update;

public abstract class AbstractHandler {
    Bot bot;
    CoviBot coviBot;

    AbstractHandler(Bot bot, CoviBot coviBot) {
        this.bot = bot;
        this.coviBot = coviBot;
    }

    public abstract String operate(String chatId, ParsedCommand parsedCommand, Update update);
}