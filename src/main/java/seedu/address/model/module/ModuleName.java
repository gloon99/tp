package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ModuleName {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String moduleName;

    /**
     * Constructs a {@code Name}.
     *
     * @param moduleName A valid meeting name.
     */
    public ModuleName(String moduleName) {
        requireNonNull(moduleName);
        checkArgument(isValidModuleName(moduleName), MESSAGE_CONSTRAINTS);
        this.moduleName = moduleName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getModuleName() {
        return this.moduleName;
    }


    @Override
    public String toString() {
        return moduleName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ModuleName
                && moduleName.equals(((ModuleName) other).getModuleName()));
    }

    @Override
    public int hashCode() {
        return moduleName.hashCode();
    }
}
