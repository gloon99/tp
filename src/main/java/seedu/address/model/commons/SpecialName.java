package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.Name;

/**
 * Represents a special Name that allows any characters. However, the special name should not contain empty spaces.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class SpecialName {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should not be empty";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public SpecialName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid special name.
     * Guarantees: String has been trimmed.
     * @param test String to be tested.
     */
    public static boolean isValidName(String test) {
        return test.length() != 0;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
