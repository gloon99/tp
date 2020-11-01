package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) for {@code AddMeetingCommand}.
 */
public class AddMeetingCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(),
            getTypicalModuleBook(), new UserPrefs());
    }

    /*
    @Test
    public void execute_newMeeting_success() {
        Meeting validMeeting = new MeetingBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getMeetingBook(), model.getModuleBook(),
            new UserPrefs());
        expectedModel.addMeeting(validMeeting);

        assertCommandSuccess(new AddMeetingCommand(validMeeting), model,
                String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), expectedModel);
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        Meeting meetingInList = model.getMeetingBook().getMeetingList().get(0);
        assertCommandFailure(new AddMeetingCommand(meetingInList), model, AddMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }
     */
}
