import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class QN2_Answers extends JFrame {

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField confirmEmailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> yearBox;
    private JComboBox<String> monthBox;
    private JComboBox<String> dayBox;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JCheckBox civilCheck;
    private JCheckBox cseCheck;
    private JCheckBox elecCheck;
    private JCheckBox ecCheck;
    private JCheckBox mechCheck;
    private JTextArea displayArea;

    public QN2_Answers() {
        setTitle("New Student Registration Form");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Student First Name:"), gbc);
        gbc.gridx = 1;
        firstNameField = new JTextField(15);
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Student Last Name:"), gbc);
        gbc.gridx = 1;
        lastNameField = new JTextField(15);
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Email Address:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(15);
        formPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Confirm Email Address:"), gbc);
        gbc.gridx = 1;
        confirmEmailField = new JTextField(15);
        formPanel.add(confirmEmailField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(15);
        formPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Date of Birth:"), gbc);
        gbc.gridx = 1;
        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        String[] years = new String[61];
        years[0] = "Select Year";
        for (int i = 1; i <= 60; i++) years[i] = String.valueOf(2026 - 15 - i);
        yearBox = new JComboBox<>(years);
        
        String[] months = {"Select Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        monthBox = new JComboBox<>(months);
        
        dayBox = new JComboBox<>(new String[]{"Select Day"});
        
        dobPanel.add(yearBox);
        dobPanel.add(monthBox);
        dobPanel.add(dayBox);
        formPanel.add(dobPanel, gbc);

        monthBox.addActionListener(e -> updateDays());
        yearBox.addActionListener(e -> updateDays());

        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        formPanel.add(genderPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1;
        JPanel deptPanel = new JPanel();
        deptPanel.setLayout(new BoxLayout(deptPanel, BoxLayout.Y_AXIS));
        civilCheck = new JCheckBox("Civil");
        cseCheck = new JCheckBox("Computer Science and Engineering");
        elecCheck = new JCheckBox("Electrical");
        ecCheck = new JCheckBox("Electronics and Communication");
        mechCheck = new JCheckBox("Mechanical");
        
        deptPanel.add(civilCheck);
        deptPanel.add(cseCheck);
        deptPanel.add(elecCheck);
        deptPanel.add(ecCheck);
        deptPanel.add(mechCheck);
        formPanel.add(deptPanel, gbc);

        gbc.gridx = 1; gbc.gridy = 9;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        formPanel.add(buttonPanel, gbc);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightPanel.add(new JLabel("Your Data is Below:"), BorderLayout.NORTH);
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        rightPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        add(formPanel);
        add(rightPanel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        cancelButton.addActionListener(e -> {
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            confirmEmailField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            yearBox.setSelectedIndex(0);
            monthBox.setSelectedIndex(0);
            dayBox.removeAllItems();
            dayBox.addItem("Select Day");
            genderGroup.clearSelection();
            civilCheck.setSelected(false);
            cseCheck.setSelected(false);
            elecCheck.setSelected(false);
            ecCheck.setSelected(false);
            mechCheck.setSelected(false);
            displayArea.setText("");
        });
    }

    private void updateDays() {
        String selectedYear = (String) yearBox.getSelectedItem();
        String selectedMonth = (String) monthBox.getSelectedItem();

        if (selectedYear == null || selectedYear.equals("Select Year") ||
            selectedMonth == null || selectedMonth.equals("Select Month")) {
            return;
        }

        int year = Integer.parseInt(selectedYear);
        int month = Integer.parseInt(selectedMonth);
        int days = 0;

        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: days = 31; break;
            case 4: case 6: case 9: case 11: days = 30; break;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) days = 29;
                else days = 28;
                break;
        }

        dayBox.removeAllItems();
        for (int i = 1; i <= days; i++) {
            dayBox.addItem(String.valueOf(i));
        }
    }

    private void handleSubmit() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String confirmEmail = confirmEmailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || 
            confirmEmail.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            JOptionPane.showMessageDialog(this, "Invalid email format.");
            return;
        }

        if (!email.equals(confirmEmail)) {
            JOptionPane.showMessageDialog(this, "Emails do not match.");
            return;
        }

        if (password.length() < 8 || password.length() > 20 || 
            !password.matches(".*[a-zA-Z]+.*") || !password.matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(this, "Password must be 8-20 chars, with 1 letter and 1 digit.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
            return;
        }

        if (yearBox.getSelectedIndex() == 0 || monthBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Select a valid Date of Birth.");
            return;
        }

        int startY = Integer.parseInt((String)yearBox.getSelectedItem());
        int startM = Integer.parseInt((String)monthBox.getSelectedItem());
        int startD = Integer.parseInt((String)dayBox.getSelectedItem());
        LocalDate dob = LocalDate.of(startY, startM, startD);
        int age = Period.between(dob, LocalDate.now()).getYears();

        if (age < 16 || age > 60) {
            JOptionPane.showMessageDialog(this, "Age must be between 16 and 60.");
            return;
        }

        if (!maleRadio.isSelected() && !femaleRadio.isSelected()) {
            JOptionPane.showMessageDialog(this, "Select a gender.");
            return;
        }

        int deptCount = 0;
        String dept = "";
        if (civilCheck.isSelected()) { deptCount++; dept = "Civil"; }
        if (cseCheck.isSelected()) { deptCount++; dept = "CSE"; }
        if (elecCheck.isSelected()) { deptCount++; dept = "Electrical"; }
        if (ecCheck.isSelected()) { deptCount++; dept = "E&C"; }
        if (mechCheck.isSelected()) { deptCount++; dept = "Mechanical"; }

        if (deptCount != 1) {
            JOptionPane.showMessageDialog(this, "Select exactly one department.");
            return;
        }

        String gender = maleRadio.isSelected() ? "M" : "F";
        String id = "2026-" + String.format("%05d", (int)(Math.random() * 10000));
        String formattedRecord = "ID: " + id + " | " + firstName + " " + lastName + " | " + gender + " | " + dept + " | " + dob + " | " + email;

        displayArea.setText(formattedRecord);

        try (FileWriter fw = new FileWriter("students.csv", true)) {
            fw.write(formattedRecord + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving to file.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QN2_Answers().setVisible(true));
    }
}
