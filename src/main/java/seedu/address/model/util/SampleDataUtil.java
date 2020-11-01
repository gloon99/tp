package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.ModuleBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.ReadOnlyModuleBook;
import seedu.address.model.commons.SpecialName;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Time;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                getTagSet("prof")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                getTagSet("ta")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                getTagSet("colleagues"))
        };
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new ModuleName("CS2103"), getPersonSet("Alex Yeoh", "Bernice Yu")),
            new Module(new ModuleName("CS2105"), getPersonSet("Bernice Yu", "David Li")),
            new Module(new ModuleName("CS2040"), getPersonSet("David Li", "Charlotte Oliveiro")),
            new Module(new ModuleName("CS2100"), getPersonSet("Roy Balakrishnan", "Bernice Yu"))
        };
    }

    public static Meeting[] getSampleMeetings() {
        return new Meeting[] {
            new Meeting(getModule("CS2103"),
                    new MeetingName("Weekly Meeting"),
                    new Date("2020-09-20"),
                    new Time("10:00"),
                    getPersonSet("Alex Yeoh", "Bernice Yu"),
                    getSpecialNameSet("Discuss observer pattern."),
                    getSpecialNameSet("Refer to textbook.")),
            new Meeting(getModule("CS2105"),
                    new MeetingName("Project Meeting"),
                    new Date("2020-10-19"),
                    new Time("17:30"),
                    getPersonSet("Bernice Yu", "David Li"),
                    getSpecialNameSet("Discuss application layer."),
                    getSpecialNameSet("Talk about http and dns.")),
            new Meeting(getModule("CS2040"),
                    new MeetingName("Emergency Meeting"),
                    new Date("2020-10-10"),
                    new Time("16:00"),
                    getPersonSet("David Li", "Charlotte Oliveiro"),
                    getSpecialNameSet("Resolve merge conflicts."),
                    getSpecialNameSet("Bug with my commit")),
            new Meeting(getModule("CS2100"),
                    new MeetingName("Report Discussion"),
                    new Date("2020-09-08"),
                    new Time("08:00"),
                    getPersonSet("Roy Balakrishnan", "Bernice Yu"),
                    getSpecialNameSet("Discuss about pipelining", "Change the presentation slides",
                            "Add more content about cache concepts"),
                    getSpecialNameSet("Arrange for consult if needed."))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a special name (Agenda or Notes) set containing the list of strings given.
     */
    public static Set<SpecialName> getSpecialNameSet(String... strings) {
        return Arrays.stream(strings)
                .map(SpecialName::new)
                .collect(Collectors.toSet());
    }

    public static ReadOnlyMeetingBook getSampleMeetingBook() {
        MeetingBook sampleMb = new MeetingBook();
        for (Meeting sampleMeeting : getSampleMeetings()) {
            sampleMb.addMeeting(sampleMeeting);
        }
        return sampleMb;
    }

    public static ReadOnlyModuleBook getSampleModuleBook() {
        ModuleBook sampleMb = new ModuleBook();
        for (Module sampleModule : getSampleModules()) {
            sampleMb.addModule(sampleModule);
        }
        return sampleMb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Person> getPersonSet(String... strings) {
        Set<Person> personSet = new HashSet<>();
        for (String string : strings) {
            Name name = new Name(string);
            for (Person person : getSamplePersons()) {
                if (person.isSameName(name)) {
                    personSet.add(person);
                }
            }
        }
        return personSet;
    }

    public static Module getModule(String string) {
        for (Module module: getSampleModules()) {
            if (module.isSameName(new ModuleName(string))) {
                return module;
            }
        }
        return null;
    }
}
