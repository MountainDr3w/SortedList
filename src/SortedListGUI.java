import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortedListGUI extends JFrame {
    private SortedList sortedList = new SortedList();

    /*
    The top panel will have the title
    */
    //Top Panel
    JPanel topPanel;
    JLabel title;

    /*
    The middle panel will have the input field and display area
    */
    //Middle Panel
    JPanel midPanel;
    JTextField inputField;
    JTextArea displayArea;

    /*
    The bottom panel will include all buttons
    Add element
    Search element
    Quit
    */
    //Bottom Panel
    JPanel botPanel;
    JButton addButton, searchButton, quitButton;

    public SortedListGUI() {
        setTitle("Sorted List Demo");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // center frame in screen
        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));

        createTopPanel();
        createMiddlePanel();
        createBottomPanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(midPanel, BorderLayout.CENTER);
        mainPanel.add(botPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void createTopPanel() {
        topPanel = new JPanel();

        title = new JLabel("Sorted List Manager");
        title.setFont(new Font("Times New Roman", Font.BOLD, 48));

        topPanel.add(title);
    }

    private void createMiddlePanel() {
        midPanel = new JPanel();
        midPanel.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        JLabel inputTitle = new JLabel("Enter Text");
        inputField = new JTextField(20);
        inputPanel.add(inputTitle, BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);

        displayArea = new JTextArea(15, 40);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        midPanel.add(inputPanel, BorderLayout.NORTH);
        midPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createBottomPanel() {
        botPanel = new JPanel();
        botPanel.setLayout(new BorderLayout(10, 10));

        addButton = new JButton("Add Element");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();
                if (!input.isEmpty()) {
                    sortedList.add(input);
                    updateDisplay("Added: " + input);
                    inputField.setText("");
                }
            }
        });

        searchButton = new JButton("Search Element");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = inputField.getText().trim();
                if (!searchTerm.isEmpty()) {
                    int position = sortedList.findIndex(searchTerm);
                    if (position < sortedList.size &&
                            sortedList.getWord(position).equals(searchTerm)) {
                        updateDisplay("Found \"" + searchTerm + "\" at position: " + (position + 1));
                    } else {
                        updateDisplay("\"" + searchTerm + "\" would be inserted at position: " + (position + 1));
                    }
                    inputField.setText("");
                }
            }
        });

        quitButton = new JButton("Quit");
        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        botPanel.add(addButton, BorderLayout.WEST);
        botPanel.add(searchButton, BorderLayout.CENTER);
        botPanel.add(quitButton, BorderLayout.EAST);
    }

    private void updateDisplay(String operation) {
        displayArea.setText("Current list: " + "\n" +sortedList.toString() +
                "\n\n" + operation + "\n" +
                "List size: " + sortedList.size);
    }
}