package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.ReadOnlyModuleBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;


/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private MeetingBookStorage meetingBookStorage;
    private ModuleBookStorage moduleBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          MeetingBookStorage meetingBookStorage,
                          ModuleBookStorage moduleBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.moduleBookStorage = moduleBookStorage;
        this.addressBookStorage = addressBookStorage;
        this.meetingBookStorage = meetingBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ MeetingBook methods ==============================

    @Override
    public Path getMeetingBookFilePath() {
        return meetingBookStorage.getMeetingBookFilePath();
    }

    @Override
    public Optional<ReadOnlyMeetingBook> readMeetingBook() throws DataConversionException, IOException {
        return readMeetingBook(meetingBookStorage.getMeetingBookFilePath());
    }

    @Override
    public Optional<ReadOnlyMeetingBook> readMeetingBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return meetingBookStorage.readMeetingBook(filePath);
    }

    @Override
    public void saveMeetingBook(ReadOnlyMeetingBook meetingBook) throws IOException {
        saveMeetingBook(meetingBook, meetingBookStorage.getMeetingBookFilePath());
    }

    @Override
    public void saveMeetingBook(ReadOnlyMeetingBook meetingBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        meetingBookStorage.saveMeetingBook(meetingBook, filePath);
    }
    // ================ ModuleBook methods ========================================
    @Override
    public Path getModuleBookFilePath() {
        return moduleBookStorage.getModuleBookFilePath();
    }

    @Override
    public Optional<ReadOnlyModuleBook> readModuleBook() throws DataConversionException, IOException {
        return readModuleBook(moduleBookStorage.getModuleBookFilePath());
    }

    @Override
    public Optional<ReadOnlyModuleBook> readModuleBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return moduleBookStorage.readModuleBook(filePath);
    }

    @Override
    public void saveModuleBook(ReadOnlyModuleBook moduleBook) throws IOException {
        saveModuleBook(moduleBook, moduleBookStorage.getModuleBookFilePath());
    }

    @Override
    public void saveModuleBook(ReadOnlyModuleBook moduleBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        moduleBookStorage.saveModuleBook(moduleBook, filePath);
    }

}
