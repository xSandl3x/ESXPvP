package ua.xsandl3x.esxpvp.modules.type;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import ua.xsandl3x.esxpvp.modules.AbstractModule;

public class FishRodModule extends AbstractModule {

    private final double KNOCKBACK_DAMAGE = 0.2;
    private final double VELOCITY_SCALAR = 0.4;

    public FishRodModule() {
        super("FishRod");
    }

    @Override
    public void inject() {
        this.addEventListener(ProjectileHitEvent.class, event -> {
            if (!event.getEntityType().equals(EntityType.FISHING_HOOK))
                return;

            Entity entity = event.getEntity();
            World world = entity.getWorld();

            Entity hitEntity = world.getNearbyEntities(entity.getLocation(), 0.2, 0.2, 0.2).stream()
                    .filter(everyEntity -> (everyEntity instanceof Player))
                    .findFirst()
                    .orElse(null);

            if (!(hitEntity instanceof LivingEntity))
                return;

            FishHook hook = (FishHook) entity;
            Player rodder = (Player) hook.getShooter();
            Player victim = (Player) hitEntity;

            if (victim.getGameMode().equals(GameMode.CREATIVE))
                return;

            if (victim.equals(rodder))
                return;

            victim.damage(this.KNOCKBACK_DAMAGE);
            victim.setVelocity(rodder.getLocation().getDirection().multiply(this.VELOCITY_SCALAR));

            hook.remove();
        });
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
