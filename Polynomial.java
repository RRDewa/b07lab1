import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;   // Import the FileWriter class
import java.io.BufferedWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class Polynomial {
	double [] coef ;
	int [] exp;
	
	public Polynomial(){
		coef = new double [0];
		exp = new int [0];
	}

	public Polynomial( double [] arr_coef, int [] arr_exp) {
		coef = arr_coef;
		exp = arr_exp;
	}
	
	public Polynomial(File myfile) {
		try {
		      Scanner myReader = new Scanner(myfile);
		      String data = myReader.nextLine();
		      myReader.close();
		      Polynomial p = StringToPolynomial(data);
		      coef = p.coef;
		      exp = p.exp;
		    } catch (FileNotFoundException e) {
		      System.out.println("File Not Found. An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private static int FindMaxArray(int []arr) {
		int len = arr.length ;
		if(len == 0) {
			return 0;
		}
		int max = arr[0];
		for(int i = 0 ; i < len ; i = i + 1) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}
	
	private static int HowManyNonZero(double [] arr, int len_arr) {
		int  len = 0;
		for(int i = 0; i < len_arr ; i = i + 1) {
			if(arr[i] != 0) {
				len = len + 1;
			}
		}
		return len;
	}
	
	private static Polynomial NonZeroPolynomial(double [] coef_temp, int deg_temp) {
		int len = Polynomial.HowManyNonZero(coef_temp, deg_temp + 1);
		
		int [] exp_res = new int [len];
		double [] coef_res = new double [len];
		
		int j = 0;
		for(int i = 0; i < deg_temp + 1 ; i = i + 1) {
			if(coef_temp[i] != 0) {
				exp_res[j] = i;
				coef_res[j] = coef_temp[i];
				j = j + 1;
			}
		}
		return new Polynomial(coef_res, exp_res);
	}
	
	public Polynomial add( Polynomial poly) {
		int deg_this = Polynomial.FindMaxArray(this.exp);
		int deg_poly = Polynomial.FindMaxArray(poly.exp);
		int deg_res;
		if(deg_this < deg_poly) {
			deg_res = deg_poly;
		}
		else {
			deg_res = deg_this;
		}
		double [] coef_temp = new double [deg_res + 1];
		
		for(int i = 0; i < (this.exp).length ; i = i + 1) {
			coef_temp[(this.exp)[i]] = (this.coef)[i];
		}
		for(int i = 0; i < (poly.exp).length ; i = i + 1) {
			coef_temp[(poly.exp)[i]] += (poly.coef)[i];
		}
		
		Polynomial res = Polynomial.NonZeroPolynomial(coef_temp, deg_res);
		
		return res;
	}
	
	private double expo(double arg, int expn) {
		double res = 1;
		for(int i = 0 ; i < expn ; i = i + 1) {
			res = res * arg;
		}
		return res;
	}
	
	public double evaluate( double arg) {
		int len_call = coef.length;
		double res = 0;
		for(int i = 0 ; i < len_call ; i = i + 1) {
			res = res + coef[i] * expo(arg, exp[i]) ;
		}
		return res;
	}
	
	public boolean hasRoot( double arg) {
		return (this.evaluate(arg) == 0);
	}
	
	public Polynomial multiply(Polynomial p) {
		int deg_this = Polynomial.FindMaxArray(this.exp);
		int deg_poly = Polynomial.FindMaxArray(p.exp);
		int deg_res = deg_this + deg_poly;
		double [] coef_temp = new double [deg_res + 1];
		
		for(int i= 0 ; i < (this.coef).length; i = i + 1 ) {
			for(int j = 0 ; j < (p.coef).length ; j = j + 1) {
				coef_temp[(this.exp)[i]+(p.exp)[j]] += (this.coef)[i] * (p.coef)[j];
			}
		}
		Polynomial res = Polynomial.NonZeroPolynomial(coef_temp, deg_res);
		return res;
	}
	
	private String CheckCoef(double arg) {
		if (arg >= 0) {
			return "+";
		}
		return "";
	}
	
	@Override
	public String toString() {
		int len = coef.length;
		if(len == 0) {
			return "";
		}
		String res;
		
		if(exp[0] > 0) {
			res = Double.toString(coef[0]) + "x" + Integer.toString(exp[0]) ;
		}
		else {
			res = Double.toString(coef[0]);
		}
		
		for(int i = 1 ; i < len ; i = i + 1) {
			if(exp[i] == 0) {
				res = res + CheckCoef(coef[i]) + Double.toString(coef[i]);
				continue;
			}
			res = res + CheckCoef(coef[i]) + Double.toString(coef[i]);
			res = res + "x" +  Integer.toString(exp[i]);
		}
		
		return res;
	}
	
	public static Polynomial StringToPolynomial(String s) {
		String[] terms = s.split("(?=[+-])");
		int poly_len = terms.length;
		int [] p_exp = new int [poly_len ];
		double [] p_coef = new double [poly_len ];
		
		for(int i = 0; i < poly_len  ; i = i + 1) {
			String term = terms[i];
			String [] part = term.split("x");
			p_coef[i] = Double.parseDouble(part[0]);
			if(part.length == 1) {
				p_exp[i] = 0;
				continue;
			}
			p_exp[i] = Integer.parseInt(part[1]);
		}
		Polynomial p = new Polynomial(p_coef, p_exp);
		return p;
	}
	
	public void saveToFile(String fileName) {
		String content = this.toString();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
			}
	}
}
