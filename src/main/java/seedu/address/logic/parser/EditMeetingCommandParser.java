package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGENDA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Name;

public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_NAME, PREFIX_NEWNAME, PREFIX_DATE,
                        PREFIX_TIME, PREFIX_PARTICIPANT, PREFIX_AGENDA, PREFIX_NOTE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditMeetingCommand.MESSAGE_USAGE));
        }

        ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(PREFIX_MODULE).get());
        MeetingName meetingName = ParserUtil.parseMeetingName(argMultimap.getValue(PREFIX_NAME).get());

        EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor = new EditMeetingCommand.EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_NEWNAME).isPresent()) {
            editMeetingDescriptor.setMeetingName(ParserUtil.parseMeetingName(
                    argMultimap.getValue(PREFIX_NEWNAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editMeetingDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            editMeetingDescriptor.setTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_AGENDA).isPresent()) {
            editMeetingDescriptor.setAgendas(ParserUtil.parseSpecialNames(argMultimap.getAllValues(PREFIX_AGENDA)));
        }
        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            editMeetingDescriptor.setNotes(ParserUtil.parseSpecialNames(argMultimap.getAllValues(PREFIX_NOTE)));
        }

        parseMemberNamesForEdit(argMultimap.getAllValues(PREFIX_PARTICIPANT))
                .ifPresent(editMeetingDescriptor::setMemberNames);

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(moduleName, meetingName, editMeetingDescriptor);
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
