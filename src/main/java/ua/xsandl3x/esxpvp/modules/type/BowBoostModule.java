package ua.xsandl3x.esxpvp.modules.type;

import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;
import ua.xsandl3x.esxpvp.modules.AbstractModule;

public class BowBoostModule extends AbstractModule {

    public BowBoostModule() {
        super("BowBoost");
    }

    @Override
    public void inject() {
        this.addEventListener(EntityDamageByEntityEvent.class, event -> {
            if (!(event.getEntity() instanceof Player))
                return;

            if (!(event.getDamager() instanceof Arrow))
                return;

            Player victim = (Player) event.getEntity();
            Arrow arrow = (Arrow) event.getDamager();

            ProjectileSource arrowShooter = arrow.getShooter();

            if (!(arrowShooter instanceof Player))
                return;

            Player shooter = (Player) arrowShooter;

            event.setCancelled(victim.equals(shooter));
        });
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
