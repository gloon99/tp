package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.SpecialName;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Time;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;

public class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final JsonAdaptedModule module;
    private final String meetingName;
    private final String date;
    private final String time;
    private final List<JsonAdaptedPerson> memberList = new ArrayList<>();
    private final List<String> agendaList = new ArrayList<>();
    private final List<String> noteList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("module") JsonAdaptedModule module,
                              @JsonProperty("meeting name") String meetingName,
                              @JsonProperty("date") String date,
                              @JsonProperty("time") String time,
                              @JsonProperty("members") List<JsonAdaptedPerson> memberList,
                              @JsonProperty("agendas") List<String> agendaList,
                              @JsonProperty("notes") List<String> noteList) {
        this.module = module;
        this.meetingName = meetingName;
        this.date = date;
        this.time = time;
        if (memberList != null) {
            this.memberList.addAll(memberList);
        }
        if (agendaList != null) {
            this.agendaList.addAll(agendaList);
        }
        if (noteList != null) {
            this.noteList.addAll(noteList);
        }
    }

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    public JsonAdaptedMeeting(Meeting source) {
        module = new JsonAdaptedModule(source.getModule());
        meetingName = source.getMeetingName().meetingName;
        date = source.getDate().value;
        time = source.getTime().value;
        memberList.addAll(source.getParticipants().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
        agendaList.addAll(source.getAgendas().stream()
                .map(SpecialName::toString).collect(Collectors.toList()));
        noteList.addAll(source.getNotes().stream()
                .map(SpecialName::toString).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        final List<Person> members = new ArrayList<>();
        for (JsonAdaptedPerson person : memberList) {
            members.add(person.toModelType());
        }

        final List<SpecialName> agendas = new ArrayList<>();
        for (String agenda : agendaList) {
            agendas.add(new SpecialName(agenda));
        }

        final List<SpecialName> notes = new ArrayList<>();
        for (String note : noteList) {
            notes.add(new SpecialName(note));
        }

        Module modelModule = module.toModelType();

        if (meetingName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                            MeetingName.class.getSimpleName()));
        }
        if (!MeetingName.isValidMeetingName(meetingName)) {
            throw new IllegalValueException(MeetingName.MESSAGE_CONSTRAINTS);
        }
        final MeetingName modelName = new MeetingName(meetingName);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }
        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(Time.MESSAGE_CONSTRAINTS);
        }
        final Time modelTime = new Time(time);

        final Set<Person> modelMembers = new HashSet<>(members);

        final Set<SpecialName> modelAgendas = new HashSet<>(agendas);

        final Set<SpecialName> modelNotes = new HashSet<>(notes);

        return new Meeting(modelModule, modelName, modelDate, modelTime, modelMembers, modelAgendas, modelNotes);
    }

}
