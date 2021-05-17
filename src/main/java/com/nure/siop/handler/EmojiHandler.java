package com.nure.siop.handler;

import com.nure.siop.Bot;
import com.nure.siop.command.ParsedCommand;
import com.nure.siop.textprocessing.CoviBot;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.objects.Update;

import java.util.HashSet;
import java.util.Set;

public class EmojiHandler extends AbstractHandler {
    private static final Logger log = Logger.getLogger(EmojiHandler.class);

    public EmojiHandler(Bot bot, CoviBot coviBot) {
        super(bot, coviBot);
    }

    @Override
    public String operate(String chatId, ParsedCommand parsedCommand, Update update) {
        String text = parsedCommand.getText();
        StringBuilder result = new StringBuilder();
        Set<String> emojisInTextUnique = new HashSet<>(EmojiParser.extractEmojis(text));
        if (emojisInTextUnique.size() > 0) result.append("Parsed emojies from message:").append("\n");
        for (String emojiUnicode : emojisInTextUnique) {
            Emoji byUnicode = EmojiManager.getByUnicode(emojiUnicode);
            log.debug(byUnicode.toString());
            String emoji = byUnicode.getUnicode() + " " +
                    byUnicode.getAliases() +
                    " " + byUnicode.getDescription();
            result.append(emoji).append("\n");
        }
        return result.toString();
    }
}