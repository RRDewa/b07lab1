import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Polynomial{
	//Fields
	double [] coef; 
	int [] expo;
	
	//Methods
	public Polynomial(){
		coef = null;
		
		expo = null ;
	}
	public Polynomial(double [] coef, int [] expo){
		this.coef = coef;
		this.expo = expo;
	}
	public Polynomial(File textfile){
		String purestring = textfile.getAbsolutePath();
		String poly_s = "";
		try{
			poly_s = new String(Files.readAllBytes(Paths.get(purestring)));
		}
		catch(IOException e){
	        e.printStackTrace();
	    }
		String[] arr_spoly = poly_s.split("[+-]");
		int len_arr = arr_spoly.length;
		coef = new double[len_arr];
		expo = new int[len_arr];

		for(int i = 0; i < len_arr ; i = i + 1){
			int idxx = arr_spoly[i].indexOf('x');
			if (idxx < 0){
				coef[i] = Double.parseDouble(arr_spoly[i]);
				expo[i] = 0;
			}
			else{
				coef[i] = Double.parseDouble(arr_spoly[i].substring(0, idxx - 1));
				expo[i] = Integer.parseInt(arr_spoly[i].substring(idxx + 1));

			}
		}
		if(poly_s.charAt(0) == '-') {
			coef[0] = coef[0] * -1;
		}
		int iter = 1;
		for(int i = 1; i < poly_s.length(); i = i + 1){
			if(poly_s.charAt(i) == '-'){
				coef[iter] *= coef[iter] * -1;
				iter = iter + 1;
			}
			else if(poly_s.charAt(i) == '+'){
				iter = iter + 1;
			}
		}

	}

	public int max_deg(){
		if (this.expo == null){
			return -1;
		}
		int l = (this.expo).length;
		int max_deg = (this.expo)[0];
		for (int i = 0 ; i < l ; i = i + 1){
			if (max_deg < (this.expo)[i]) {
				max_deg = (this.expo)[i];
			}
		}
		return max_deg;
	}
	public Polynomial add( Polynomial p1){

		if (p1.expo == null){
            Polynomial res = new Polynomial();
            res.coef = p1.coef;
            res.expo = p1.expo;
            return res;
        }

        if (this.expo == null){
            Polynomial res = new Polynomial();
            res.coef = coef;
            res.expo = expo;
            return res;
        }

		int l1 = (p1.expo).length;
		int l = (this.expo).length;

        int max_deg;
		if (p1.max_deg() > this.max_deg()){
			max_deg = p1.max_deg();
		}
		else {
			max_deg = this.max_deg();
		}

		double [] coef_iter = new double[max_deg + 1];
		for (int i = 0 ; i < (max_deg + 1) ; i = i + 1){
			coef_iter[i] = 0;
		}
		// int [] expo_iter = new int[max_deg + 1];
		int deg_iter;

		for (int i = 0 ; i < l ; i = i + 1){
			deg_iter = (this.expo)[i];
			coef_iter[deg_iter] = coef_iter[deg_iter] + (this.coef)[i];
		}

		for (int i = 0 ; i < l1 ; i = i + 1){
			deg_iter = (p1.expo)[i];
			coef_iter[deg_iter] = coef_iter[deg_iter] + (p1.coef)[i];
		}

		int natural_deg = 0;
		for (int i = 0 ; i < (max_deg + 1) ; i = i + 1){
			if (coef_iter[i] != 0){
				natural_deg += 1;
			}
		}

		if (natural_deg == 0) {
			Polynomial poly_res = new Polynomial();
			return poly_res;
		}

		double[] coef_res = new double[natural_deg];
		int [] expo_res = new int[natural_deg];

		int expo_iter = 0;
		for (int i = 0 ; i < (max_deg + 1) ; i = i + 1){
			if (coef_iter[i] != 0){
				coef_res[expo_iter] = coef_iter[i];
				expo_res[expo_iter] = i;
				expo_iter += 1;
			}
		}

		Polynomial poly_res = new Polynomial (coef_res, expo_res);
		return poly_res;
	}
	public Polynomial multiply(Polynomial p1){
		if ( (this.expo == null) || (p1.expo == null)){
            Polynomial res = new Polynomial();
            return res;
        }

		int l1 = (p1.expo).length;
		int l = (this.expo).length;

        int max_deg = (this.max_deg() + p1.max_deg());
		double[] coef_iter = new double[max_deg + 1];

		int p_expo ;
		double p_coef ;
		int p1_expo;
		double p1_coef;
		for (int i = 0 ; i < l ;i = i + 1){
			p_expo = (this.expo)[i];
			p_coef = (this.coef)[i];
			for (int j = 0 ; j < l1 ; j = j + 1){
				p1_expo = (p1.expo)[j];
				p1_coef = (p1.coef)[j];
				coef_iter[p_expo + p1_expo] += p_coef*p1_coef;
			}
		}
		int natural_deg = 0;
		for (int i = 0 ; i < (max_deg + 1) ; i = i + 1){
			if (coef_iter[i] != 0){
				natural_deg += 1;
			}
		}

		if (natural_deg == 0) {
			Polynomial poly_res = new Polynomial();
			return poly_res;
		}

		double[] coef_res = new double[natural_deg];
		int [] expo_res = new int[natural_deg];

		int expo_iter = 0;
		for (int i = 0 ; i < (max_deg + 1) ; i = i + 1){
			if (coef_iter[i] != 0){
				coef_res[expo_iter] = coef_iter[i];
				expo_res[expo_iter] = i;
				expo_iter += 1;
			}
		}

		Polynomial poly_res = new Polynomial (coef_res, expo_res);
		return poly_res;
	}

	double evaluate(double arg){
		if (this.expo == null){
			return 0;
		}
		int max_deg = this.max_deg();

		double result = 0;
		for (int i = 0 ; i < max_deg ; i = i + 1){
			double expo_arg = 1;
			for (int j = 0; j < (this.expo)[i]; j = j + 1){
				expo_arg = expo_arg*arg;
			}
			result = result + (this.coef)[i]*expo_arg;
		}
		return result;
	}
	
	boolean hasRoot(double arg){
		return (this.evaluate(arg) == 0);
	}

	void saveToFile(String s){
		String out = "";
		for(int i = 0; i < coef.length; i++){
			if( (i != 0) && (coef[i] > 0)){
				out = out + "+";
			}
			out = out + Double.toString(coef[i]);
			if(expo[i] != 0){
				out = out + "x";
				out = out + Integer.toString(expo[i]);
			}
		}
		Path cur = Paths.get(s);
		String ss = cur.toAbsolutePath().toString();
		Path p = Paths.get(ss);
		try{
			Files.writeString(p, out, StandardCharsets.UTF_8);
		}
		catch(IOException e){
	        e.printStackTrace();
	    }
	}

}