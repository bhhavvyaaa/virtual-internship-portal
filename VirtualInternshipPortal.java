import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// User class
class User {
    String name;
    String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

// Internship class
class Internship {
    String title;
    String company;
    String description;

    Internship(String title, String company, String description) {
        this.title = title;
        this.company = company;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Company: " + company + ", Description: " + description;
    }
}

// Portal class
public class VirtualInternshipPortal extends JFrame {
    private List<Internship> internships = new ArrayList<>();
    private JTextArea internshipDisplayArea;
    private JTextField titleField, companyField, descriptionField;

    public VirtualInternshipPortal() {
        setTitle("Virtual Internship Portal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel for adding internships
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Company:"));
        companyField = new JTextField();
        inputPanel.add(companyField);

        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        JButton addButton = new JButton("Add Internship");
        addButton.addActionListener(new AddInternshipListener());
        inputPanel.add(addButton);

        // Text area to display internships
        internshipDisplayArea = new JTextArea();
        internshipDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(internshipDisplayArea);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private class AddInternshipListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String company = companyField.getText();
            String description = descriptionField.getText();

            if (!title.isEmpty() && !company.isEmpty() && !description.isEmpty()) {
                Internship internship = new Internship(title, company, description);
                internships.add(internship);
                updateInternshipDisplay();
                clearInputFields();
            } else {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
            }
        }
    }

    private void updateInternshipDisplay() {
        internshipDisplayArea.setText("");
        for (Internship internship : internships) {
            internshipDisplayArea.append(internship.toString() + "\n");
        }
    }

    private void clearInputFields() {
        titleField.setText("");
        companyField.setText("");
        descriptionField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VirtualInternshipPortal portal = new VirtualInternshipPortal();
            portal.setVisible(true);
        });
    }
}
