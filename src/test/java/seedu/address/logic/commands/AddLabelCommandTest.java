package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.LABEL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LABEL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddLabelCommand.LabelPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.LabelPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * AddLabelCommand.
 */
public class AddLabelCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
        new UserPrefs());

    @Test
    public void execute_filteredList_success() {
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person labelledPerson = new PersonBuilder(personInFilteredList).withTags("professor").build();
        AddLabelCommand labelCommand = new AddLabelCommand(labelledPerson.getName(),
                new LabelPersonDescriptorBuilder(labelledPerson).build());
        Set<Tag> updatedTags = new HashSet<>(personInFilteredList.getTags());
        updatedTags.add(new Tag("professor"));
        Person finalPerson = new Person(labelledPerson.getName(), labelledPerson.getPhone(),
                labelledPerson.getEmail(), updatedTags);

        String expectedMessage = String.format(AddLabelCommand.MESSAGE_EDIT_PERSON_SUCCESS, finalPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                                                new MeetingBook(model.getMeetingBook()),
                                                new ModuleBook(model.getModuleBook()),
                                                new UserPrefs());
        expectedModel.setPerson(personInFilteredList, finalPerson);
        expectedModel.updatePersonInMeetingBook(personInFilteredList, finalPerson);
        expectedModel.updatePersonInModuleBook(personInFilteredList, finalPerson);
        expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        assertCommandSuccess(labelCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonNameUnfilteredList_failure() {
        LabelPersonDescriptor descriptor = new LabelPersonDescriptorBuilder().build();
        AddLabelCommand labelCommand = new AddLabelCommand(new Name("Fake"), descriptor);

        assertCommandFailure(labelCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED);
    }

    @Test
    public void equals() {
        Person firstPerson = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPerson = model.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        final AddLabelCommand standardCommand = new AddLabelCommand(firstPerson.getName(), LABEL_DESC_AMY);

        // same values -> returns true
        LabelPersonDescriptor copyDescriptor = new LabelPersonDescriptor(LABEL_DESC_AMY);
        AddLabelCommand commandWithSameValues = new AddLabelCommand(firstPerson.getName(), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddLabelCommand(secondPerson.getName(), LABEL_DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AddLabelCommand(secondPerson.getName(), LABEL_DESC_BOB)));
    }

}
