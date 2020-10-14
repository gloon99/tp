package seedu.address.model.person;

import java.util.List;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the names given
 * and a {@code Person}'s {@code Tags} matches all of the tags given.
 */
public class PersonHasTagsPredicate implements PersonPredicate {
    private final List<Tag> tags;
    public PersonHasTagsPredicate(List<Tag> tags) {
        this.tags = tags;
    }
    @Override
    public boolean test(Person person) {
        return tags.stream()
                .anyMatch(tag -> person.getTags().contains(tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonHasTagsPredicate // instanceof handles nulls
                && tags.equals(((PersonHasTagsPredicate) other).tags)); // state check
    }

}
