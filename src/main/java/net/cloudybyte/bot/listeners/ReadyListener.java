package net.cloudybyte.bot.listeners;


import io.sentry.SentryClient;
import io.sentry.SentryClientFactory;
import net.cloudybyte.bot.Listener;
import net.cloudybyte.bot.util.Colors;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

import static net.cloudybyte.bot.util.Colors.GREEN;
import static net.cloudybyte.bot.util.Colors.RESET;


public class ReadyListener extends ListenerAdapter {
    private String GREEN = Colors.GREEN;
    private String RESET = Colors.RESET;
    SentryClient sentry = SentryClientFactory.sentryClient();
    private final Logger logger = LoggerFactory.getLogger(ReadyListener.class);


        public static void main(String[] args)
                throws LoginException
        {
            JDA jda = new JDABuilder(args[0])
                    .addEventListeners(new ReadyListener()).build();
        }

    @Override
    public void onReady(ReadyEvent event) {
        logger.info(String.format("Logged in as %#s", event.getJDA().getSelfUser()));
        logger.info(String.format(GREEN + "Booted" + RESET));
    }
    }

