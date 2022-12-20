package ua.xsandl3x.esxpvp.modules.type;

import org.bukkit.entity.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import ua.xsandl3x.esxpvp.modules.AbstractModule;

public class AttackFrequencyModule extends AbstractModule {

    private final int HIT_TICKS = 18;

    public AttackFrequencyModule() {
        super("AttackFrequency");
    }

    @Override
    public void inject() {
        this.addEventListener(PlayerJoinEvent.class, event -> {
            Player player = event.getPlayer();
            player.setMaximumNoDamageTicks(this.HIT_TICKS);
        });

        this.addEventListener(CreatureSpawnEvent.class, event -> {
            Entity entity = event.getEntity();

            if (!(entity instanceof LivingEntity))
                return;

            LivingEntity livingEntity = (LivingEntity)entity;
            livingEntity.setMaximumNoDamageTicks(this.HIT_TICKS - 2);
        });
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
