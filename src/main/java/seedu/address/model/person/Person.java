package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    public enum PersonType {
        CONTACT,
        PROFESSOR,
        TA
    }

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final PersonType personType;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.personType = parseTags(tags);
        this.tags.addAll(tags);
    }

    /**
     * Mark a person as Professor or TA if there's a prof tag or ta tag. Otherwise set PersonType as CONTACT.
     */
    private PersonType parseTags(Set<Tag> tags) {
        Tag profTag = new Tag(Tag.PROF_TAG_NAME);
        Tag taTag = new Tag(Tag.TA_TAG_NAME);

        if (tags.contains(profTag)) {
            return PersonType.PROFESSOR;
        } else if (tags.contains(taTag)) {
            return PersonType.TA;
        } else {
            return PersonType.CONTACT;
        }
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public PersonType getPersonType() {
        return personType;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same name.
     */
    public boolean isSameName(Name otherName) {
        return name.equals(otherName);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
