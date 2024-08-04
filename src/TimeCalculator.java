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

    private JTextField birthdateField;
    private JLabel ageLabel;

    private JTextField timeField1;
    private JTextField timeField2;
    private JLabel timeDifferenceLabel;

    private final java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter
            .ofPattern("yyyy-MM-dd");
    private final java.time.format.DateTimeFormatter timeFormatter = java.time.format.DateTimeFormatter
            .ofPattern("HH:mm:ss");

    public TimeCalculator() {
        frame = new JFrame("Time Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new GridLayout(2, 1));

        agePanel = new JPanel(new GridLayout(3, 2));
        timePanel = new JPanel(new GridLayout(3, 2));

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

        frame.add(agePanel);
        frame.add(timePanel);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimeCalculator();
            }
        });
    }
}
