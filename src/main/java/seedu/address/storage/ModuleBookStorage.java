package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyModuleBook;

public interface ModuleBookStorage {

    Path getModuleBookFilePath();

    Optional<ReadOnlyModuleBook> readModuleBook() throws DataConversionException, IOException;

    Optional<ReadOnlyModuleBook> readModuleBook(Path filePath) throws DataConversionException, IOException;

    void saveModuleBook(ReadOnlyModuleBook moduleBook) throws IOException;

    void saveModuleBook(ReadOnlyModuleBook moduleBook, Path filePath) throws IOException;

}
