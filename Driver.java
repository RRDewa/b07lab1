import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) {
		double[] p1_coef = {1,2,3};
	    int[] p1_expo = {0,3,2};
	    Polynomial p1 = new Polynomial(p1_coef, p1_expo);
	    
	    double[] p2_coef = {1,2,-3};
	    int[] p2_expo = {0,3,2};
	    Polynomial p2 = new Polynomial(p2_coef, p2_expo);
	    
	    Polynomial p3 = p1.add(p2);
	    Polynomial p4 = p3.multiply(p3);
	    
	    System.out.println("p1 =" + p1.toString());
	    System.out.println("p2 ="+ p2.toString());
	    System.out.println("p1 + p2 ="+ p3.toString());
	    System.out.println("(p1 + p2)^2 = "+ p4.toString());
	    System.out.println(Polynomial.StringToPolynomial(p4.toString()));
	    
	    double[] p5_coef = {-1,2,3,10,-22,-31};
	    int[] p5_expo = {0,3,2, 6, 5, 4};
	    Polynomial p5 = new Polynomial(p5_coef, p5_expo);
	    
	    double[] p6_coef = {-1,2,-3, 12};
	    int[] p6_expo = {0,3,2,6};
	    Polynomial p6 = new Polynomial(p6_coef, p6_expo);
	    
	    Polynomial p7 = p5.add(p6);
	    Polynomial p8 = p5.multiply(p6);
	    
	    System.out.println("p5 =" + p5.toString());
	    System.out.println("p6 ="+ p6.toString());
	    System.out.println("p5 + p6 ="+ p7.toString());
	    System.out.println("p5 x p6 = "+ p8.toString());
	    System.out.println("p5 x p6 = " + Polynomial.StringToPolynomial(p8.toString()));
	    
	    double[] c1_coef = {6,5};
        int[] c1_expo = {0,3};
        Polynomial c1 = new Polynomial(c1_coef, c1_expo);

        double[] c2_coef = {-2,-9};
        int[] c2_expo = {1,4};
        Polynomial c2 = new Polynomial(c2_coef, c2_expo);

        Polynomial s = c1.add(c2);
        System.out.println(c2.toString());
        if(s.hasRoot(1)){
            System.out.println("1 is a root of s");
        }
        else{
            System.out.println("1 is not a root of s");
        }
        
        Path cur = Paths.get("2_B07Lab2.txt");
        String ss = cur.toAbsolutePath().toString();
        Path p = Paths.get(ss);
        try{
            Files.writeString(p, "1.0x1", StandardCharsets.UTF_8);
        }
        catch(IOException e){
            e.printStackTrace();
	    }
	    c2.saveToFile("1_B07Lab2.txt");
	    File mypoly = new File("2_B07Lab2.txt"); 
	    Polynomial c1file = new Polynomial(mypoly);
	    System.out.println(c1file.toString());
	    
	    p7.saveToFile("3_B07Lab2.txt");
	}
}
