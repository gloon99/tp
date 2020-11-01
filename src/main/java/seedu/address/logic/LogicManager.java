package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.MeetingBookParser;
import seedu.address.logic.parser.ModuleBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.ReadOnlyModuleBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private final MeetingBookParser meetingBookParser;
    private final ModuleBookParser moduleBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        meetingBookParser = new MeetingBookParser();
        moduleBookParser = new ModuleBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        String firstWord = commandText.split(" ")[0];

        CommandResult commandResult;
        Command command;

        if (firstWord.equals("meeting")) {
            command = meetingBookParser.parseCommand(commandText);
        } else if (firstWord.equals("module")) {
            command = moduleBookParser.parseCommand(commandText);
        } else {
            command = addressBookParser.parseCommand(commandText);
        }
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveMeetingBook(model.getMeetingBook());
            storage.saveModuleBook(model.getModuleBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getMeetingBookFilePath() {
        return model.getMeetingBookFilePath();
    }

    @Override
    public ReadOnlyMeetingBook getMeetingBook() {
        return model.getMeetingBook();
    }

    @Override
    public Meeting getSelectedMeeting() {
        return model.getSelectedMeeting(); }

    @Override
    public ReadOnlyModuleBook getModuleBook() {
        return model.getModuleBook();
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return model.getFilteredModuleList();
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return model.getFilteredMeetingList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public Path getModuleBookFilePath() {
        return model.getModuleBookFilePath();
    }
}
