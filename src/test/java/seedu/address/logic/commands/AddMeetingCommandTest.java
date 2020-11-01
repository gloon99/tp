package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.ReadOnlyModuleBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.MeetingBuilder;



public class AddMeetingCommandTest {

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddMeetingCommand(null, null, null, null, null,
                        null, null));
    }

    /*
    @Test
    public void execute_meetingAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMeetingAdded modelStub = new ModelStubAcceptingMeetingAdded();
        Meeting validMeeting = new MeetingBuilder().build();

        CommandResult commandResult = new AddMeetingCommand(validMeeting).execute(modelStub);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        Meeting validMeeting = new MeetingBuilder().build();
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(validMeeting);
        ModelStub modelStub = new ModelStubWithMeeting(validMeeting);

        assertThrows(CommandException.class, AddMeetingCommand.MESSAGE_DUPLICATE_MEETING, () ->
                addMeetingCommand.execute(modelStub));
    }
     */

    @Test
    public void equals() {
        Meeting cs2103 = new MeetingBuilder().withName("CS2103").build();
        Meeting cs3234 = new MeetingBuilder().withName("CS3234").build();
        AddMeetingCommand meetingCommand1 = new AddMeetingCommand(cs2103);
        AddMeetingCommand meetingCommand2 = new AddMeetingCommand(cs3234);

        // same object -> returns true
        assertTrue(meetingCommand2.equals(meetingCommand2));

        // same values -> returns true
        AddMeetingCommand meetingCommand1Copy = new AddMeetingCommand(cs2103);
        assertTrue(meetingCommand1.equals(meetingCommand1Copy));

        // different types -> returns false
        assertFalse(meetingCommand2.equals(1));

        // null -> returns false
        assertFalse(meetingCommand2.equals(null));

        // different meeting -> returns false
        assertFalse(meetingCommand2.equals(meetingCommand1));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonName(Name name) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getUpdatedFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getUpdatedFilteredPersonList(Predicate<Person> predicate,
                                                                   List<ModuleName> modules) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingBook(ReadOnlyMeetingBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMeetingBook getMeetingBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedMeeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedMeeting(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Meeting getSelectedMeeting() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePersonInMeetingBook(Person ...persons) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeetingName(MeetingName meetingName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting targetMeeting) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public Path getMeetingBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingBookFilePath(Path meetingBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasModuleName(ModuleName moduleName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyModuleBook getModuleBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleBook(ReadOnlyModuleBook moduleBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getModuleBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleBookFilePath(Path moduleBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getPersonsInModule(ModuleName moduleName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePersonInModuleBook(Person ...persons) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateModuleInMeetingBook(Module... modules) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModule(Module target, Module editedModule) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(Module target) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single meeting.
     */
    private class ModelStubWithMeeting extends ModelStub {
        private final Meeting meeting;

        ModelStubWithMeeting(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return this.meeting.isSameMeeting(meeting);
        }

        @Override
        public boolean hasMeetingName(MeetingName meetingName) {
            return this.meeting.isSameMeetingName(meetingName);
        }
    }

    /**
     * A Model stub that always accept the meeting being added.
     */
    private class ModelStubAcceptingMeetingAdded extends ModelStub {
        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();

        @Override
        public boolean hasMeeting(Meeting meeting) {
            return meetingsAdded.stream().anyMatch(meeting::isSameMeeting);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public boolean hasMeetingName(MeetingName meetingName) {
            return meetingsAdded.stream().anyMatch(meeting -> meeting.isSameMeetingName(meetingName));
        }

        @Override
        public ReadOnlyMeetingBook getMeetingBook() {
            return new MeetingBook();
        }
    }

}
