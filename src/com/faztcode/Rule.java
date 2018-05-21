package com.faztcode;

class Rule
{

    private Interval lastDigits;
    private Interval[] hourIntervals;

    private static final int LAST_DIGIT = 9;
    public static int MAX_PER_DAY = 2;

    public Rule(Interval[] defaults)
    {
        lastDigits = new Interval(LAST_DIGIT);
        hourIntervals = defaults;
    }

    public boolean forbidden(int lastDigit, int elapsedTimeInSeconds)
    {
        return validateLastDigit(lastDigit) && validateTime(elapsedTimeInSeconds);
    }

    private boolean validateTime(int secondsElapsed)
    {
        boolean result = false;
        for (Interval i: hourIntervals) {
            if(i.belongs(secondsElapsed)) result = true;
        }
        return result;
    }

    private boolean validateLastDigit(int lastDigit)
    {
        return lastDigits.belongs(lastDigit);
    }

}
