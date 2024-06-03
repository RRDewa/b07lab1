import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Driver {
    public static void main(String[] args){
        Polynomial p0 = new Polynomial();
        System.out.println(p0.max_deg());
        double[] p1_coef = {1,2,3};
        int[] p1_expo = {0,3,2};
        Polynomial p1 = new Polynomial(p1_coef, p1_expo);
        double[] p2_coef = {-1,-2,-3};
        int[] p2_expo = {0,3,2};
        Polynomial p2 = new Polynomial(p2_coef, p2_expo);
        System.out.println(p2.max_deg());
        Polynomial p12_sum = p1.add(p2);
        System.out.println(p12_sum.max_deg());
        Polynomial p12_mult = p1.multiply(p2);
        System.out.println((p12_mult.expo)[4]);
        Polynomial p10_mult = p1.multiply(p0);
        System.out.println(p10_mult.max_deg());
        System.out.println(p12_mult.evaluate(3));

        double[] c1_coef = {6,5};
        int[] c1_expo = {0,3};
        Polynomial c1 = new Polynomial(c1_coef, c1_expo);

        double[] c2_coef = {-2,-9};
        int[] c2_expo = {1,4};
        Polynomial c2 = new Polynomial(c2_coef, c2_expo);

        Polynomial s = c1.add(c2);
        if(s.hasRoot(1)){
            System.out.println("1 is a root of s");
        }
        else{
            System.out.println("1 is not a root of s");
        }


        Path cur = Paths.get("filepoly2.txt");
            String ss = cur.toAbsolutePath().toString();
            Path p = Paths.get(ss);
            try{
                Files.writeString(p, "1.0x1", StandardCharsets.UTF_8);
            }
            catch(IOException e){
                e.printStackTrace();
        }
        c2.saveToFile("filepoly1.txt");
        File mypoly = new File("filepoly2.txt"); 
        Polynomial c1file = new Polynomial(mypoly);
        System.out.println(c1file.max_deg());
        System.out.println((c1file.expo)[0]);
    }
}
