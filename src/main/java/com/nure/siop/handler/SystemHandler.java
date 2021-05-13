package com.nure.siop.handler;

import com.nure.siop.Bot;
import com.nure.siop.command.Command;
import com.nure.siop.command.ParsedCommand;
import com.nure.siop.textprocessing.NurePsychologist;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import static com.nure.siop.messages.Responses.HELLO;
import static com.nure.siop.messages.Responses.SEE_HELP;

public class SystemHandler extends AbstractHandler {
    private static final Logger log = Logger.getLogger(SystemHandler.class);
    private final String END_LINE = "\n";

    public SystemHandler(Bot bot, NurePsychologist nurePsychologist) {
        super(bot, nurePsychologist);
    }

    @Override
    public String operate(String chatId, ParsedCommand parsedCommand, Update update) {
        Command command = parsedCommand.getCommand();

        switch (command) {
            case START:
                bot.sendQueue.add(getMessageStart(chatId));
                break;
            case HELP:
                bot.sendQueue.add(getMessageHelp(chatId));
                break;
            case ID:
                return "Your telegramID: " + update.getMessage().getFrom().getId();
            case STICKER:
                return "StickerID: " + parsedCommand.getText();
        }
        return "";
    }

    private SendMessage getMessageHelp(String chatID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);

        StringBuilder text = new StringBuilder();
        text.append("[/start](/start) - показать начальное сообщение").append(END_LINE);
        text.append("[/help](/help) - показать сообщение со списком команд").append(END_LINE);
        text.append("[/id](/id) - узнать свой id в телеграме").append(END_LINE);
        text.append("/notify _time-in-sec_  - получить уведомление в указанное время").append(END_LINE);
        text.append("").append(END_LINE);
        text.append("Шоб добавить сюда еще команду, пиздуй в класс SystemHandler").append(END_LINE);

        sendMessage.setText(text.toString());
        return sendMessage;
    }

    private SendMessage getMessageStart(String chatID) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.enableMarkdown(true);
        StringBuilder text = new StringBuilder();
        text.append(HELLO).append(END_LINE);
        text.append(SEE_HELP);
        sendMessage.setText(text.toString());
        return sendMessage;
    }
}
