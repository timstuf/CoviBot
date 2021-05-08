package com.nure.siop.ability;

import com.nure.siop.Bot;
import lombok.ToString;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import static com.nure.siop.messages.Responses.NOTIFY_CONFIRMATION;
import static com.nure.siop.messages.Responses.NOTIFY_MESSAGE;

@ToString
public class Notify implements Runnable {
    private static final Logger log = Logger.getLogger(Notify.class);
    private static final int MILLISEC_IN_SEC = 1000;

    Bot bot;
    long delayInMillisec;
    String chatID;

    public Notify(Bot bot, String chatID, long delayInMillisec) {
        this.bot = bot;
        this.chatID = chatID;
        this.delayInMillisec = delayInMillisec;
        log.debug("CREATE. " + toString());
    }

    @Override
    public void run() {
        log.info("RUN. " + toString());
        bot.sendQueue.add(getFirstMessage());
        try {
            Thread.sleep(delayInMillisec);
            bot.sendQueue.add(NOTIFY_MESSAGE);
            bot.sendQueue.add(Stickers.ANTON_NAHUY.getSendSticker(chatID));
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("FIHISH. " + toString());
    }

    private SendMessage getFirstMessage() {
        return new SendMessage(chatID, String.format(NOTIFY_CONFIRMATION,  delayInMillisec / MILLISEC_IN_SEC));
    }

    private SendMessage getSecondMessage() {
        return new SendMessage(chatID, "This is notify message. Thanks for using :)");
    }
}
