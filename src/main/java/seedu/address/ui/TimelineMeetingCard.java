package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TimelineMeetingCard extends UiPart<Region> {

    private static final String FXML = "TimelineMeetingCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Meeting meeting;

    @FXML
    private HBox cardPane;
    @FXML
    private Label module;
    @FXML
    private Label name;
    @FXML
    private Label time;
    @FXML
    private Label date;
    @FXML
    private FlowPane participants;
    @FXML
    private VBox vbox;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TimelineMeetingCard(Meeting meeting) {
        super(FXML);
        this.meeting = meeting;
        module.setText("[" + meeting.getModule().getModuleName() + "] ");
        name.setText(meeting.getMeetingName().meetingName);
        time.setText(meeting.getTime().toString());
        date.setText(meeting.getDate().toString());
        meeting.getParticipants().stream()
                .sorted(Comparator.comparing(participant -> participant.getName().fullName))
                .forEach(participant -> participants.getChildren()
                        .add(new Label(participant.getName().getFirstName())));
    }

    public void setOverdue() {
        vbox.setStyle("-fx-background-color: rgb(102, 0, 0);"
                + "-fx-background-radius: 20;"
                + "-fx-border-color: white;"
                + "-fx-border-radius: 20;");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimelineMeetingCard)) {
            return false;
        }

        // state check
        TimelineMeetingCard card = (TimelineMeetingCard) other;
        return name.getText().equals(card.name.getText())
                && module.getText().equals(card.module.getText())
                && meeting.equals(card.meeting);
    }
}
