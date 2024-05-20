import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class ClickCounter {

    // COUNTER VARIABLES
    private static int COUNTER = 0;
    private static int CLICK_COUNT = 0;
    private static int MAX_CPS = 0;
    private static JTextField CPS_BOX;
    private static JTextField MAX_CPS_BOX;

    public static void main(String[] args) {

        // APP ICON
        ImageIcon IMAGE = new ImageIcon("assets/click_counter_app_logo.png");

        // TEXT FIELD TO SHOW THE COUNT TO THE USER
        JTextField COUNTER_BOX = new JTextField("0");
        Font COUNTER_BOX_FONT = new Font("Arial", Font.BOLD, 100);
        Border COUNTER_BOX_BORDER = BorderFactory.createLineBorder(Color.BLUE, 3);
        COUNTER_BOX.setFont(COUNTER_BOX_FONT);
        COUNTER_BOX.setEditable(false);
        COUNTER_BOX.setFocusable(false);
        COUNTER_BOX.setHorizontalAlignment(JTextField.CENTER);
        COUNTER_BOX.setBorder(COUNTER_BOX_BORDER);

        // TEXT FIELD TO SHOW THE CPS TO THE USER
        CPS_BOX = new JTextField("CPS: 0");
        Font CPS_BOX_FONT = new Font("Arial", Font.BOLD, 20);
        Border CPS_BOX_BORDER = BorderFactory.createLineBorder(Color.GREEN, 2);
        CPS_BOX.setFont(CPS_BOX_FONT);
        CPS_BOX.setEditable(false);
        CPS_BOX.setFocusable(false);
        CPS_BOX.setHorizontalAlignment(JTextField.CENTER);
        CPS_BOX.setBorder(CPS_BOX_BORDER);
        CPS_BOX.setBounds(65, 280, 240, 50);

        // TEXT FIELD TO SHOW THE MAX CPS REACHED BY THE USER
        MAX_CPS_BOX = new JTextField("Max CPS: 0");
        Font MAX_CPS_BOX_FONT = new Font("Arial", Font.BOLD, 20);
        Border MAX_CPS_BOX_BORDER = BorderFactory.createLineBorder(Color.ORANGE, 2);
        MAX_CPS_BOX.setFont(MAX_CPS_BOX_FONT);
        MAX_CPS_BOX.setEditable(false);
        MAX_CPS_BOX.setFocusable(false);
        MAX_CPS_BOX.setHorizontalAlignment(JTextField.CENTER);
        MAX_CPS_BOX.setBorder(MAX_CPS_BOX_BORDER);
        MAX_CPS_BOX.setBounds(65, 340, 240, 50);

        // BUTTON TO INCREASE COUNT
        JButton BUTTON = new JButton("Click Here");
        Font BUTTON_FONT = new Font("Arial", Font.PLAIN, 18);
        Border BUTTON_BORDER = BorderFactory.createLineBorder(Color.BLACK, 2);
        BUTTON.setFont(BUTTON_FONT);
        BUTTON.setBorder(BUTTON_BORDER);
        BUTTON.setFocusPainted(false);
        BUTTON.setBackground(Color.YELLOW);
        BUTTON.setForeground(Color.BLACK);
        BUTTON.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                COUNTER++;
                CLICK_COUNT++;
                COUNTER_BOX.setText(String.valueOf(COUNTER));
            }
        });

        // BUTTON TO RESET THE COUNTER
        ImageIcon RESET_ICON = new ImageIcon("assets/reset_icon.png");
        Image resetImage = RESET_ICON.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        RESET_ICON = new ImageIcon(resetImage);
        JButton BUTTON_RESET = new JButton(RESET_ICON);
        Border BUTTON_RESET_BORDER = BorderFactory.createLineBorder(Color.RED, 2);
        BUTTON_RESET.setBackground(Color.WHITE);
        BUTTON_RESET.setBorder(BUTTON_RESET_BORDER);
        BUTTON_RESET.setFocusPainted(false);
        BUTTON_RESET.setBounds(260, 430, 50, 50);

        BUTTON_RESET.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                COUNTER = 0;
                CLICK_COUNT = 0;
                COUNTER_BOX.setText(String.valueOf(COUNTER));
                CPS_BOX.setText("CPS: 0");
            }
        });

        // TIMER TO UPDATE CPS BOX EVERY SECOND
        Timer cpsTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CPS_BOX.setText("CPS: " + CLICK_COUNT);
                if (CLICK_COUNT > MAX_CPS) {
                    MAX_CPS = CLICK_COUNT;
                    MAX_CPS_BOX.setText("Max CPS: " + MAX_CPS);
                }
                CLICK_COUNT = 0; // Reset click count after each second
            }
        });
        cpsTimer.start();

        // PANEL SETUP
        JPanel PANEL = new JPanel(null); // Use null layout for absolute positioning
        PANEL.add(COUNTER_BOX);
        PANEL.add(CPS_BOX);
        PANEL.add(MAX_CPS_BOX);
        PANEL.add(BUTTON);
        PANEL.add(BUTTON_RESET);

        // Set bounds for components
        COUNTER_BOX.setBounds(65, 150, 240, 120);
        BUTTON.setBounds(65, 400, 170, 60);
        BUTTON_RESET.setBounds(245, 400, 60, 60);

        // APPLICATION FRAME SETUP
        JFrame FRAME = new JFrame("Click Counter");
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setSize(390, 630);
        FRAME.setLayout(new BorderLayout());
        FRAME.setResizable(false);
        FRAME.setIconImage(IMAGE.getImage());
        FRAME.add(PANEL, BorderLayout.CENTER);
        FRAME.setVisible(true);

        // Ensure the UI is refreshed properly
        FRAME.invalidate();
        FRAME.validate();
        FRAME.repaint();
    }
}
