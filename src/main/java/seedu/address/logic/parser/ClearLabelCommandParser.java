package seedu.address.logic.parser;

import seedu.address.logic.commands.ClearLabelCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class ClearLabelCommandParser implements Parser<ClearLabelCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ClearLabelCommand
     * and returns a ClearLabelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearLabelCommand parse(String args) throws ParseException {
        Name targetName = ParserUtil.parseName(args);
        return new ClearLabelCommand(targetName);
    }
}
