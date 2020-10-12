package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingName;
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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME, PREFIX_MEMBER);

        MeetingName meetingName;

        try {
            meetingName = ParserUtil.parseMeetingName(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor = new EditMeetingCommand.EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editMeetingDescriptor.setMeetingName(ParserUtil.parseMeetingName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editMeetingDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            editMeetingDescriptor.setTime(ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get()));
        }

        parseMemberNamesForEdit(argMultimap.getAllValues(PREFIX_MEMBER))
                .ifPresent(editMeetingDescriptor::setMemberNames);

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(meetingName, editMeetingDescriptor);
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

}
