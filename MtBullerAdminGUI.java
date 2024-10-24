import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MtBullerAdminGUI extends JFrame {

    private MtBullerResort resort;
    private JLabel roomImageLabel;
    private JTextArea packageDetailsArea; // Declare JTextArea for package details

    public MtBullerAdminGUI() {
        resort = new MtBullerResort(); // Initialize the resort logic

        // Adding initial customers (example)
        resort.addCustomer(new Customer("C1", "Alice", "0435551234", "beginner", "101"));
        resort.addCustomer(new Customer("C2", "Bob", "0425555678", "intermediate", "202"));
        resort.addCustomer(new Customer("C3", "Charlie", "0465559876", "expert", "303"));

        // Set up the window
        setTitle("Mt. Buller Admin");
        setSize(1200, 920);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create a tabbed pane with two tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create the two tab panels
        JPanel accommodationsTab = createAccommodationsTab();
        JPanel customersTab = createCustomersTab();
        JPanel travelPackagesTab = createTravelPackagesTab();

        // Add tabs to the tabbed pane
        tabbedPane.addTab("Accommodations", accommodationsTab);
        tabbedPane.addTab("Customers", customersTab);
        tabbedPane.addTab("Travel Packages", travelPackagesTab);

        // Add the tabbed pane to the main frame
        add(tabbedPane, BorderLayout.CENTER);

        // Set an initial image on the roomImageLabel as soon as the program starts
        roomImageLabel.setIcon(new ImageIcon("Source\\Welcome.jpg")); // Set the initial image here
    }

    // Create the "Accommodations" tab panel
    private JPanel createAccommodationsTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panel to hold buttons and labels (on the left side)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));  // 2 rows for the buttons

        JButton showRoomsButton = createButton("Show All Rooms");
        JButton availableRoomsButton = createButton("Show Available Rooms");

        buttonPanel.add(showRoomsButton);
        buttonPanel.add(availableRoomsButton);

        // Text area to display room details (on the right side)
        JTextArea roomDetailsArea = new JTextArea();
        roomDetailsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        roomDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(roomDetailsArea);

        // Label to display an image
        roomImageLabel = new JLabel();  // Initialize here
        roomImageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Split panel to display both image and details
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, roomImageLabel, scrollPane);
        splitPane.setDividerLocation(300);  // Set the initial location for the divider

        // Button listeners
        showRoomsButton.addActionListener(e -> {
            roomDetailsArea.setText(resort.showRooms());
            roomImageLabel.setIcon(new ImageIcon("Source\\Buller2.jpg"));  // Show all rooms image
        });

        availableRoomsButton.addActionListener(e -> {
            roomDetailsArea.setText(resort.displayRooms());
            roomImageLabel.setIcon(new ImageIcon("Source\\Buller.jpg"));  // Show available rooms image
        });

        panel.add(buttonPanel, BorderLayout.WEST);  // Add buttons on the left side
        panel.add(splitPane, BorderLayout.CENTER);  // Add split panel with image and text area on the right side

        return panel;
    }

    // Create the "Customers" tab panel
    private JPanel createCustomersTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panel to hold buttons and labels (on the left side)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton addCustomerButton = createButton("Add Customer");
        JButton listCustomersButton = createButton("List Customers");

        buttonPanel.add(addCustomerButton);
        buttonPanel.add(listCustomersButton);

        // Text area to display customer details (on the right side)
        JTextArea customerDetailsArea = new JTextArea();
        customerDetailsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        customerDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(customerDetailsArea);

        // Button listeners
        addCustomerButton.addActionListener(e -> {
            resort.addCustomerProcess(); // Call method to add customer
            customerDetailsArea.setText("Customer added successfully!");
        });

        listCustomersButton.addActionListener(e -> customerDetailsArea.setText(resort.listCustomers()));

        panel.add(buttonPanel, BorderLayout.WEST);  // Add buttons on the left side
        panel.add(scrollPane, BorderLayout.CENTER);  // Add text area on the right side

        return panel;
    }

    // Create the "Travel Packages" tab panel
    private JPanel createTravelPackagesTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Panel to hold buttons and labels (on the left side)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));  // Adjust for 5 rows now

        JButton createPackageButton = createButton("Create Package");
        JButton listPackagesButton = createButton("List Packages");
        JButton savePackageDetailButton = createButton("Save Package Details");
        JButton readPackageDetailButton = createButton("Read Package Details");
        JButton removePackageButton = createButton("Remove Package");  // New button for removing packages

        buttonPanel.add(createPackageButton);
        buttonPanel.add(listPackagesButton);
        buttonPanel.add(savePackageDetailButton);
        buttonPanel.add(readPackageDetailButton);
        buttonPanel.add(removePackageButton);  // Add the new button here

        // Text area to display package details (on the right side)
        packageDetailsArea = new JTextArea();
        packageDetailsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        packageDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(packageDetailsArea);

        // Button listeners
        createPackageButton.addActionListener(e -> resort.createPackageProcess());  // Connect to createPackageProcess in MtBullerResort

        listPackagesButton.addActionListener(e -> {
            String packagesList = resort.listPackages();
            packageDetailsArea.setText(packagesList);  // Set the list of packages in the text area
        });

        savePackageDetailButton.addActionListener(e -> {
            String filename = "packages.dat"; // File to save the packages
            resort.savePackagesToFile(filename); // Call the method to save
            packageDetailsArea.setText("Package details saved successfully to " + filename);
        });

        readPackageDetailButton.addActionListener(e -> {
            String filename = "packages.dat"; // File to read the packages
            resort.readPackagesFromFile(filename); // Call the method to read
            packageDetailsArea.setText(resort.listPackages()); // Display the read packages
        });

        // Listener for removing packages with animation
        removePackageButton.addActionListener(e -> {
            String customerId = JOptionPane.showInputDialog(null, "Enter Customer ID to remove package:");
            if (customerId != null && !customerId.trim().isEmpty()) {
                startFadeOutAnimation(customerId);  // Start the fade-out animation
            } else {
                packageDetailsArea.setText("Customer ID cannot be empty.");
            }
        });

        panel.add(buttonPanel, BorderLayout.WEST);  // Add buttons on the left side
        panel.add(scrollPane, BorderLayout.CENTER);  // Add text area on the right side

        return panel;
    }

    // Animation method for fade-out effect
    private void startFadeOutAnimation(String customerId) {
        Timer timer = new Timer(100, new ActionListener() {
            float opacity = 1.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= 0.1f;
                if (opacity <= 0) {
                    ((Timer) e.getSource()).stop();  // Stop the timer
                    packageDetailsArea.setText(resort.removePackage(customerId));  // Remove the package after fade-out
                    packageDetailsArea.setForeground(Color.BLACK);  // Reset color after fade-out
                } else {
                    // Change text color to mimic fade-out effect
                    Color fadedColor = new Color(0, 0, 0, opacity);
                    packageDetailsArea.setForeground(fadedColor);
                }
            }
        });
        timer.start();  // Start the animation
    }

    // Create a custom JButton with consistent styling
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MtBullerAdminGUI gui = new MtBullerAdminGUI();
            gui.setVisible(true);
        });
    }
}
