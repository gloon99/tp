package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Person;

public class ModuleBuilder {
    public static final String DEFAULT_NAME = "CS2103";

    private ModuleName moduleName;
    private Set<Person> members;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleName = new ModuleName(DEFAULT_NAME);
        members = new HashSet<>();
    }

    /**
     * Initializes the ModuleBuilder with the data of {@code meetingToCopy}.
     */
    public ModuleBuilder(Module moduleToCopy) {
        moduleName = moduleToCopy.getModuleName();
    }

    /**
     * Sets the {@code ModuleName} of the {@code Module} that we are building.
     */
    public ModuleBuilder withName(String name) {
        this.moduleName = new ModuleName(name);
        return this;
    }


    /**
     * Sets the {@code Members} of the {@code Module} that we are building.
     */
    public ModuleBuilder withMembers(Set<Person> members) {
        this.members = members;
        return this;
    }

    public Module build() {
        return new Module(moduleName, members);
    }
}
