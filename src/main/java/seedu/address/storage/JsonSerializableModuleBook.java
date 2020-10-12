package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ModuleBook;
import seedu.address.model.ReadOnlyModuleBook;
import seedu.address.model.module.Module;

public class JsonSerializableModuleBook {

    public static final String MESSAGE_DUPLICATE_MEETING = "Module list contains duplicate module(s).";

    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    @JsonCreator
    public JsonSerializableModuleBook(@JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableModuleBook(ReadOnlyModuleBook source) {
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this module book into the model's {@code ModuleBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ModuleBook toModelType() throws IllegalValueException {
        ModuleBook moduleBook = new ModuleBook();
        for (JsonAdaptedModule jsonAdaptedModule : modules) {
            Module module = jsonAdaptedModule.toModelType();
            if (moduleBook.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEETING);
            }
            moduleBook.addModule(module);
        }
        return moduleBook;
    }

}
