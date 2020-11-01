package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

public class DeleteLabelCommand extends Command {
    public static final String COMMAND_WORD = "label delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the given tag(s) from the person identified by the name used in the displayed person list.\n"
            + "Parameters: NAME (must be name of person existing in ModDuke) "
            + PREFIX_TAG + "TAG\n"
            + "Example: " + COMMAND_WORD + " "
            + "Roy "
            + PREFIX_TAG + "classmate";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Labelled Person: %1$s";

    private final Name targetName;
    private final Set<Tag> tags;

    /**
     * @param targetName of the person in the filtered person list to edit
     * @param tags the set of tags to be removed from the person
     */
    public DeleteLabelCommand(Name targetName, Set<Tag> tags) {
        this.targetName = targetName;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isValidContact = model.hasPersonName(targetName);
        // check if Person exists in address book
        if (!isValidContact) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED);
        }

        // Update address book
        List<Person> filteredList = model.getFilteredPersonList().stream()
                .filter(person -> person.isSameName(targetName)).collect(Collectors.toList());
        Person personToEdit = filteredList.get(0);
        Person editedPerson = createEditedPerson(personToEdit, tags); // deletes the given labels from Person

        model.setPerson(personToEdit, editedPerson);

        // update meeting book
        model.updatePersonInMeetingBook(personToEdit, editedPerson);

        // update module book
        model.updatePersonInModuleBook(personToEdit, editedPerson);

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * with labels in {@code tags} removed.
     */
    private static Person createEditedPerson(Person personToEdit, Set<Tag> tags) throws CommandException {
        assert personToEdit != null;

        if (tags.stream().allMatch(tag -> personToEdit.getTags().contains(tag))) {
            // Person has all tags to be deleted
            Set<Tag> updatedTags = new HashSet<>(personToEdit.getTags());
            updatedTags.removeAll(tags);
            return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(), updatedTags);
        } else {
            // Person does not have all tags to be deleted
            throw new CommandException(
                    String.format("The person '%s' does not have all the tags provided.",
                            personToEdit.getName().toString()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteLabelCommand // instanceof handles nulls
                && targetName.equals(((DeleteLabelCommand) other).targetName)); // state check
    }
}
