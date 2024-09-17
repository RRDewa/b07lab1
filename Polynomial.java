
public class Polynomial {
	double [] coef ;
	public Polynomial(){
		coef = new double [1];
		coef[0] = 0;
	}

	public Polynomial( double [] arr) {
		int n = arr.length ;
		coef = new double [n];
		for(int i = 0; i < n ; i++) {
			coef[i] = arr[i];
		}
	}
	
	public Polynomial add( Polynomial poly) {
		int len_call = coef.length;
		int len_arg = poly.coef.length;
		if (len_call < len_arg) {
			double [] p_res = new double[len_arg];
			int i = 0;
			for(i = 0 ; i < len_call ; i++) {
				p_res[i] = poly.coef[i] + coef[i];
			}
			while(i<len_arg) {
				p_res[i] = poly.coef[i];
				i = i +1;
			}
			Polynomial res = new Polynomial(p_res);
			return res;
		}
		else {
			double [] p_res = new double[len_call];
			int i = 0;
			for(i = 0 ; i < len_arg ; i++) {
				p_res[i] = poly.coef[i] + coef[i];
			}
			while(i<len_call) {
				p_res[i] = coef[i];
				i = i +1;
			}
			Polynomial res = new Polynomial(p_res);
			return res;
		}
	}
	public double evaluate( double arg) {
		double exp = 1;
		int len_call = coef.length;
		double res = 0;
		for(int i = 0 ; i < len_call ; i++) {
			res += coef[i] * exp;
			exp = exp * arg;
		}
		return res;
	}
	
	public boolean hasRoot( double arg) {
		return (this.evaluate(arg) == 0);
	}
}
