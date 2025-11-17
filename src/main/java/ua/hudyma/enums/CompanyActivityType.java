package ua.hudyma.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanyActivityType implements LabeledEnum {
    IT_SERVICES ("IT-Services"),
    IT_CONSULTING ("IT-Consulting");

    private final String label;
}
