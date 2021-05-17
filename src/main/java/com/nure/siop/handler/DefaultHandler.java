package com.nure.siop.handler;

import com.nure.siop.Bot;
import com.nure.siop.command.ParsedCommand;
import com.nure.siop.textprocessing.CoviBot;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.objects.Update;

public class DefaultHandler extends AbstractHandler {
    private static final Logger log = Logger.getLogger(DefaultHandler.class);

    public DefaultHandler(Bot bot, CoviBot coviBot) {
        super(bot, coviBot);
    }

    @Override
    public String operate(String chatId, ParsedCommand parsedCommand, Update update) {
        String response = coviBot.processInput(parsedCommand.getText());
        return response;

    }
}
