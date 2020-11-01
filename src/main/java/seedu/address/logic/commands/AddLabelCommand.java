package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class AddLabelCommand extends Command {

    public static final String COMMAND_WORD = "label add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a label to the person specified.\n"
            + "Parameters: NAME (must be name of person existing in ModDuke) "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " "
            + "Roy "
            + PREFIX_TAG + "classmate";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Labelled Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Name targetName;
    private final LabelPersonDescriptor labelPersonDescriptor;

    /**
     * @param targetName of the person in the filtered person list to label
     * @param labelPersonDescriptor of the label to be added
     */
    public AddLabelCommand(Name targetName, LabelPersonDescriptor labelPersonDescriptor) {
        requireNonNull(targetName);
        requireNonNull(labelPersonDescriptor);

        this.targetName = targetName;
        this.labelPersonDescriptor = new LabelPersonDescriptor(labelPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isValidContact = model.hasPersonName(targetName);

        if (!isValidContact) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED);
        }

        List<Person> filteredList = model.getFilteredPersonList().stream()
                .filter(person -> person.isSameName(targetName)).collect(Collectors.toList());
        Person personToLabel = filteredList.get(0);

        Person labelledPerson = createLabelledPerson(personToLabel, labelPersonDescriptor);

        if (!personToLabel.isSamePerson(labelledPerson) && model.hasPerson(labelledPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToLabel, labelledPerson);

        // update meeting book
        model.updatePersonInMeetingBook(personToLabel, labelledPerson);

        // update module book
        model.updatePersonInModuleBook(personToLabel, labelledPerson);

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, labelledPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToLabel}
     * edited with {@code labelPersonDescriptor}.
     */
    private static Person createLabelledPerson(Person personToLabel, LabelPersonDescriptor labelPersonDescriptor) {
        assert personToLabel != null;

        Set<Tag> updatedTags = new HashSet<>(personToLabel.getTags());

        if (labelPersonDescriptor.getTags().isPresent()) {
            updatedTags.addAll(labelPersonDescriptor.getTags().get());
        }

        return new Person(personToLabel.getName(), personToLabel.getPhone(), personToLabel.getEmail(), updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddLabelCommand)) {
            return false;
        }

        // state check
        AddLabelCommand e = (AddLabelCommand) other;
        return targetName.equals(e.targetName)
                && labelPersonDescriptor.equals(e.labelPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class LabelPersonDescriptor {
        private Set<Tag> tags;

        public LabelPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public LabelPersonDescriptor(LabelPersonDescriptor toCopy) {
            setTags(toCopy.tags);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof LabelPersonDescriptor)) {
                return false;
            }

            // state check
            LabelPersonDescriptor e = (LabelPersonDescriptor) other;

            return getTags().equals(e.getTags());
        }
    }
}
