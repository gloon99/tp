package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Meeting> PREDICATE_SHOW_ALL_MEETINGS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;


    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getMeetingBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setMeetingBookFilePath(Path meetingBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setMeetingBook(ReadOnlyMeetingBook meetingBook);

    /** Returns the AddressBook */
    ReadOnlyMeetingBook getMeetingBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the same name as {@code person} exists in the address book.
     */
    boolean hasPersonName(Name name);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    ObservableList<Person> getUpdatedFilteredPersonList(Predicate<Person> predicate);

    ObservableList<Person> getUpdatedFilteredPersonList(Predicate<Person> predicate,
                                                        List<ModuleName> modules) throws CommandException;

    boolean hasMeeting(Meeting meeting);

    void addMeeting(Meeting meeting);

    /**
     * Returns true if a meeting with the same meeting name as {@code meeting} exists in the meeting book.
     */
    boolean hasMeetingName(MeetingName meetingName);

    /**
     * Deletes the given meeting.
     * The person must exist in the meeting book.
     */
    void deleteMeeting(Meeting targetMeeting);

    /**
     * Replaces the given meeting {@code target} with {@code editedMeeting}.
     * {@code target} must exist in the meeting book.
     * The meeting identity of {@code editedMeeting} must not be the same
     * as another existing meeting in the meeting book.
     */
    void setMeeting(Meeting target, Meeting editedMeeting);

    /**
     * Updates all meetings in the meeting book if the required person was part of any meeting.
     * @param persons First argument is the person to update which is either deleted or replaced. If replaced, there
     * is a second argument which is the edited person who will replace the deleted person.
     */
    void updatePersonInMeetingBook(Person ...persons);

    /**
     * Sets the selected Meeting for the model manager.
     */
    void setSelectedMeeting(Meeting target);

    /**
     * Returns a selected Meeting in an observable list.
     */
    Meeting getSelectedMeeting();

    /** Returns an unmodifiable view of the filtered meeting list */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Updates the filter of the filtered meeting list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMeetingList(Predicate<Meeting> predicate);

    void addModule(Module module);

    /**
     * Returns true if a module with the same module name as {@code module} exists in the address book.
     */
    boolean hasModuleName(ModuleName moduleName);

    /** Returns the ModuleBook */
    ReadOnlyModuleBook getModuleBook();

    /**
     * Replaces module book data with the data in {@code moduleBook}.
     */
    void setModuleBook(ReadOnlyModuleBook moduleBook);

    /**
     * Returns the user prefs' module book file path.
     */
    Path getModuleBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setModuleBookFilePath(Path moduleBookFilePath);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    void getPersonsInModule(ModuleName moduleName) throws CommandException;

    /**
     * Updates all modules in the module book if the required person was part of any module.
     * @param persons First argument is the person to update which is either deleted or replaced. If replaced,
     * second argument is the edited person who will replace the deleted person.
     */
    void updatePersonInModuleBook(Person ...persons);

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    /**
     * Updates all meetings in the module book if the required module was part of any meetings. Will delete the meeting
     * if all the members of the new module are not in the meeting or if the module is deleted.
     * @param modules First argument is the module to update which is either deleted or replaced. If replaced,
     * second argument is the edited module who will replace the deleted module.
     */
    void updateModuleInMeetingBook(Module ...modules);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the module book.
     * The module identity of {@code editedModule} must not be the same
     * as another existing module in the module book.
     */
    void setModule(Module target, Module editedModule);

    /**
     * Deletes the given module.
     * The module must exist in the module book.
     */
    void deleteModule(Module target);

}
