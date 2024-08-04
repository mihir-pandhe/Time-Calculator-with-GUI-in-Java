package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class TimeCalculator {

    private JFrame frame;
    private JTextField birthdateField;
    private JLabel ageLabel;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TimeCalculator() {
        frame = new JFrame("Time Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 2));

        JLabel birthdateLabel = new JLabel("Enter Birthdate (YYYY-MM-DD):");
        birthdateField = new JTextField();
        ageLabel = new JLabel("Age: ");

        JButton calculateButton = new JButton("Calculate Age");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAge();
            }
        });

        frame.add(birthdateLabel);
        frame.add(birthdateField);
        frame.add(calculateButton);
        frame.add(ageLabel);

        frame.setVisible(true);
    }

    private void calculateAge() {
        String birthdateText = birthdateField.getText();
        try {
            LocalDate birthdate = LocalDate.parse(birthdateText, dateFormatter);
            LocalDate today = LocalDate.now();
            Period period = Period.between(birthdate, today);

            ageLabel.setText(String.format("Age: %d years, %d months, %d days",
                    period.getYears(), period.getMonths(), period.getDays()));
        } catch (DateTimeParseException e) {
            ageLabel.setText("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TimeCalculator());
    }
}
