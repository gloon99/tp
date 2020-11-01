package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.commons.SpecialName;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Time;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class MeetingBuilder {
    public static final String DEFAULT_MODULE = "CS2103";
    public static final String DEFAULT_NAME = "Weekly Meeting";
    public static final String DEFAULT_DATE = "2020-10-03";
    public static final String DEFAULT_TIME = "10:00";
    public static final String DEFAULT_MEMBERS = "Alex Yeoh";

    private Module module;
    private MeetingName meetingName;
    private Date date;
    private Time time;
    private Set<Person> members;
    private Set<SpecialName> agendas = new HashSet<>();
    private Set<SpecialName> notes = new HashSet<>();

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public MeetingBuilder() {
        module = SampleDataUtil.getModule(DEFAULT_MODULE);
        meetingName = new MeetingName(DEFAULT_NAME);
        date = new Date(DEFAULT_DATE);
        time = new Time(DEFAULT_TIME);
        members = new HashSet<>();
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        meetingName = meetingToCopy.getMeetingName();
        date = meetingToCopy.getDate();
        time = meetingToCopy.getTime();
    }

    /**
     * Sets the {@code Module} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withModule(Module module) {
        this.module = module;
        return this;
    }

    /**
     * Sets the {@code MeetingName} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withName(String name) {
        this.meetingName = new MeetingName(name);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Sets the {@code Members} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withMembers(Set<Person> members) {
        this.members = members;
        return this;
    }

    public Meeting build() {
        return new Meeting(module, meetingName, date, time, members, agendas, notes);
    }
}
