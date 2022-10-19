/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.aquapackrobotics.sw8s;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.aquapackrobotics.sw8s.missions.Mission;
import org.aquapackrobotics.sw8s.missions.AutoMission;
import org.aquapackrobotics.sw8s.missions.ManualMission;
public class App {

    static final int POOLSIZE = 1;
    
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(POOLSIZE);
        String currentMission = "N/A";
        String currentState = "N/A";
        String helpFlag[] = {
            "\nBasic Utility:", 
            "\n'--help' or '-h' -- displays list of command flags",
            "\n'--currentMission' -- prints the current mission",
            "\nTesting:",
            "\n'--test' -- The Command Flag used in Testing", 
            "\n'--testmission' -- sets mission to be the string 'test' for testing purposes",
            "\nMissions:", 
            "\n'--raw_test' runs the Raw Test mission",
            "\n '--local_test' runs the Local Test mission"};
        System.out.println("Basic Format: gradle run --args='_'");
        for (String str: args) {
            switch (str) {
                case "--test":
                    currentMission = "None, running test";
                    System.out.println("Yay! it worked!");
                    break;
                case "--testmission":
                    currentMission = "Test Mission";
                    break;
                case "--currentMission":
                    System.out.println(currentMission);
                    break;
                case "-h":
                case "--help":
                    for(int i = 0; i < helpFlag.length; i++){
                        System.out.println(helpFlag[i]);
                    }
                    break;
                // case "--raw_test":
                //     currentMission = "Raw Test";
                //     Mission missionRaw_Test = (Mission) new Raw_TestMission(pool);
                //     missionRaw_Test.run();
                //     break;
                // case "--local_test":
                //     currentMission = "Local Test";
                //     Mission missionLocal_Test = (Mission) new Local_TestMission(pool);
                //     missionLocal_Test.run();
                //     break;
                // case "-s1":
                //     executeState(State1);
                //     break;
                // case "-s2":
                //     executeState(State2);
                //     break;
                // case "-s3":
                //     executeState(State3);
                //     break;
                case "manual":
                    currentMission = "Manual";
                    Mission missionManual = (Mission) new ManualMission(pool);
                    missionManual.run();
                    break;
                default:
                    currentMission = "Auto";
                    Mission missionAuto = (Mission) new AutoMission(pool);
                    missionAuto.run();
                    break;

            }
        }

        
        // Variables
        Color red = new Color(255, 0, 0);
        Color darkGrey = new Color(80, 80, 80);
        Color white = new Color(255, 255, 255);
       
        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
       
        //Low Panel and Content
        JPanel lowPanel = new JPanel(); // the panel is not visible in output
        lowPanel.setBackground(darkGrey);
        JButton eStop = new JButton("STOP");
        eStop.setPreferredSize(new Dimension(300, 100));
        eStop.setBackground(red);
        eStop.setForeground(white);
        eStop.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        eStop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TO IMPLEMENT: ALL ZEROES, MAKE ROBOT NOT GO **BEFORE** SYSTEM.EXIT
                System.exit(0);
            }
        });
        lowPanel.add(eStop);

        //High Panel and Content
        JPanel highPanel = new JPanel();
        highPanel.setBackground(darkGrey);
        JLabel label = new JLabel("Enter Text");
        label.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        label.setForeground(white);
        JTextField tf = new JTextField(50); // accepts upto 100 characters
        JButton sendButton = new JButton("Send");
        highPanel.add(label);
        highPanel.add(tf);
        highPanel.add(sendButton);
        

        // Text Area at the Center
        JTextArea ta = new JTextArea();
        ta.setText("Mission: " + currentMission + 
                 "\nState:   " + currentState +
                   "");
        Font  f  = new Font(Font.DIALOG,  Font.BOLD, 50);
        ta.setFont(f);
        
        //Adding Panel/Text Area to the frame.
        frame.getContentPane().add(BorderLayout.NORTH, highPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, lowPanel);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
        
    }
}
