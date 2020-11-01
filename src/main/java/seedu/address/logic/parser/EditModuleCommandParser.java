package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Name;

public class EditModuleCommandParser implements Parser<EditModuleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditModuleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_NAME, PREFIX_PARTICIPANT);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditModuleCommand.MESSAGE_USAGE));
        }

        ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_MODULE).get());

        EditModuleCommand.EditModuleDescriptor editModuleDescriptor = new EditModuleCommand.EditModuleDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editModuleDescriptor.setModuleName(ParserUtil.parseModuleName(
                    argMultimap.getValue(PREFIX_NAME).get()));
        }

        parseMemberNamesForEdit(argMultimap.getAllValues(PREFIX_PARTICIPANT))
                .ifPresent(editModuleDescriptor::setMemberNames);

        if (!editModuleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditModuleCommand.MESSAGE_NOT_EDITED);
        }

        return new EditModuleCommand(moduleName, editModuleDescriptor);
    }

    /**
     * Parses {@code Collection<String> members} into a {@code Set<Person>} if {@code members} is non-empty.
     * If {@code members} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Person>} containing zero members.
     */
    private Optional<Set<Name>> parseMemberNamesForEdit(Collection<String> memberNames) throws ParseException {
        assert memberNames != null;

        if (memberNames.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> memberNamesSet = memberNames.size() == 1 && memberNames.contains("")
                ? Collections.emptySet() : memberNames;
        return Optional.of(ParserUtil.parseNames(memberNamesSet));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
