package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.commons.SpecialName;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

public class Meeting {
    // Identity fields
    private final Module module;
    private final MeetingName meetingName;

    //Data fields
    private final Date date;
    private final Time time;
    private final Set<Person> members = new HashSet<>();
    private final Set<SpecialName> agendas = new HashSet<>();
    private final Set<SpecialName> notes = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Meeting(Module module, MeetingName name, Date date, Time time, Set<Person> members,
                   Set<SpecialName> agendas, Set<SpecialName> notes) {
        requireAllNonNull(module, name, date, time, members);
        this.module = module;
        this.meetingName = name;
        this.date = date;
        this.time = time;
        this.members.addAll(members);
        this.agendas.addAll(agendas);
        this.notes.addAll(notes);
    }

    public Module getModule() {
        return this.module;
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
        return Collections.unmodifiableSet(members);
    }

    public Set<SpecialName> getAgendas() {
        return Collections.unmodifiableSet(agendas);
    }

    public Set<SpecialName> getNotes() {
        return Collections.unmodifiableSet(notes);
    }

    public List<SpecialName> getAgendasAsList() {
        return new ArrayList<>(agendas);
    }

    public List<SpecialName> getNotesAsList() {
        return new ArrayList<>(notes);
    }

    /**
     * Returns true if both meetings have the same name, date and time.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getModule().equals(getModule())
                && otherMeeting.getMeetingName().equals(getMeetingName());
    }

    public boolean isSameMeetingName(MeetingName otherMeetingName) {
        return meetingName.equals(otherMeetingName);
    }

    public String getBracketNotation() {
        return "[" + this.module.getModuleName() + "] " + this.meetingName;
    }

    /**
     * Returns true if both meetings have the same identity and data fields.
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
        return otherMeeting.getModule().equals(getModule())
                && otherMeeting.getMeetingName().equals(getMeetingName())
                && otherMeeting.getDate().equals(getDate())
                && otherMeeting.getTime().equals(getTime())
                && otherMeeting.getParticipants().equals(getParticipants());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(module, meetingName, date, time, members);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[" + getModule().getModuleName() + "] ")
                .append(getMeetingName())
                .append(" Date: ")
                .append(getDate())
                .append(" Time: ")
                .append(getTime())
                .append(" Members: ");
        getParticipants().forEach(member -> builder.append(member.getName() + ", "));
        builder.append(" Agendas: ");
        getAgendas().forEach(agenda -> builder.append(agenda.toString() + ", "));
        builder.append(" Notes: ");
        getNotes().forEach(note -> builder.append(note.toString() + ", "));
        return builder.substring(0, builder.length() - 2);
    }
}
