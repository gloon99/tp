package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

public class Meeting {
    // Identity fields
    private final MeetingName meetingName;
    private final Date date;
    private final Time time;
    private Set<Person> participants;

    /**
     * Every field must be present and not null.
     */
    public Meeting(MeetingName name, Date date, Time time) {
        requireAllNonNull(name, date, time);
        this.meetingName = name;
        this.date = date;
        this.time = time;

        this.participants = new HashSet<>();

        HashSet<Person> persons = new HashSet<>(Arrays.asList(SampleDataUtil.getSamplePersons()));
        participants.addAll(persons);
    }

    public MeetingName getMeetingName() {
        return this.meetingName;
    }

    public Date getDate() {
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }

    public Set<Person> getParticipants() {
        return participants;
    }

    /**
     * Returns true if both meetings have the same name, date and time.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getMeetingName().equals(getMeetingName())
                && otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return otherMeeting.getMeetingName().equals(getMeetingName())
                && otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(meetingName, date, time);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getMeetingName())
                .append(" Date: ")
                .append(getDate())
                .append(" Time: ")
                .append(getTime());
        return builder.toString();
    }
}
