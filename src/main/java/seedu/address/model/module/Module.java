package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class Module {
    private final ModuleName moduleName;
    private final Set<Person> classmates;
    /**
     * Every field must be present and not null.
     */
    public Module(ModuleName moduleName, Set<Person> classmates) {
        requireAllNonNull(moduleName);
        this.moduleName = moduleName;
        this.classmates = classmates;
    }

    public ModuleName getModuleName() {
        return this.moduleName;
    }

    public Set<Person> getClassmates() {
        return Collections.unmodifiableSet(classmates);
    }

    /**
     * checks if the module contains the classmate of the given name
     * @param name name of the classmate
     * @return true if classmate is in module, false otherwise
     */
    public boolean hasClassmate(Name name) {
        for (Person person : getClassmates()) {
            if (person.isSameName(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both modules have the same name, date and time.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModuleName().getModuleName().equals(getModuleName().getModuleName())
                && otherModule.getClassmates().equals(getClassmates());
    }

    /**
     * Returns true if both modules have the same name
     */
    public boolean isSameName(ModuleName otherModuleName) {
        return moduleName.equals(otherModuleName);
    }

    /**
     * Returns true if both modules have the same identity and data fields.
     * This defines a stronger notion of equality between two modules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Module)) {
            return false;
        }

        Module otherModule = (Module) other;
        return otherModule.getModuleName().equals(getModuleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleName, classmates);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Module: ")
                .append(getModuleName())
                .append(" Members: ");
        getClassmates().forEach(member -> builder.append(member.getName() + ", "));
        return builder.substring(0, builder.length() - 2);
    }
}
