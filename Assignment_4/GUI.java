package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class GUI {

    private JTextField JSCheckingTime;
    private JTextField JSnumberOfCustomerManagers;
    private JPanel Headline;
    private JPanel CheckTime;
    private JPanel NumOfCustManagers;
    private JButton startButton;
    private JButton exitButton;
    private JPanel Buttons;
    private JPanel MainFrame;
    private boolean restartFlag = false;

    LogicManager logicManager;
    public GUI() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    boolean continueFlag = true;
                    float checkingTime = Float.parseFloat(JSCheckingTime.getText());
                    int numberOfCustomerManagers = Integer.parseInt(JSnumberOfCustomerManagers.getText());
                    if (checkingTime < 0 || checkingTime > 5){
                        JOptionPane.showMessageDialog(null, "Please enter checking time between 0 and 5");
                        continueFlag = false;
                    }
                    if (numberOfCustomerManagers <= 0){
                        JOptionPane.showMessageDialog(null, "Please enter positive number of customer managers");
                        continueFlag = false;
                    }
                    else if (continueFlag){
                        Runnable showMessageDialog = new Runnable() {
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(null, "Simulation started");
                            }
                        };
                        SwingUtilities.invokeLater(showMessageDialog);

                        logicManager = new LogicManager(checkingTime, numberOfCustomerManagers);
                        logicManager.readFiles("src\\data\\Customerscpy.txt", "src\\data\\Stock.txt");
                        logicManager.startDay();
                        restartFlag = true;
                    }
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers");
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scooter Company 4");
        frame.setContentPane(new GUI().MainFrame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
