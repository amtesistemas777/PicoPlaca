package com.faztcode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

class Predictor
{
    private Rule[] rules;
    private Scanner scanner;
    private Date dateCirculation;
    private LicensePlate licensePlate;

    public static final int RULES_PER_WEEK = 5;

    private static final String DATE_PATTERN = "yyyy-MM-dd' 'HH:mm";
    private static final String ERROR_PATTERN = "Sorry, that's not valid format. Please try again.";
    private static final String DATE_PATTERN_MESSAGE = "Please Enter a valid date and time ";
    private static final String LICENSE_PATTERN_MESSAGE = "Please enter a valid license plate ";

    public Predictor()
    {
        scanner = new Scanner(System.in);
        rules = new Rule[RULES_PER_WEEK];

        Interval[] defaults = new Interval[Rule.MAX_PER_DAY];
        defaults[0] = new Interval(
                timeToSeconds(7,0),
                timeToSeconds(9,29));

        defaults[1] = new Interval(
            timeToSeconds(16,0),
            timeToSeconds(19,29));

        for (int i = 0; i < RULES_PER_WEEK; i++) {
            rules[i] = new Rule(defaults);
        }
    }

    public void rushHour()
    {
        boolean result;

        do {

            readDateTime();
            readLicensePlate();

            result = rules[dayOfWeek(dateCirculation) - 2].forbidden(
                    licensePlate.getLastDigit(),
                    getElapsedSeconds(dateCirculation));

        } while (result);

    }

    private int dayOfWeek(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    private int getElapsedSeconds(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return timeToSeconds(
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE)
        );
    }

    private int timeToSeconds(int hours, int minutes)
    {
        return hours * 3600 + minutes * 60;
    }

    private void readDateTime()
    {
        String dateInput;
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

        do {
            System.out.println(DATE_PATTERN_MESSAGE + DATE_PATTERN);
            dateInput = scanner.nextLine();
            try {
                dateCirculation = dateFormat.parse(dateInput);
            } catch (ParseException e) {
                System.out.println(ERROR_PATTERN);
            }
        } while (dateCirculation == null || !dateInput.equals(dateFormat.format(dateCirculation)));

    }

    private void readLicensePlate()
    {
        String plate;
        boolean validator;
        do {
            System.out.println(LICENSE_PATTERN_MESSAGE + LicensePlate.PATTERN_EXAMPLE);
            plate = scanner.nextLine();
            validator = LicensePlate.validateFormat(plate);
        } while (validator == false);
        licensePlate = new LicensePlate(plate);
    }

}
