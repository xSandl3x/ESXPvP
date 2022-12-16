package ua.xsandl3x.esxpvp.modules.type;

import org.bukkit.attribute.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import ua.xsandl3x.esxpvp.modules.AbstractModule;

public class AttackCooldownModule extends AbstractModule {

    private final double SPEED_ATTACK = 16.0D;

    public AttackCooldownModule() {
        super("AttackCooldown");
    }

    @Override
    public void inject() {
        this.addEventListener(PlayerJoinEvent.class, event -> {
            Player player = event.getPlayer();
            AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);

            attribute.setBaseValue(this.SPEED_ATTACK);
        });
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
