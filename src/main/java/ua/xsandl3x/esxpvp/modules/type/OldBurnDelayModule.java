package ua.xsandl3x.esxpvp.modules.type;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import ua.xsandl3x.esxpvp.modules.AbstractModule;

public class OldBurnDelayModule extends AbstractModule {

    private final int FIRE_TICKS = 1;

    public OldBurnDelayModule() {
        super("OldBurnDelay");
    }

    @Override
    public void inject() {
        this.addEventListener(EntityDamageEvent.class, event -> {
            Entity entity = event.getEntity();

            if (event.getCause() == EntityDamageEvent.DamageCause.FIRE)
                entity.setFireTicks(this.FIRE_TICKS);
        });
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
