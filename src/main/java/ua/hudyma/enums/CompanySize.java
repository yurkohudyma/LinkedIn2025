package ua.hudyma.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanySize {
    SIZE_1_10(1, 10, "1–10 employees"),
    SIZE_11_50(11, 50, "11–50 employees"),
    SIZE_51_200(51, 200, "51–200 employees"),
    SIZE_201_500(201, 500, "201–500 employees"),
    SIZE_501_1000(501, 1000, "501–1,000 employees"),
    SIZE_1001_5000(1001, 5000, "1,001–5,000 employees"),
    SIZE_5001_10000(5001, 10000, "5,001–10,000 employees"),
    SIZE_10001_PLUS(10001, Integer.MAX_VALUE, "10,001+ employees");

    private final int min;
    private final int max;
    private final String label;

    /** Пошук мінливого діапазону за числом працівників */
    public static CompanySize fromEmployeeCount(int count) {
        for (CompanySize size : values()) {
            if (count >= size.min && count <= size.max) {
                return size;
            }
        }
        throw new IllegalArgumentException("Unknown employee count: " + count);
    }
}

