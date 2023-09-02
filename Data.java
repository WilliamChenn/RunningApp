package IA;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Arrays;
public class Data {
    public static String formatTime(int hours, int mins, int secs){ // formats time from hours, mins and secs to "00:00:00"
        String time = "";
        if(hours <10){
            time += "0"+Integer.toString(hours);
        }
        else{
            time += Integer.toString(hours);
        }
        time +=":";
        if(mins <10){
            time += "0"+Integer.toString(mins);
        }
        else{
            time += Integer.toString(mins);
        }
        time+=":";
        if(secs <10){
            time += "0"+ Integer.toString((secs));
        }
        else{
            time += Integer.toString(secs);
        }
        return time;
    }
    public static String averagePace(double distance, String time){//calculate average pace given distance and time("00:00:00")
        String average ="";
        long secPerDistance = 0;
        long totalSeconds = 0;
        totalSeconds += Integer.parseInt(time.substring(0,2))*3600;
        totalSeconds += Integer.parseInt(time.substring(3,5)) * 60;
        totalSeconds += Integer.parseInt(time.substring(6));
        secPerDistance = Math.round(totalSeconds/distance);
        int hours =0 ,min=0, sec=0 ;
        if(secPerDistance > 3600){
            hours = (int)(secPerDistance/3600);
            secPerDistance = secPerDistance%3600;
        }
        if(secPerDistance > 60){
            min = (int)(secPerDistance/60);
            secPerDistance = secPerDistance%60;
        }
        sec = (int)secPerDistance;
        average = formatTime(hours, min, sec);
        return average;
    }
    public static double kmToMiles(double km){//converts km to miles
        BigDecimal round = new BigDecimal(km/1.609).setScale(2, RoundingMode.HALF_UP);
        return round.doubleValue();
    }
    public static double milesToKm(double miles){// converts miles to km
        BigDecimal round = new BigDecimal(miles*1.609).setScale(2, RoundingMode.HALF_UP);
        return round.doubleValue();
    }
    public static int listCount() throws IOException {//counts the amount of lines in a file
        BufferedReader reader = new BufferedReader(new FileReader("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/kmData"));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }
    public static boolean streakCounter() throws IOException { //checks if the most recent run is the day before today, if so then streak is not broken
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");// find time
        LocalDate today = LocalDate.now();//today time
        LocalDate yesterday = LocalDate.now().minusDays( 1 );//yesterday time
        System.out.println("today: " +dtf.format(today) + "yesterday:" + dtf.format(yesterday)+"bruh");
        BufferedReader input = new BufferedReader(new FileReader("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/kmData"));
        String last= "";
        String line = "" ;
        while ((line = input.readLine()) != null) { //COMPLEXITY 6
            last = line;
        }
        last = last.substring(0,10);
        System.out.println(last);
        if(last.equals(dtf.format(yesterday))){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean sameDay() throws IOException { //check if most recent entry is on the same day as the one just entered
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");// find time
        LocalDate today = LocalDate.now();//today time
        BufferedReader input = new BufferedReader(new FileReader("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/kmData"));
        String last= "";
        String line = "" ;
        while ((line = input.readLine()) != null) {
            last = line;
        }
        last = last.substring(0,10);
        System.out.println(last);
        if(last.equals(dtf.format(today))){
            return true;
        }
        else{
            return false;
        }
    }
    public static void saveToFile(String fileName, String text) throws IOException { //save text to a file
        File file1 = new File(fileName);
        FileWriter fw = new FileWriter(file1, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.print(text);
        pw.close();
    }
    public static void incrementStreak(String choice) throws IOException {//increments streak in the file
        File file1 = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/streakFile");
        Scanner sc = new Scanner(file1);
        int temp = sc.nextInt();
        if(choice.equals("plus")) {
            temp++;
        }
        else if(choice.equals("one")){
            temp = 1;
        }
        else{
            temp = 0;
        }
        PrintWriter writer = new PrintWriter(file1);
        writer.print("");
        writer.close();
        saveToFile("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/streakFile", Integer.toString(temp));
        System.out.println(temp);
    }
    public static String findPR(String fileName) throws  IOException{ //find personal record (fastest time)
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        String pr = "";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        if(lines == 1){ // if only one element then that means pr is that one
            String temp = sc.nextLine();
            pr = "["+temp.substring(0,10)+"] "+ temp.substring(20,28);
        }
        else if(lines == 0){
            pr = "N/A";
        }
        else { //else calculate personal best
            long intPr = Long.MAX_VALUE;
            String returnTime = "";
            String returnDate = "";
            String temp;
            while (sc.hasNextLine()) {
                temp = sc.nextLine();
                String tempTime = temp.substring(20, 28);// get time
                if (timeToSeconds(tempTime) < intPr) {
                    intPr = timeToSeconds(tempTime);
                    returnTime = temp.substring(20,28);
                    returnDate = temp.substring(0,10);
                }
            }
            pr = "["+returnDate+"] "+ returnTime;
        }
        return pr;
    }
    public static String finLongestDistanceKm() throws FileNotFoundException {//find longest distance ran
        String longestDistance = "";
        File file = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/kmData");
        Scanner sc = new Scanner(file);
        double longest = 0;
        while(sc.hasNextLine()){
            String [] tempArr = sc.nextLine().split("\\|");
            double temp = Double.parseDouble(tempArr[2]);
            if(temp>longest){
                longest = temp;
            }
        }
        longestDistance = Double.toString(longest);
        return longestDistance;
    }
    public static String findLongestDate() throws  FileNotFoundException{ //find the date of longest distance ran
        String date = "";
        File file = new File("/Users/william/IdeaProjects/School/IB Compsci SL/src/IA/kmData");
        Scanner sc = new Scanner(file);
        double longest = 0;
        while(sc.hasNextLine()){
            String [] tempArr = sc.nextLine().split("\\|");
            double temp = Double.parseDouble(tempArr[2]);
            String datetemp = tempArr[0].substring(0,10);
            if(temp>longest){
                date = datetemp;
            }
        }
        date = "[" +date + "]";
        return date;
    }
    public static long timeToSeconds(String time){ //convert "00:00:00" into seconds
        String hours = time.substring(0,2);
        String minutes = time.substring(3,5);
        String seconds = time.substring(6);
        long sec = (Integer.parseInt(hours) *  3600) + (Integer.parseInt(minutes) * 60) + (Integer.parseInt(seconds));
        return sec;
    }

}
