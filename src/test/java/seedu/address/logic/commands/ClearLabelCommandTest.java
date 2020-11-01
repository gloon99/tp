package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ClearLabelCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
            new UserPrefs());

    @Test
    public void execute_personWithTags_success() {
        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person clearedPerson = new PersonBuilder(personInFilteredList).withTags().build();
        ClearLabelCommand labelCommand = new ClearLabelCommand(personInFilteredList.getName());

        String expectedMessage = String.format("All labels of person '%s' have been cleared!",
                clearedPerson.getName().toString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new ModuleBook(model.getModuleBook()),
                new UserPrefs());
        expectedModel.setPerson(personInFilteredList, clearedPerson);

        assertCommandSuccess(labelCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_personWithoutTags_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person personInFilteredList = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());
        Person clearedPerson = new PersonBuilder(personInFilteredList).withTags().build();
        ClearLabelCommand labelCommand = new ClearLabelCommand(personInFilteredList.getName());

        String expectedMessage = String.format("All labels of person '%s' have been cleared!",
                clearedPerson.getName().toString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new ModuleBook(model.getModuleBook()),
                new UserPrefs());
        expectedModel.setPerson(personInFilteredList, clearedPerson);

        assertCommandSuccess(labelCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidName_failure() {
        ClearLabelCommand labelCommand = new ClearLabelCommand(new Name("Fake"));
        assertCommandFailure(labelCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED);
    }
}
