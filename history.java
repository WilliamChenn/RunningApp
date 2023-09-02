package IA;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class history extends JFrame {
    private JPanel tablePanel;
    private JTable table;
    private JComboBox comboBox1;
    private JComboBox SortComboBox;
    private JPanel togglePanel;
    private JPanel prLabelPanel;
    private JLabel icon;
    private JLabel streakLabel;
    private ImageIcon crown;
    private ImageIcon fire;
    private static boolean status = true; //true = km false = miles

    //////  all the object arrays used to create a table COMPLEXITY 1
    Object[][] data = fileArr(new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/kmData"));
    Object[][] data2 = fileArr(new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/milesData"));
    Object[][] mile = fileArr(new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/1Mile"));
    Object[][] five = fileArr(new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/5K"));
    Object[][] ten = fileArr(new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/10K"));
    Object[][] half = fileArr(new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/HalfMarathon"));
    Object[][] marathon = fileArr(new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/Marathon"));
    ////

    public static Object [][] fileArr(File file) throws FileNotFoundException { // return object array filled with contents of file
        Scanner sc = new Scanner(file);
        int length = 0;
        while(sc.hasNextLine()) {
            sc.nextLine();
            length++;
        }
        Object[][] temp = new Object[length][4];
        Scanner sc2 = new Scanner(file);
        for(int i = 0; i<temp.length; i++){
            temp[i] = sc2.nextLine().split("\\|");
        }
        return temp;
    }
    public static String getStreak() throws FileNotFoundException { //gets the streak number
        File file1 = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/streakFile");
        Scanner sc = new Scanner(file1);
        return Integer.toString(sc.nextInt());
    }
    public history(String title) throws FileNotFoundException {
        /////setup
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(tablePanel);
        this.setVisible(true);
        this.pack();
        ////

        //section 2
        crown = new ImageIcon(this.getClass().getResource("crown.png"));//crown png
        icon.setIcon(crown);//crown icon set
        icon.setText("Distance PR: "+ Data.findLongestDate()+
                " ("+Data.kmToMiles(Double.parseDouble(Data.finLongestDistanceKm()))+"Miles) "
                + "(" +Data.finLongestDistanceKm()+ "KM)");//display longest distance
        fire = new ImageIcon(this.getClass().getResource("streakImg.png"));//fire png
        streakLabel.setIcon(fire);//streak icon
        streakLabel.setText("Streak: " + getStreak());//streak number display
        //

        if (status = true) { //COMPLEXITY 4
            createTableKm();
        } else {
            createTableMile();
        }
        comboBox1.addActionListener(new ActionListener() {                    // dropbox that switches from km to miles shows tables
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox temp = (JComboBox) e.getSource();
                String msg = (String) temp.getSelectedItem();
                if (msg.equals("KM")) {
                    status = true;
                    createTableKm(); //display km table
                } else {
                    status = false;
                    createTableMile();//display miles table
                }

            }
        });
        SortComboBox.addActionListener(new ActionListener() { //dropbox that basically just displays sorted runs
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox temp = (JComboBox) e.getSource();
                String msg = (String) temp.getSelectedItem();
                if(msg.equals("1 Mile")){ //display 1 mile table
                    togglePanel.setVisible(false);
                    table.setModel(new DefaultTableModel(
                            mile,
                            new String[]{"Date/time", "Duration", "Distance(Mi)", "Pace mins/mi"}
                    ));
                    try {
                        icon.setText("Mile PR: " + Data.findPR("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/1Mile"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else if(msg.equals("5K")){ //display 5k table
                    togglePanel.setVisible(false);
                    table.setModel(new DefaultTableModel(
                            five,
                            new String[]{"Date/time", "Duration", "Distance(KM)", "Pace mins/km"}
                    ));
                    try {
                        icon.setText("5K PR: " + Data.findPR("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/5K"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else if(msg.equals("10K")){//display 10k table
                    togglePanel.setVisible(false);
                    table.setModel(new DefaultTableModel(
                            ten,
                            new String[]{"Date/time", "Duration", "Distance(KM)", "Pace mins/km"}
                    ));
                    try {
                        icon.setText("10K PR: " + Data.findPR("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/10K"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else if(msg.equals("Half Marathon")){//display half marathon table
                    togglePanel.setVisible(false);
                    table.setModel(new DefaultTableModel(
                            half,
                            new String[]{"Date/time", "Duration", "Distance(KM)", "Pace mins/km"}
                    ));
                    try {
                        icon.setText("Half Marathon PR: " + Data.findPR("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/HalfMarathon"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else if(msg.equals("Marathon")){//full marathon table
                    togglePanel.setVisible(false);
                    table.setModel(new DefaultTableModel(
                            marathon,
                            new String[]{"Date/time", "Duration", "Distance(KM)", "Pace mins/km"}
                    ));
                    try {
                        icon.setText("Marathon PR: " + Data.findPR("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/specificDistanceFiles/Marathon"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else if(msg.equals("All Runs")){ //displays all runs
                    togglePanel.setVisible(true);
                    if (status = true) {
                        createTableKm();

                    } else {
                        createTableMile();

                    }
                    try {
                        icon.setText("Distance PR: "+ Data.findLongestDate()+" ("+Data.kmToMiles(Double.parseDouble(Data.finLongestDistanceKm()))+"Miles) " + "(" +Data.finLongestDistanceKm()+ "KM)");
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
        });
    }

    private void createTableKm() { //creates km table
        table.setModel(new DefaultTableModel(
                data,
                new String[]{"Date/time", "Duration", "Distance(KM)", "Pace mins/km"}
        ));
    }
    private void createTableMile() { //creates mile table
        table.setModel(new DefaultTableModel(
                data2,
                new String[]{"Date/time", "Duration", "Distance(Mi)", "Pace mins/mi"}
        ));
    }
}