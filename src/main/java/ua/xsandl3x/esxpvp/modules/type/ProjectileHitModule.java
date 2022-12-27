package ua.xsandl3x.esxpvp.modules.type;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ua.xsandl3x.esxpvp.modules.AbstractModule;

public class ProjectileHitModule extends AbstractModule {

    private final String[] HIT_MATERIALS = { "EGG", "SNOWBALL", "ENDER_PEARL" };

    private final double PROJECTILE_DAMAGE = 0.01;

    public ProjectileHitModule() {
        super("ProjectileHit");
    }

    @Override
    public void inject() {
        this.addEventListener(EntityDamageByEntityEvent.class, event -> {
            EntityType entityType = event.getEntityType();

            for (String hitMaterial : this.HIT_MATERIALS) {
                if (entityType.toString().equals(hitMaterial))
                    event.setDamage(this.PROJECTILE_DAMAGE);
            }
        });
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
