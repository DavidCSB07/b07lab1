import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Driver {
    public static void main(String [] args) throws Exception{
        double[] coef = {1, 1, 1};
        int[] expo = {0, 1, 2};
        double[] coef1 = {1, 1, 1, 1};
        int[] expo1 = {0, 1, 3, 9};
        Polynomial p1 = new Polynomial(coef, expo);
        Polynomial p2 = new Polynomial(coef1, expo1);
        Polynomial p3 = p2.add(p1);
        /*
        System.out.println("\ntest one\n");
        for(int i=0; i < p3.exponent.length; i++){
            System.out.println("coeffecient ["+ i + "] = " + p3.coefficients[i]);
            System.out.println("exponent ["+ i + "] = " + p3.exponent[i] + "\n");
        }
         */
        double[] coef2 = {1,1};
        int[] expo2 = {0,1};
        double[] coef3 = {1, 1};
        int[] expo3 = {0,1};
        Polynomial p4 = new Polynomial(coef2, expo2);
        Polynomial p5 = new Polynomial(coef3, expo3);
        Polynomial p6 = p5.multiply(p4);
        /*
        System.out.println("\ntest two\n");
        for(int i=0; i < p6.exponent.length; i++){
            System.out.println("coeffecient ["+ i + "] = " + p6.coefficients[i]);
            System.out.println("exponent ["+ i + "] = " + p6.exponent[i] + "\n");
        }
        */
        
        //BufferedReader input = new BufferedReader(new FileReader("myfile.txt"));
        //File temp = new File("/Users/macbook/b07lab1/myfile.txt");
        File file = new File("/Users/macbook/b07lab1/myfile.txt");
        Polynomial p7 = new Polynomial(file);
        /* 
        for(int i=0; i < p7.exponent.length; i++){
            System.out.println("coeffecient ["+ i + "] = " + p7.coefficients[i]);
            System.out.println("exponent ["+ i + "] = " + p7.exponent[i] + "\n");
        }
        */
        p7.saveToFile("example1");

        
    }
}
