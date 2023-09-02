package IA;
import javax.swing.*;
import java.io.IOException;

public class RunnerApp {

    public double traditionalMethod( double base, double height){
        double area = (0.5)*(base)*height;
        return area;
    }


    public static double findArea (double a, double b, double angle){
        double radians = Math.toRadians(angle);
        double area = (0.5)*(a)*(b)*Math.sin(radians);
        return area;
    }
    public static void main(String[] args) throws IOException {
        JFrame frame = new GUI("Runner App");
        frame.setVisible(true);
    }

}
