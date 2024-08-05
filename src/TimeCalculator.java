package src;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class TimeCalculator {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public TimeCalculator() {
        frame = new JFrame("Time Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createAgePanel(), "Age Calculation");
        mainPanel.add(createTimeDiffPanel(), "Time Difference Calculation");
        mainPanel.add(createDateDiffPanel(), "Date Difference Calculation");
        mainPanel.add(createTimeZonePanel(), "Time Zone Calculation");
        mainPanel.add(createReminderPanel(), "Reminder Calculation");

        frame.add(createNavigationPanel(), BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createNavigationPanel() {
        JPanel navigationPanel = new JPanel(new GridLayout(1, 5));
        addButtonToPanel(navigationPanel, "Age Calculation", "Age Calculation");
        addButtonToPanel(navigationPanel, "Time Difference", "Time Difference Calculation");
        addButtonToPanel(navigationPanel, "Date Difference", "Date Difference Calculation");
        addButtonToPanel(navigationPanel, "Time Zone", "Time Zone Calculation");
        addButtonToPanel(navigationPanel, "Reminder", "Reminder Calculation");
        return navigationPanel;
    }

    private void addButtonToPanel(JPanel panel, String buttonText, String cardName) {
        JButton button = new JButton(buttonText);
        button.addActionListener(e -> cardLayout.show(mainPanel, cardName));
        panel.add(button);
    }

    private JPanel createAgePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel dateLabel = new JLabel("Enter Birthdate (YYYY-MM-DD):");
        JTextField dateField = new JTextField();
        JLabel resultLabel = new JLabel("Age: ");

        JButton calculateButton = new JButton("Calculate Age");
        calculateButton.addActionListener(e -> calculateAge(dateField, resultLabel));

        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        return panel;
    }

    private void calculateAge(JTextField dateField, JLabel resultLabel) {
        try {
            LocalDate birthDate = LocalDate.parse(dateField.getText());
            LocalDate currentDate = LocalDate.now();
            if (birthDate.isAfter(currentDate)) {
                throw new DateTimeParseException("Invalid date", dateField.getText(), 0);
            }
            Period period = Period.between(birthDate, currentDate);
            resultLabel.setText("Age: " + period.getYears() + " years, " + period.getMonths() + " months, "
                    + period.getDays() + " days");
        } catch (DateTimeParseException ex) {
            showErrorDialog("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private JPanel createTimeDiffPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel timeLabel1 = new JLabel("Enter Time 1 (HH:MM:SS):");
        JTextField timeField1 = new JTextField();
        JLabel timeLabel2 = new JLabel("Enter Time 2 (HH:MM:SS):");
        JTextField timeField2 = new JTextField();
        JLabel resultLabel = new JLabel("Time Difference: ");

        JButton calculateButton = new JButton("Calculate Time Difference");
        calculateButton.addActionListener(e -> calculateTimeDifference(timeField1, timeField2, resultLabel));

        panel.add(timeLabel1);
        panel.add(timeField1);
        panel.add(timeLabel2);
        panel.add(timeField2);
        panel.add(calculateButton);
        panel.add(resultLabel);

        return panel;
    }

    private void calculateTimeDifference(JTextField timeField1, JTextField timeField2, JLabel resultLabel) {
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime localTime1 = LocalTime.parse(timeField1.getText(), timeFormatter);
            LocalTime localTime2 = LocalTime.parse(timeField2.getText(), timeFormatter);
            if (localTime1.isAfter(localTime2)) {
                throw new DateTimeParseException("Invalid time", timeField1.getText(), 0);
            }
            long hours = ChronoUnit.HOURS.between(localTime1, localTime2);
            long minutes = ChronoUnit.MINUTES.between(localTime1, localTime2) % 60;
            long seconds = ChronoUnit.SECONDS.between(localTime1, localTime2) % 60;
            resultLabel.setText(
                    "Time Difference: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds");
        } catch (DateTimeParseException ex) {
            showErrorDialog("Invalid time format. Please use HH:MM:SS.");
        }
    }

    private JPanel createDateDiffPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel dateLabel1 = new JLabel("Enter Date 1 (YYYY-MM-DD):");
        JTextField dateField1 = new JTextField();
        JLabel dateLabel2 = new JLabel("Enter Date 2 (YYYY-MM-DD):");
        JTextField dateField2 = new JTextField();
        JLabel resultLabel = new JLabel("Date Difference: ");

        JButton calculateButton = new JButton("Calculate Date Difference");
        calculateButton.addActionListener(e -> calculateDateDifference(dateField1, dateField2, resultLabel));

        panel.add(dateLabel1);
        panel.add(dateField1);
        panel.add(dateLabel2);
        panel.add(dateField2);
        panel.add(calculateButton);
        panel.add(resultLabel);

        return panel;
    }

    private void calculateDateDifference(JTextField dateField1, JTextField dateField2, JLabel resultLabel) {
        try {
            LocalDate startDate = LocalDate.parse(dateField1.getText());
            LocalDate endDate = LocalDate.parse(dateField2.getText());
            if (startDate.isAfter(endDate)) {
                throw new DateTimeParseException("Invalid date", dateField1.getText(), 0);
            }
            Period period = Period.between(startDate, endDate);
            long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
            resultLabel.setText("Date Difference: " + period.getMonths() + " months, " + period.getDays()
                    + " days (Total days: " + totalDays + ")");
        } catch (DateTimeParseException ex) {
            showErrorDialog("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private JPanel createTimeZonePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel timeZoneLabel = new JLabel("Enter Time Zone (ID):");
        JTextField timeZoneField = new JTextField();
        JLabel resultLabel = new JLabel("Current Time: ");

        JButton calculateButton = new JButton("Calculate Time Zone");
        calculateButton.addActionListener(e -> calculateTimeZone(timeZoneField, resultLabel));

        panel.add(timeZoneLabel);
        panel.add(timeZoneField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        return panel;
    }

    private void calculateTimeZone(JTextField timeZoneField, JLabel resultLabel) {
        try {
            ZonedDateTime timeInZone = ZonedDateTime.now(ZoneId.of(timeZoneField.getText()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
            resultLabel.setText("Current Time: " + timeInZone.format(formatter));
        } catch (DateTimeException ex) {
            showErrorDialog("Invalid time zone ID. Please use a valid time zone ID.");
        }
    }

    private JPanel createReminderPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel reminderLabel = new JLabel("Enter Reminder Date (YYYY-MM-DD):");
        JTextField reminderField = new JTextField();
        JLabel resultLabel = new JLabel("Days until reminder: ");

        JButton calculateButton = new JButton("Calculate Days Until Reminder");
        calculateButton.addActionListener(e -> calculateReminder(reminderField, resultLabel));

        panel.add(reminderLabel);
        panel.add(reminderField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        return panel;
    }

    private void calculateReminder(JTextField reminderField, JLabel resultLabel) {
        try {
            LocalDate reminderLocalDate = LocalDate.parse(reminderField.getText());
            long daysUntilReminder = ChronoUnit.DAYS.between(LocalDate.now(), reminderLocalDate);
            resultLabel.setText("Days until reminder: " + daysUntilReminder);
        } catch (DateTimeParseException ex) {
            showErrorDialog("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TimeCalculator::new);
    }
}
