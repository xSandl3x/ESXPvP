package ua.xsandl3x.esxpvp.modules;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bukkit.event.Event;
import ua.xsandl3x.esxpvp.interfaces.IJectable;
import ua.xsandl3x.esxpvp.listeners.SXListener;
import java.util.function.Consumer;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public abstract class AbstractModule implements IJectable {

    private SXListener listener = new SXListener();

    @Getter
    private String moduleName;

    protected <T extends Event> void addEventListener(Class<T> eventClass, Consumer<T> eventConsumer) {
        listener.register(eventClass, eventConsumer);
    }

    @Override
    public void eject() {
        this.listener.unregisterAll();
    }

    public abstract boolean isEnabled();
}
