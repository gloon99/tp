package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Module module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private FlowPane participants;
    @FXML
    private FlowPane teachingStaff;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ModuleCard(Module module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        name.setText(module.getModuleName().getModuleName());
        module.getClassmates().stream()
                .filter(participant -> participant.getPersonType() == Person.PersonType.CONTACT)
                .sorted(Comparator.comparing(participant -> participant.getName().fullName))
                .forEach(participant -> participants.getChildren()
                        .add(new Label(participant.getName().getFirstName())));
        module.getClassmates().stream()
                .filter(participant -> participant.getPersonType() == Person.PersonType.PROFESSOR)
                .sorted(Comparator.comparing(participant -> participant.getName().fullName))
                .forEach(participant -> teachingStaff.getChildren()
                        .add(new Label("Professor: " + participant.getName().getFirstName() + "  ")));
        module.getClassmates().stream()
                .filter(participant -> participant.getPersonType() == Person.PersonType.TA)
                .sorted(Comparator.comparing(participant -> participant.getName().fullName))
                .forEach(participant -> teachingStaff.getChildren()
                        .add(new Label("TA: " + participant.getName().getFirstName() + "  ")));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return id.getText().equals(card.id.getText())
                && module.equals(card.module);
    }
}
