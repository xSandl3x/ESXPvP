package ua.xsandl3x.esxpvp;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import ua.xsandl3x.esxpvp.modules.manager.ModuleManager;
import ua.xsandl3x.esxpvp.utils.TemplateUtil;

public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    private ModuleManager moduleManager;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        this.moduleManager = new ModuleManager();
        this.moduleManager.load();

        TemplateUtil.pluginStartMessage();
    }

    @Override
    public void onDisable() {
        this.moduleManager.unload();

        TemplateUtil.pluginStopMessage();
    }
}
