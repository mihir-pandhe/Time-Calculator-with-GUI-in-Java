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

        JPanel agePanel = createAgePanel();
        JPanel timeDiffPanel = createTimeDiffPanel();
        JPanel dateDiffPanel = createDateDiffPanel();
        JPanel timeZonePanel = createTimeZonePanel();
        JPanel reminderPanel = createReminderPanel();

        mainPanel.add(agePanel, "Age Calculation");
        mainPanel.add(timeDiffPanel, "Time Difference Calculation");
        mainPanel.add(dateDiffPanel, "Date Difference Calculation");
        mainPanel.add(timeZonePanel, "Time Zone Calculation");
        mainPanel.add(reminderPanel, "Reminder Calculation");

        JPanel navigationPanel = new JPanel(new GridLayout(1, 5));
        JButton ageButton = new JButton("Age Calculation");
        JButton timeDiffButton = new JButton("Time Difference");
        JButton dateDiffButton = new JButton("Date Difference");
        JButton timeZoneButton = new JButton("Time Zone");
        JButton reminderButton = new JButton("Reminder");

        ageButton.addActionListener(e -> cardLayout.show(mainPanel, "Age Calculation"));
        timeDiffButton.addActionListener(e -> cardLayout.show(mainPanel, "Time Difference Calculation"));
        dateDiffButton.addActionListener(e -> cardLayout.show(mainPanel, "Date Difference Calculation"));
        timeZoneButton.addActionListener(e -> cardLayout.show(mainPanel, "Time Zone Calculation"));
        reminderButton.addActionListener(e -> cardLayout.show(mainPanel, "Reminder Calculation"));

        navigationPanel.add(ageButton);
        navigationPanel.add(timeDiffButton);
        navigationPanel.add(dateDiffButton);
        navigationPanel.add(timeZoneButton);
        navigationPanel.add(reminderButton);

        frame.setLayout(new BorderLayout());
        frame.add(navigationPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createAgePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel dateLabel = new JLabel("Enter Birthdate (YYYY-MM-DD):");
        JTextField dateField = new JTextField();
        JLabel resultLabel = new JLabel("Age: ");

        JButton calculateButton = new JButton("Calculate Age");
        calculateButton.addActionListener(e -> {
            try {
                String inputDate = dateField.getText();
                LocalDate birthDate = LocalDate.parse(inputDate);
                LocalDate currentDate = LocalDate.now();
                if (birthDate.isAfter(currentDate)) {
                    throw new DateTimeParseException("Invalid date", inputDate, 0);
                }
                Period period = Period.between(birthDate, currentDate);
                resultLabel.setText("Age: " + period.getYears() + " years, " + period.getMonths() + " months, "
                        + period.getDays() + " days");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        return panel;
    }

    private JPanel createTimeDiffPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel timeLabel1 = new JLabel("Enter Time 1 (HH:MM:SS):");
        JTextField timeField1 = new JTextField();
        JLabel timeLabel2 = new JLabel("Enter Time 2 (HH:MM:SS):");
        JTextField timeField2 = new JTextField();
        JLabel resultLabel = new JLabel("Time Difference: ");

        JButton calculateButton = new JButton("Calculate Time Difference");
        calculateButton.addActionListener(e -> {
            try {
                String time1 = timeField1.getText();
                String time2 = timeField2.getText();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime localTime1 = LocalTime.parse(time1, timeFormatter);
                LocalTime localTime2 = LocalTime.parse(time2, timeFormatter);
                if (localTime1.isAfter(localTime2)) {
                    throw new DateTimeParseException("Invalid time", time1, 0);
                }
                long hours = ChronoUnit.HOURS.between(localTime1, localTime2);
                long minutes = ChronoUnit.MINUTES.between(localTime1, localTime2) % 60;
                long seconds = ChronoUnit.SECONDS.between(localTime1, localTime2) % 60;
                resultLabel.setText(
                        "Time Difference: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid time format. Please use HH:MM:SS.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(timeLabel1);
        panel.add(timeField1);
        panel.add(timeLabel2);
        panel.add(timeField2);
        panel.add(calculateButton);
        panel.add(resultLabel);

        return panel;
    }

    private JPanel createDateDiffPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel dateLabel1 = new JLabel("Enter Date 1 (YYYY-MM-DD):");
        JTextField dateField1 = new JTextField();
        JLabel dateLabel2 = new JLabel("Enter Date 2 (YYYY-MM-DD):");
        JTextField dateField2 = new JTextField();
        JLabel resultLabel = new JLabel("Date Difference: ");

        JButton calculateButton = new JButton("Calculate Date Difference");
        calculateButton.addActionListener(e -> {
            try {
                String date1 = dateField1.getText();
                String date2 = dateField2.getText();
                LocalDate startDate = LocalDate.parse(date1);
                LocalDate endDate = LocalDate.parse(date2);
                if (startDate.isAfter(endDate)) {
                    throw new DateTimeParseException("Invalid date", date1, 0);
                }
                Period period = Period.between(startDate, endDate);
                long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
                resultLabel.setText("Date Difference: " + period.getMonths() + " months, " + period.getDays()
                        + " days (Total days: " + totalDays + ")");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(dateLabel1);
        panel.add(dateField1);
        panel.add(dateLabel2);
        panel.add(dateField2);
        panel.add(calculateButton);
        panel.add(resultLabel);

        return panel;
    }

    private JPanel createTimeZonePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel timeZoneLabel = new JLabel("Enter Time Zone (ID):");
        JTextField timeZoneField = new JTextField();
        JLabel resultLabel = new JLabel("Current Time: ");

        JButton calculateButton = new JButton("Calculate Time Zone");
        calculateButton.addActionListener(e -> {
            try {
                String timeZone = timeZoneField.getText();
                ZonedDateTime timeInZone = ZonedDateTime.now(ZoneId.of(timeZone));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
                resultLabel.setText("Current Time: " + timeInZone.format(formatter));
            } catch (DateTimeException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid time zone ID. Please use a valid time zone ID.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(timeZoneLabel);
        panel.add(timeZoneField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        return panel;
    }

    private JPanel createReminderPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel reminderLabel = new JLabel("Enter Reminder Date (YYYY-MM-DD):");
        JTextField reminderField = new JTextField();
        JLabel resultLabel = new JLabel("Days until reminder: ");

        JButton calculateButton = new JButton("Calculate Days Until Reminder");
        calculateButton.addActionListener(e -> {
            try {
                String reminderDate = reminderField.getText();
                LocalDate reminderLocalDate = LocalDate.parse(reminderDate);
                long daysUntilReminder = ChronoUnit.DAYS.between(LocalDate.now(), reminderLocalDate);
                resultLabel.setText("Days until reminder: " + daysUntilReminder);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Please use YYYY-MM-DD.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(reminderLabel);
        panel.add(reminderField);
        panel.add(calculateButton);
        panel.add(resultLabel);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TimeCalculator::new);
    }
}
