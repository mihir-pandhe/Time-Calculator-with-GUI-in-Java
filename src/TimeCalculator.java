package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeCalculator {

    private JFrame frame;

    private JTextField dateField1;
    private JTextField dateField2;
    private JTextField timeField1;
    private JTextField timeField2;

    private JLabel dateLabel1;
    private JLabel dateLabel2;
    private JLabel timeLabel1;
    private JLabel timeLabel2;

    public TimeCalculator() {
        frame = new JFrame("Time Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2));

        dateLabel1 = new JLabel("Enter Date 1 (YYYY-MM-DD):");
        dateField1 = new JTextField();
        dateLabel2 = new JLabel("Enter Date 2 (YYYY-MM-DD):");
        dateField2 = new JTextField();
        timeLabel1 = new JLabel("Enter Time 1 (HH:MM:SS):");
        timeField1 = new JTextField();
        timeLabel2 = new JLabel("Enter Time 2 (HH:MM:SS):");
        timeField2 = new JTextField();

        frame.add(dateLabel1);
        frame.add(dateField1);
        frame.add(dateLabel2);
        frame.add(dateField2);
        frame.add(timeLabel1);
        frame.add(timeField1);
        frame.add(timeLabel2);
        frame.add(timeField2);

        JButton calculateButton = new JButton("Calculate");
        frame.add(calculateButton);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Calculation function will be implemented later.");
            }
        });

        frame.setVisible(true);
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
