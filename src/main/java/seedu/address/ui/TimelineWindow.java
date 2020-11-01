package seedu.address.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.Meeting;

/**
 * Controller for a help page
 */
public class TimelineWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(TimelineWindow.class);
    private static final String FXML = "TimelineWindow.fxml";

    private Logic logic;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox container;

    /**
     * Creates a new TimelineWindow.
     *
     * @param root Stage to use as the root of the TimelineWindow.
     */
    public TimelineWindow(Stage root, Logic logic) {
        super(FXML, root);

        this.logic = logic;

        for (int i = 0; i < logic.getFilteredMeetingList().size(); i++) {
            Meeting meeting = logic.getFilteredMeetingList().get(i);
            TimelineSection timelineSection = new TimelineSection(new TimelineMeetingCard(meeting));
            Date date = new Date(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            if (i % 2 == 1) {
                timelineSection = TimelineSection.getFlipped(timelineSection);
            }
            if (LocalDate.now().compareTo(meeting.getDate().getLocalDateFormat()) > 0) {
                timelineSection = TimelineSection.getOverdue(timelineSection);
            } else if ((LocalDate.now().compareTo(meeting.getDate().getLocalDateFormat()) == 0)
                && (LocalTime.now().compareTo(meeting.getTime().getLocalTimeFormat()) >= 0)) {
                timelineSection = TimelineSection.getOverdue(timelineSection);
            }
            container.getChildren().addAll(timelineSection.getRoot());
        }
    }

    /**
     * Creates a new TimelineWindow.
     */
    public TimelineWindow(Logic logic) {
        this(new Stage(), logic);
    }

    public TimelineWindow updateLogic(Logic logic) {
        return new TimelineWindow(logic);
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing timeline view of the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
