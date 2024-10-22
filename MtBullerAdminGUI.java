import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MtBullerAdminGUI extends JFrame {

    private JTextArea infoTextArea;
    private MtBullerResort resort;

    public MtBullerAdminGUI() {
        resort = new MtBullerResort(); // Initialize the resort logic

        resort.addCustomer(new Customer("C1", "Alice", "0435551234", "beginner", "101"));
        resort.addCustomer(new Customer("C2", "Bob", "0425555678", "intermediate", "202"));
        resort.addCustomer(new Customer("C3", "Charlie", "0465559876", "expert", "303"));

        // Set up the window
        setTitle("Mt. Buller Admin");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create buttons panel for the left side
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10));

        JButton showRoomsButton = createButton("Show All Rooms");
        JButton availableRoomsButton = createButton("Show Available Rooms");
        JButton addCustomerButton = createButton("Add Customer");
        JButton listCustomersButton = createButton("List Customers");
        JButton createPackageButton = createButton("Create Package");
        JButton loadPackagesButton = createButton("Load Packages");

        // Add buttons to the panel
        buttonPanel.add(showRoomsButton);
        buttonPanel.add(availableRoomsButton);
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(listCustomersButton);
        buttonPanel.add(createPackageButton);
        buttonPanel.add(loadPackagesButton);

        // Add buttonPanel to the left side of the window
        add(buttonPanel, BorderLayout.WEST);

        // Create the text area for the center to display information
        infoTextArea = new JTextArea();
        infoTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        infoTextArea.setEditable(false);

        // Add a scroll pane in case the text is too long
        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button Listeners to display information from resort methods
        showRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.setText(resort.showRooms()); // Show all rooms
            }
        });

        availableRoomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.setText(resort.displayRooms()); // Show available rooms
            }
        });

        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resort.addCustomerProcess(); // Call method from MtBullerResort to add a customer
                infoTextArea.setText("Customer added!"); // Optional: display confirmation in the text area
            }
        });

        listCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.setText(resort.listCustomers()); // Show customer list
            }
        });

        createPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.setText("Creating a package..."); // Example action, customize as needed
            }
        });

        loadPackagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoTextArea.setText("Loading packages..."); // Example action, customize as needed
            }
        });
    }

    // Create a custom JButton with consistent styling
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MtBullerAdminGUI gui = new MtBullerAdminGUI();
                gui.setVisible(true);
            }
        });
    }
}
