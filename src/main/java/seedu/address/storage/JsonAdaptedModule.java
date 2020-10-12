package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Person;

public class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String moduleName;
    private final List<JsonAdaptedPerson> memberList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("module name") String moduleName,
                              @JsonProperty("members") List<JsonAdaptedPerson> memberList) {
        this.moduleName = moduleName;
        if (memberList != null) {
            this.memberList.addAll(memberList);
        }
    }

    /**
     * Constructs a {@code JsonAdaptedModule} with the given meeting details.
     */
    public JsonAdaptedModule(Module source) {
        moduleName = source.getModuleName().getModuleName();
        memberList.addAll(source.getClassmates().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        final List<Person> members = new ArrayList<>();
        for (JsonAdaptedPerson person : memberList) {
            members.add(person.toModelType());
        }

        if (moduleName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleName.class.getSimpleName()));
        }
        if (!ModuleName.isValidModuleName(moduleName)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelName = new ModuleName(moduleName);

        final Set<Person> modelMembers = new HashSet<>(members);

        return new Module(modelName, modelMembers);
    }

}
