package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class MeetingNameContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public MeetingNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(meeting.getMeetingName().meetingName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MeetingNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
