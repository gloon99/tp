package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Time;
import seedu.address.model.person.Person;

public class MeetingBuilder {
    public static final String DEFAULT_NAME = "CS2102";
    public static final String DEFAULT_DATE = "2020-10-03";
    public static final String DEFAULT_TIME = "10:00";

    private MeetingName meetingName;
    private Date date;
    private Time time;
    private Set<Person> persons;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public MeetingBuilder() {
        meetingName = new MeetingName(DEFAULT_NAME);
        date = new Date(DEFAULT_DATE);
        time = new Time(DEFAULT_TIME);
        persons = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        meetingName = meetingToCopy.getMeetingName();
        date = meetingToCopy.getDate();
        time = meetingToCopy.getTime();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public MeetingBuilder withName(String name) {
        this.meetingName = new MeetingName(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public MeetingBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public MeetingBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    public Meeting build() {
        return new Meeting(meetingName, date, time);
    }
}
