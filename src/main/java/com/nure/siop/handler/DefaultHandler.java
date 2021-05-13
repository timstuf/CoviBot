package com.nure.siop.handler;

import com.nure.siop.Bot;
import com.nure.siop.command.ParsedCommand;
import com.nure.siop.textprocessing.NurePsychologist;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.objects.Update;

public class DefaultHandler extends AbstractHandler {
    private static final Logger log = Logger.getLogger(DefaultHandler.class);

    public DefaultHandler(Bot bot, NurePsychologist nurePsychologist) {
        super(bot, nurePsychologist);
    }

    @Override
    public String operate(String chatId, ParsedCommand parsedCommand, Update update) {
        String response = nurePsychologist.processInput(parsedCommand.getText());
        return response;

    }
}
