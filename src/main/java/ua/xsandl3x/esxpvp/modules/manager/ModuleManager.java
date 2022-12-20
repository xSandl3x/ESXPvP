package ua.xsandl3x.esxpvp.modules.manager;

import lombok.experimental.FieldDefaults;
import ua.xsandl3x.esxpvp.interfaces.*;
import ua.xsandl3x.esxpvp.modules.AbstractModule;
import ua.xsandl3x.esxpvp.modules.type.*;
import ua.xsandl3x.esxpvp.utils.Utils;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

@FieldDefaults(makeFinal = true)
public class ModuleManager implements ILoadable, IUnloadable {

    private List<AbstractModule> moduleList = new ArrayList<>();

    @Override
    public void load() {
        this.moduleList.addAll(Arrays.asList(
                new AttackCooldownModule(),
                new AttackFrequencyModule(),
                new AttackSoundsModule(),
                new OldBurnDelayModule(),
                new BowBoostModule())
        );

        List<AbstractModule> sortedList = this.moduleList.stream()
                .filter(AbstractModule::isEnabled)
                .collect(Collectors.toList());

        sortedList.forEach(AbstractModule::inject);

        Utils.sendLog(Level.INFO, " [DEBUG] -> {0}/{1} modules loaded.\n", sortedList.size(), this.moduleList.size());
    }

    @Override
    public void unload() {
        this.moduleList.forEach(AbstractModule::eject);

        Utils.sendLog(Level.INFO, "| [DEBUG] -> All running modules disabled.");
    }
}
