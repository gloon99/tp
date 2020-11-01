package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICIA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.MeetingBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

public class TypicalMeetings {

    public static final Meeting CS2103_NO_MEMBERS = new MeetingBuilder().withName("CS2103")
            .withDate("2020-10-07")
            .withTime("10:00")
            .withMembers(new HashSet<Person>(Arrays.asList(ALICE)))
            .build();

    public static final Meeting CS2103 = new MeetingBuilder().withName("CS2103")
            .withDate("2020-10-07")
            .withTime("10:00")
            .build();

    // Edited name from Alice Pauline to Alicia
    public static final Meeting CS2103_EDITED_MEMBER = new MeetingBuilder().withName("CS2103")
            .withDate("2020-10-07")
            .withTime("10:00")
            .withMembers(new HashSet<Person>(Arrays.asList(ALICIA)))
            .build();

    /*
    // Manually added
    public static final Meeting A = new MeetingBuilder().withName("Meeting A").withDate("8482424")
            .withEmail("stefan@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalMeetings() {} // prevents instantiation
     */
    // meeting has no members
    public static MeetingBook getTypicalMeetingBook() {
        MeetingBook mb = new MeetingBook();
        for (Meeting meeting : getTypicalMeetings()) {
            mb.addMeeting(meeting);
        }
        return mb;
    }

    // meeting has one member
    public static MeetingBook getTypicalMeetingBookWithMember() {
        MeetingBook mb = new MeetingBook();
        for (Meeting meeting : getTypicalMeetingsWithMembers()) {
            mb.addMeeting(meeting);
        }
        return mb;
    }

    // meeting has edited member
    public static MeetingBook getTypicalMeetingBookWithEditedMember() {
        MeetingBook mb = new MeetingBook();
        for (Meeting meeting : getTypicalMeetingsWithEditedMember()) {
            mb.addMeeting(meeting);
        }
        return mb;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(CS2103));
    }
    public static List<Meeting> getTypicalMeetingsWithMembers() {
        return new ArrayList<>(Arrays.asList(CS2103_NO_MEMBERS));
    }
    public static List<Meeting> getTypicalMeetingsWithEditedMember() {
        return new ArrayList<>(Arrays.asList(CS2103_EDITED_MEMBER));
    }
}
