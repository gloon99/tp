package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleName;

public class ListModuleCommand extends Command {

    public static final String COMMAND_WORD = "module list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to the timetable. "
            + "Parameters: "
            + PREFIX_MODULE + "MODULE NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2103 ";

    public static final String MESSAGE_SUCCESS = "Listed Contacts in module";

    private final ModuleName moduleName;

    public ListModuleCommand(ModuleName moduleName) {
        this.moduleName = moduleName;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        if (moduleName.getModuleName().equals("clean")) {
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        } else {
            model.getPersonsInModule(moduleName);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
