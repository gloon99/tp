package seedu.address.ui;

import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class MeetingDetailsPanel extends UiPart<Region> {

    private static final String FXML = "MeetingDetailsPanel.fxml";

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
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label time;
    @FXML
    private Label date;
    @FXML
    private FlowPane participants;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab agendaTab;
    @FXML
    private Tab notesTab;
    @FXML
    private ListView agendas;
    @FXML
    private ListView notes;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public MeetingDetailsPanel(Meeting meeting, int displayedIndex) {
        super(FXML);
        agendaTab.setText("Agenda");
        notesTab.setText("Notes");
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        name.setText(meeting.getBracketNotation());
        time.setText(meeting.getTime().toString());
        date.setText(meeting.getDate().toString());
        agendas.setItems(FXCollections.observableList(meeting.getAgendasAsList()));
        notes.setItems(FXCollections.observableList(meeting.getNotesAsList()));
        meeting.getParticipants().stream()
                .sorted(Comparator.comparing(participant -> participant.getName().fullName))
                .forEach(participant -> participants.getChildren()
                        .add(new Label(participant.getName().getFirstName())));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingDetailsPanel)) {
            return false;
        }

        // state check
        MeetingDetailsPanel card = (MeetingDetailsPanel) other;
        return id.getText().equals(card.id.getText())
                && meeting.equals(card.meeting);
    }
}
