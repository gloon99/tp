package seedu.address.testutil;

import seedu.address.model.ModuleBook;
import seedu.address.model.module.Module;

public class ModuleBookBuilder {
    private ModuleBook moduleBook;

    public ModuleBookBuilder() {
        moduleBook = new ModuleBook();
    }

    public ModuleBookBuilder(ModuleBook moduleBook) {
        this.moduleBook = moduleBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ModuleBookBuilder withModule(Module module) {
        moduleBook.addModule(module);
        return this;
    }

    public ModuleBook build() {
        return moduleBook;
    }

}
