package com.faztcode;

class LicensePlate
{

    private String value;

    private static final int INDEX_ALPHABETIC = 0;
    private static final int INDEX_NUMERIC = 1;
    private static final int MAX_NUM_CHARACTERS = 3;
    private static final int MAX_NUM_DIGITS = 3;

    public static final String PATTERN_EXAMPLE = "PBR-4373 OR PBR-437";
    public static final String DIVISION_CHARACTER = "-";
    public static final String LICENSE_PLATE_PATTERN = "^[a-zA-Z0-9-]*$";
    public static final String ALPHABETICAL_PATTERN = "^[a-zA-Z]*$";
    public static final String NUMERIC_PATTERN = "^[0-9]*$";

    public LicensePlate(String value)
    {
        this.value = value;
    }

    public static boolean validateFormat(String plate)
    {
        boolean result = false;
        if (!plate.matches(LICENSE_PLATE_PATTERN) || !plate.contains(DIVISION_CHARACTER)) {
            return result;
        }

        if (plate.split(DIVISION_CHARACTER).length > 2) {
            return result;
        }

        if (isAlphabetValue(plate.split(DIVISION_CHARACTER)[INDEX_ALPHABETIC])
                && isNumericValue(plate.split(DIVISION_CHARACTER)[INDEX_NUMERIC])) {
            result = true;
        }
        return result;
    }

    private static boolean isAlphabetValue(String value)
    {
        return value.matches(ALPHABETICAL_PATTERN)
                && value.length() == MAX_NUM_CHARACTERS;
    }

    private static boolean isNumericValue(String value)
    {
        Interval intervalNumericLength = new Interval(MAX_NUM_DIGITS,MAX_NUM_DIGITS + 1);
        return value.matches(NUMERIC_PATTERN)
                && (intervalNumericLength.belongs(value.length()));
    }

    private String getNumericValue()
    {
        return value.split(DIVISION_CHARACTER)[INDEX_NUMERIC];
    }

    public int getLastDigit()
    {
        String numeric = getNumericValue();
        int lastDigit = Integer.valueOf(numeric.substring(numeric.length() - 1));
        return lastDigit;
    }

}
