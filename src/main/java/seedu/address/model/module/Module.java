package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;

public class Module {
    private final ModuleName moduleName;
    private final Set<Person> classmates;
    /**
     * Every field must be present and not null.
     */
    public Module(ModuleName name, Set<Person> classmates) {
        requireAllNonNull(name);
        this.moduleName = name;
        this.classmates = classmates;
    }

    public ModuleName getModuleName() {
        return moduleName;
    }

    public Set<Person> getClassmates() {
        return Collections.unmodifiableSet(classmates);
    }


    /**
     * Returns true if both modules have the same name, date and time.
     */
    public boolean isSameModule(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModuleName().getModuleName().equals(getModuleName().getModuleName());
    }

    /**
     * Returns true if both modules have the same name
     */
    public boolean isSameName(ModuleName otherModule) {
        return otherModule != null
                && otherModule.getModuleName().equals(getModuleName());
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

        if (!(other instanceof seedu.address.model.meeting.Meeting)) {
            return false;
        }

        seedu.address.model.meeting.Meeting otherMeeting = (seedu.address.model.meeting.Meeting) other;
        return otherMeeting.getMeetingName().equals(getModuleName())
                && otherMeeting.getMembers().equals(getClassmates());
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
