package ua.xsandl3x.esxpvp.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import javax.annotation.Nullable;
import java.util.function.Consumer;
import java.util.logging.Level;

@UtilityClass
public class Utils {

    public void lazyColored(String message, Consumer<String> newMessage) {
        String coloredMessage = toColor(message);

        newMessage.accept(coloredMessage);
    }

    public void sendMessage(CommandSender sender, String message) {
        lazyColored(message, sender::sendMessage);
    }

    public void sendMessage(Player player, String message) {
        lazyColored(message, player::sendMessage);
    }

    public void sendLog(Level level, String message, @Nullable Object... objs) {
        lazyColored(message, newMessage -> {

            String formatMessage = "ESXPvP | " + message;

            Bukkit.getLogger().log(level, formatMessage, objs);
        });
    }

    public void sendLog(Level level, String[] messages, @Nullable Object... objs) {
        for (String message : messages)
            sendLog(level, message, objs);
    }

    public String toColor(String message) {
        return message.replace("&", "§");
    }
}

