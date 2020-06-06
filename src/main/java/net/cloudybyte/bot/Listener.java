package net.cloudybyte.bot;


import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.cloudybyte.bot.core.Constants;
import net.cloudybyte.bot.core.command.CommandManager;
import net.cloudybyte.bot.core.data.PrefixCache;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends ListenerAdapter {
    EventWaiter eventWaiter = new EventWaiter();
    private final CommandManager manager = new CommandManager(eventWaiter);
    private final Logger logger = LoggerFactory.getLogger(Listener.class);



    @Override
    public void onMessageReceived(MessageReceivedEvent event) {



        User author = event.getAuthor();
        Message message = event.getMessage();
        String content = message.getContentDisplay();

        if (event.isFromType(ChannelType.TEXT)) {

            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();

            //logger.info(String.format("(%s) [%s] <%#s>: %s", guild.getName(), textChannel.getName(), author, content));
        }
        /*else if (event.isFromType(ChannelType.PRIVATE)) {
            logger.info(String.format("[PRIV] <%#s>: %s", author, content));
        } */

    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().equalsIgnoreCase(Constants.PREFIX + "shutdown") &&
                event.getAuthor().getIdLong() == Constants.OWNER) {

            shutdown(event.getJDA());
            return;
        }

        final long guildid = event.getGuild().getIdLong();
        String prefix = PrefixCache.PREFIXES.computeIfAbsent(guildid, (id) -> Constants.PREFIX);

        if (!event.getAuthor().isBot() && !event.getMessage().isWebhookMessage() && event.getMessage().getContentRaw().startsWith(Constants.PREFIX)) {
                    manager.handleCommand(event, prefix);
        }
    }

    private void shutdown(JDA jda) {
        jda.shutdown();
    }
}
