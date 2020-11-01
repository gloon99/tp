package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Adds a meeting to the meeting book.
 */
public class AddModuleCommand extends Command {

    public static final String COMMAND_WORD = "module add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to the timetable. "
            + "Parameters: "
            + PREFIX_NAME + "MODULE NAME "
            + "[" + PREFIX_PARTICIPANT + "PARTICIPANTS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103 "
            + PREFIX_PARTICIPANT + "Jay "
            + PREFIX_PARTICIPANT + "Roy";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module is already in the timetable";
    public static final String MESSAGE_NONEXISTENT_PERSON = "The following person(s): %s are not in your contacts";

    private final ModuleName moduleName;
    private final Set<Name> nameList;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddModuleCommand(ModuleName moduleName, Set<Name> nameList) {
        requireNonNull(moduleName);
        requireNonNull(nameList);
        this.moduleName = moduleName;
        this.nameList = nameList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModuleName(moduleName)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        List<Name> nonExistentPersonNames = new ArrayList<>();
        for (Name name : nameList) {
            if (!model.hasPersonName(name)) {
                nonExistentPersonNames.add(name);
            }
        }

        if (!nonExistentPersonNames.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Name name : nonExistentPersonNames) {
                sb.append(name + ", ");
            }
            String nonExistentPersonNamesString = sb.substring(0, sb.length() - 2);
            throw new CommandException(String.format(MESSAGE_NONEXISTENT_PERSON, nonExistentPersonNamesString));
        }

        Set<Person> personList = new HashSet<>();
        for (Name name : nameList) {
            List<Person> filteredList = model.getFilteredPersonList().stream()
                    .filter(person -> person.isSameName(name)).collect(Collectors.toList());
            personList.addAll(filteredList);
        }

        Module toAdd = new Module(moduleName, personList);
        try {
            model.addModule(toAdd);
        } catch (DuplicateModuleException e) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMeetingCommand // instanceof handles nulls
                && moduleName.equals(((AddModuleCommand) other).moduleName)
                && nameList.equals(((AddModuleCommand) other).nameList));
    }
}
