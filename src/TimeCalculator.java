package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;
import java.time.Duration;

public class TimeCalculator {

    private JFrame frame;

    private JPanel agePanel;
    private JPanel timePanel;
    private JPanel datePanel;

    private JTextField birthdateField;
    private JLabel ageLabel;

    private JTextField timeField1;
    private JTextField timeField2;
    private JLabel timeDifferenceLabel;

    private JTextField dateField1;
    private JTextField dateField2;
    private JLabel dateDifferenceLabel;

    private final java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter
            .ofPattern("yyyy-MM-dd");
    private final java.time.format.DateTimeFormatter timeFormatter = java.time.format.DateTimeFormatter
            .ofPattern("HH:mm:ss");

    public TimeCalculator() {
        frame = new JFrame("Time Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(3, 1));

        agePanel = new JPanel(new GridLayout(3, 2));
        timePanel = new JPanel(new GridLayout(3, 2));
        datePanel = new JPanel(new GridLayout(3, 2));

        agePanel.add(new JLabel("Enter Birthdate (YYYY-MM-DD):"));
        birthdateField = new JTextField();
        agePanel.add(birthdateField);

        JButton calculateAgeButton = new JButton("Calculate Age");
        agePanel.add(calculateAgeButton);

        ageLabel = new JLabel(" ");
        agePanel.add(ageLabel);

        timePanel.add(new JLabel("Enter Time 1 (HH:MM:SS):"));
        timeField1 = new JTextField();
        timePanel.add(timeField1);

        timePanel.add(new JLabel("Enter Time 2 (HH:MM:SS):"));
        timeField2 = new JTextField();
        timePanel.add(timeField2);

        JButton calculateTimeButton = new JButton("Calculate Time Difference");
        timePanel.add(calculateTimeButton);

        timeDifferenceLabel = new JLabel(" ");
        timePanel.add(timeDifferenceLabel);

        datePanel.add(new JLabel("Enter Date 1 (YYYY-MM-DD):"));
        dateField1 = new JTextField();
        datePanel.add(dateField1);

        datePanel.add(new JLabel("Enter Date 2 (YYYY-MM-DD):"));
        dateField2 = new JTextField();
        datePanel.add(dateField2);

        JButton calculateDateButton = new JButton("Calculate Date Difference");
        datePanel.add(calculateDateButton);

        dateDifferenceLabel = new JLabel(" ");
        datePanel.add(dateDifferenceLabel);

        frame.add(agePanel);
        frame.add(timePanel);
        frame.add(datePanel);

        calculateAgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAge();
            }
        });

        calculateTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTimeDifference();
            }
        });

        calculateDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateDateDifference();
            }
        });

        frame.setVisible(true);
    }

    private void calculateAge() {
        String birthdateText = birthdateField.getText();
        try {
            LocalDate birthdate = LocalDate.parse(birthdateText, dateFormatter);
            LocalDate today = LocalDate.now();

            Period period = Period.between(birthdate, today);

            if (birthdate.isAfter(today)) {
                ageLabel.setText("Birthdate cannot be in the future.");
            } else {
                ageLabel.setText(String.format("Age: %d years, %d months, %d days",
                        period.getYears(), period.getMonths(), period.getDays()));
            }
        } catch (DateTimeParseException e) {
            ageLabel.setText("Invalid date format. Please use YYYY-MM-DD (e.g., 2005-04-17).");
        } catch (Exception e) {
            ageLabel.setText("An error occurred while calculating age.");
        }
    }

    private void calculateTimeDifference() {
        String time1Text = timeField1.getText();
        String time2Text = timeField2.getText();
        try {
            LocalTime time1 = LocalTime.parse(time1Text, timeFormatter);
            LocalTime time2 = LocalTime.parse(time2Text, timeFormatter);

            Duration duration = Duration.between(time1, time2);
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            long seconds = duration.getSeconds() % 60;

            timeDifferenceLabel.setText(
                    String.format("Time Difference: %d hours, %d minutes, %d seconds", hours, minutes, seconds));
        } catch (DateTimeParseException e) {
            timeDifferenceLabel.setText("Invalid time format. Please use HH:MM:SS.");
        } catch (Exception e) {
            timeDifferenceLabel.setText("An error occurred while calculating time difference.");
        }
    }

    private void calculateDateDifference() {
        String date1Text = dateField1.getText();
        String date2Text = dateField2.getText();
        try {
            LocalDate date1 = LocalDate.parse(date1Text, dateFormatter);
            LocalDate date2 = LocalDate.parse(date2Text, dateFormatter);

            Period period = Period.between(date1, date2);

            if (date1.isAfter(date2)) {
                dateDifferenceLabel.setText("Date 1 cannot be after Date 2.");
            } else {
                long days = period.getDays();
                long months = period.getMonths();
                long weeks = days / 7;
                dateDifferenceLabel.setText(
                        String.format("Date Difference: %d months, %d weeks, %d days", months, weeks, days % 7));
            }
        } catch (DateTimeParseException e) {
            dateDifferenceLabel.setText("Invalid date format. Please use YYYY-MM-DD.");
        } catch (Exception e) {
            dateDifferenceLabel.setText("An error occurred while calculating date difference.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimeCalculator();
            }
        });
    }
}
