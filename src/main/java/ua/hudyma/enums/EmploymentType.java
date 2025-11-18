package ua.hudyma.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EmploymentType implements LabeledEnum{
    FULL_TIME ("Full-time"),
    PART_TIME ("Part-time"),
    SELF_EMPLOYED("Self-Employed"),
    FREELANCE ("Freelance"),
    CONTRACT ("Contract"),
    TRAINEE ("Trainee"),
    TEMPORARY ("Temporary");

    private final String label;

    @Override
    public String getLabel() {
        return label;
    }
}
