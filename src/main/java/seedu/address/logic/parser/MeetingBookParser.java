package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListMeetingCommand;
import seedu.address.logic.commands.ViewMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class MeetingBookParser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandCategory>\\S+)"
            + "(?<commandVerb>\\s\\S+)?(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandCategory = matcher.group("commandCategory");
        final String commandVerb = matcher.group("commandVerb");
        final String commandWord = commandVerb != null ? commandCategory + commandVerb : commandCategory;
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);
        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);
        case ListMeetingCommand.COMMAND_WORD:
            return new ListMeetingCommand();
        case EditMeetingCommand.COMMAND_WORD:
            return new EditMeetingCommandParser().parse(arguments);
        case ViewMeetingCommand.COMMAND_WORD:
            return new ViewMeetingCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
