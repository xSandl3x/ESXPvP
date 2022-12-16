package ua.xsandl3x.esxpvp.listeners;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.plugin.PluginManager;
import ua.xsandl3x.esxpvp.Main;
import java.util.function.Consumer;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class SXListener implements Listener {

    private Main INSTANCE = Main.getInstance();
    private PluginManager pluginManager = Bukkit.getPluginManager();

    public <T extends Event> void register(Class<T> eventClass, EventPriority priority, Consumer<T> eventConsumer) {
        this.pluginManager.registerEvent(eventClass, this, priority,
                (l, e) -> eventConsumer.accept((T) e), this.INSTANCE);
    }

    public <T extends Event> void register(Class<T> eventClass, Consumer<T> eventConsumer) {
        this.register(eventClass, EventPriority.NORMAL, eventConsumer);
    }

    public void register(Listener listener) {
        this.pluginManager.registerEvents(listener, this.INSTANCE);
    }

    public void register() {
        this.register(this);
    }

    public void unregisterAll() {
        HandlerList.unregisterAll(this.INSTANCE);
    }
}
