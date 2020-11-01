package seedu.address.ui;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TimelineSection extends UiPart<Region> {
    private static final String FXML = "TimelineSection.fxml";

    @FXML
    private StackPane meetingCard;
    @FXML
    private VBox vbox;

    private final TimelineMeetingCard timelineMeetingCard;

    TimelineSection(TimelineMeetingCard timelineMeetingCard) {
        super(FXML);
        this.timelineMeetingCard = timelineMeetingCard;
        meetingCard.getChildren().add(timelineMeetingCard.getRoot());
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(vbox.getChildren());
        Collections.reverse(tmp);
        vbox.getChildren().setAll(tmp);
    }

    public static TimelineSection getFlipped(TimelineSection timelineSection) {
        timelineSection.flip();
        return timelineSection;
    }

    private void setOverdue() {
        timelineMeetingCard.setOverdue();
    }
    public static TimelineSection getOverdue(TimelineSection timelineSection) {
        timelineSection.setOverdue();
        return timelineSection;
    }
}
