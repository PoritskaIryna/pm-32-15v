import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class PersonGUI extends JFrame {
    private PersonsManager personManager = new PersonsManager();
    private JTextArea displayArea;
    private JComboBox<String> personTypeFilter;
    public PersonGUI() {
        setTitle("Persons Manager");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
        setVisible(true);
    }
    private void initUI() {
        JPanel panel = new JPanel();
        this.add(panel);
        JButton addButton = new JButton("Add person");
        JButton saveButton = new JButton("Save Persons");
        JButton loadButton = new JButton("Load Persons}");

        String[] personTypes = {"All", "Warrior", "Molfar", "Chorakternyk"};

        personTypeFilter = new JComboBox<>(personTypes);
        personTypeFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplayArea();
            }
        });
        panel.add(personTypeFilter);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPerson();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    personManager.savePersons("persons.dat");
                    JOptionPane.showMessageDialog(null, "Persons saved successfully");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving persons");
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    personManager.loadPersons("persons.dat");
                    JOptionPane.showMessageDialog(null, "Persons loaded successfully");
                    updateDisplayArea();
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error loading persons");
                }
            }
        });

        panel.add(addButton);
        panel.add(saveButton);
        panel.add(loadButton);
        displayArea = new JTextArea(10, 25);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        this.add(scrollPane, BorderLayout.SOUTH);
        try {
            personManager.loadPersons("persons.dat");
            JOptionPane.showMessageDialog(null, "Persons loaded successfully");
            updateDisplayArea();
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error loading persons");
        }
    }

    private void updateDisplayArea() {
        StringBuilder builder = new StringBuilder();
        String selectedType = (String) personTypeFilter.getSelectedItem();

        for (Person person : personManager.getPersons()) {
            boolean matchesType = false;
            String type = "Unknown";
            if (person instanceof Warrior) {
                type = "Warrior";
            } else if (person instanceof Molfar) {
                type = "Molfar";
            } else if (person instanceof Chorakternyk) {
                type = "Chorakternyk";
            }
            if ("All".equals(selectedType)) {
                matchesType = true;
            } else if ("Warrior".equals(selectedType) && person instanceof Warrior) {
                matchesType = true;
            } else if ("Molfar".equals(selectedType) && person instanceof Molfar) {
                matchesType = true;
            } else if ("Chorakternyk".equals(selectedType) && person instanceof Chorakternyk) {
                matchesType = true;
            }

            if (matchesType) {
                builder.append("ID: ").append(person.getId())
                        .append(", Name: ").append(person.getName())
                        .append(", Type: ").append(type)
                        .append(", Impact: ").append(person.calculateImpact())
                        .append("\n");
            }
        }
        displayArea.setText(builder.toString());
    }

    private void addPerson() {
        JDialog addDialog = new JDialog(this, "Add Person", true);
        addDialog.setSize(900, 300);
        addDialog.setLayout(new FlowLayout());

        JTextField nameField = new JTextField(20);
        JTextField idField = new JTextField(20);
        JTextField hpField = new JTextField(20);
        JTextField powerField = new JTextField(20);
        JTextField magicField = new JTextField(20);
        JTextField iqField = new JTextField(20);

        String[] personsTypes = {"Warrior", "Molfar", "Chorakternyk"};
        JComboBox<String> typeCombo = new JComboBox<>(personsTypes);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    int id = Integer.parseInt(idField.getText());
                    Person person = null;
                    double hp = Double.parseDouble(hpField.getText());
                    switch ((String)typeCombo.getSelectedItem()) {
                        case "Warrior":
                            double power = Double.parseDouble(powerField.getText());
                            person = new Warrior(name, id, hp,power);
                            break;
                        case "Molfar":
                            double magic = Double.parseDouble(magicField.getText());
                            person = new Molfar(name, id, hp, magic);
                            break;
                        case "Chorakternyk":
                            double iq = Double.parseDouble(iqField.getText());
                            person = new Chorakternyk(name, id, hp, iq);
                            break;
                    }
                    if (person != null) {
                        personManager.addPerson(person);
                        JOptionPane.showMessageDialog(addDialog, "Person added successfully");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addDialog, "Invalid input format");
                } finally {
                    addDialog.dispose();
                }
            }
        });

        addDialog.add(new JLabel("Name:"));
        addDialog.add(nameField);
        addDialog.add(new JLabel("ID:"));
        addDialog.add(idField);
        addDialog.add(new JLabel("Type:"));
        addDialog.add(typeCombo);
        addDialog.add(new JLabel("Hp:"));
        addDialog.add(hpField);
        addDialog.add(new JLabel("Power:"));
        addDialog.add(powerField);
        addDialog.add(new JLabel("Magic:"));
        addDialog.add(magicField);
        addDialog.add(new JLabel("Iq:"));
        addDialog.add(iqField);
        addDialog.add(submitButton);

        addDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PersonGUI();
            }
        });
    }


}
