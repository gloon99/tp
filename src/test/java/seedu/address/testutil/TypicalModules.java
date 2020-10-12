package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ModuleBook;
import seedu.address.model.module.Module;


public class TypicalModules {
    public static final Module CS2105 = new ModuleBuilder().withName("CS2105").build();

    public static ModuleBook getTypicalModuleBook() {
        ModuleBook mb = new ModuleBook();
        for (Module module : getTypicalModules()) {
            mb.addModule(module);
        }
        return mb;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2105));
    }
}
