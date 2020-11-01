package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteLabelCommand;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

public class DeleteLabelCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLabelCommand.MESSAGE_USAGE);

    private DeleteLabelCommandParser parser = new DeleteLabelCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no tags field specified
        assertParseFailure(parser, NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "James&" + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, VALID_NAME_AMY + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "James&" + INVALID_TAG_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = VALID_NAME_AMY + TAG_DESC_HUSBAND + TAG_DESC_FRIEND;
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag(VALID_TAG_HUSBAND));
        tags.add(new Tag(VALID_TAG_FRIEND));
        DeleteLabelCommand expectedCommand = new DeleteLabelCommand(new Name(VALID_NAME_AMY), tags);


        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyTags_failure() {
        String userInput = VALID_NAME_AMY + TAG_EMPTY;

        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }
}
