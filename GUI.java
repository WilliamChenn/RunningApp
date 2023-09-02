package IA;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
public class GUI extends JFrame {
    private JPanel mainPanel;
    private JTextField timeTextField;
    private JLabel timeLabel;
    private JButton addRunButton;
    private JTextField distanceTextField;
    private JComboBox unitsComboBox;
    private JPanel EnterDistancePanel;
    private JPanel selectionPanel;
    private JComboBox selectionBox;
    JFrame tableFrame = new history("Run Log"); // create history log
    private static boolean status = true; //true = km false = miles
    private static String selection = "Mile";//defult
    public static void saveToFile(String fileName, String text) throws IOException {//save text to a file
        File file1;
        if (fileName.equals("KM")) {
            file1 = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/kmData");
        } else if (fileName.equals("1 Mile")) {
            file1 = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/1Mile");
        } else if (fileName.equals("5K")) {
            file1 = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/5K");
        } else if (fileName.equals("10K")) {
            file1 = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/10K");
        } else if (fileName.equals("Half Marathon")) {
            file1 = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/HalfMarathon");
        } else if (Objects.equals(fileName, "Marathon")) {
            file1 = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/Marathon");
        } else {
            file1 = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/milesData");
        }
        FileWriter fw = new FileWriter(file1, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(text);
        pw.close();
    }
    public GUI(String title) throws IOException { //constructor
        /////setup
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        EnterDistancePanel.setVisible(false);
        /////
        selectionBox.addActionListener(new ActionListener() { //distance dropbox
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox temp = (JComboBox) e.getSource();
                String msg = (String) temp.getSelectedItem();
                if (msg.equals("1 Mile")) { //sets selection to user's request
                    EnterDistancePanel.setVisible(false);
                    selection = "Mile";
                } else if (msg.equals("5K")) {
                    EnterDistancePanel.setVisible(false);
                    selection = "Five";
                } else if (msg.equals("10K")) {
                    EnterDistancePanel.setVisible(false);
                    selection = "Ten";
                } else if (msg.equals("Half Marathon")) {
                    EnterDistancePanel.setVisible(false);
                    selection = "Half";
                } else if (msg.equals("Marathon")) {
                    EnterDistancePanel.setVisible(false);
                    selection = "full";
                } else if (msg.equals("Custom")) {
                    EnterDistancePanel.setVisible(true);
                    selection = "custom";
                }
            }
        });
        unitsComboBox.addActionListener(new ActionListener() {          //changes custom distance input according to user's request
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox temp = (JComboBox) e.getSource();
                String msg = (String) temp.getSelectedItem();
                if (msg.equals("KM")) {
                    status = true;
                } else {
                    status = false;
                }
            }
        });
        if(Data.listCount() < 1 || (Data.streakCounter() == false && Data.sameDay() == false)){ //if the one before is not same day and if it is not the day before
            Data.incrementStreak("zero");
        }
        addRunButton.addActionListener(new ActionListener() {        //when clicked, run is added to correct files
            @Override
            public void actionPerformed(ActionEvent e) {

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");// find time
                LocalDateTime now = LocalDateTime.now();
                String timeNow = dtf.format(now);
                String time = timeTextField.getText();
                double distance = 0;
                String pace = "";
                try {
                    if(Data.listCount() < 1){
                        Data.incrementStreak("plus");
                    }
                    else if(Data.streakCounter() == true){ //if day is 01 then check if last day
                       Data.incrementStreak("plus");
                   }
                   else if(Data.sameDay() == true){

                   }
                   else{
                       Data.incrementStreak("one");
                   }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


                if (selection.equals("Mile")) {
                    distance = 1;
                    pace = Data.averagePace(distance, time);
                    try {
                        saveToFile("1 Mile", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        saveToFile("Miles", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    double distance2 = Data.milesToKm(distance);
                    String pace2 = Data.averagePace(distance2, time);
                    try {
                        saveToFile("KM", dtf.format(now) + "|" + time + "|" + distance2 + "|" + pace2);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                } else if (selection.equals("Five")) {
                    distance = 5;
                    pace = Data.averagePace(distance, time);

                    try {
                        saveToFile("5K", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        saveToFile("KM", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    double distance2 = Data.kmToMiles(distance);
                    String pace2 = Data.averagePace(distance2, time);

                    try {
                        saveToFile("Miles", dtf.format(now) + "|" + time + "|" + distance2 + "|" + pace2);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                } else if (selection.equals("Ten")) {
                    distance = 10;
                    pace = Data.averagePace(distance, time);

                    try {
                        saveToFile("10K", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        saveToFile("KM", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    double distance2 = Data.kmToMiles(distance);
                    String pace2 = Data.averagePace(distance2, time);

                    try {
                        saveToFile("Miles", dtf.format(now) + "|" + time + "|" + distance2 + "|" + pace2);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (selection.equals("Half")) {
                    distance = 21.1;
                    pace = Data.averagePace(distance, time);

                    try {
                        saveToFile("Half Marathon", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        saveToFile("KM", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    double distance2 = Data.kmToMiles(distance);
                    String pace2 = Data.averagePace(distance2, time);

                    try {
                        saveToFile("Miles", dtf.format(now) + "|" + time + "|" + distance2 + "|" + pace2);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (selection.equals("full")) {
                    distance = 42.195;
                    pace = Data.averagePace(distance, time);

                    try {
                        saveToFile("Marathon", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        saveToFile("KM", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    double distance2 = Data.kmToMiles(distance);
                    String pace2 = Data.averagePace(distance2, time);

                    try {
                        saveToFile("Miles", dtf.format(now) + "|" + time + "|" + distance2 + "|" + pace2);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }


                } else {
                    distance = Double.parseDouble(distanceTextField.getText());
                    pace = Data.averagePace(distance, time);
                    if (status == true) { //if selection is KM
                        try {
                            saveToFile("KM", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        double distance2 = Data.kmToMiles(distance);
                        String pace2 = Data.averagePace(distance2, time);

                        try {
                            saveToFile("Miles", dtf.format(now) + "|" + time + "|" + distance2 + "|" + pace2);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    } else {//if selection is Miles
                        double distance2 = Data.milesToKm(distance);
                        String pace2 = Data.averagePace(distance2, time);
                        try {
                            saveToFile("Miles", dtf.format(now) + "|" + time + "|" + distance + "|" + pace);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        try {
                            saveToFile("KM", dtf.format(now) + "|" + time + "|" + distance2 + "|" + pace2);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
                tableFrame.dispose();// close old table update new one
                try {
                    tableFrame = new history("Run Log");//make the history jframe.
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            }
        });
    }
    public static void main(String[] args) throws IOException {
        JFrame frame = new GUI("Runner App");
        frame.setVisible(true);

    }
}
