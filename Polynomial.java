public class Polynomial{
	//Fields
	double [] coef; 
	int [] expo;
	
	//Methods
	public Polynomial(){
		coef = new double [0];
		
		expo = new int [0] ;
	}
	public Polynomial(double [] coef, int [] expo){
		this.coef = coef;
		this.expo = expo;
	}
	public Polynomial add( Polynomial p1){
		int idx = 0;
		int idx_1 = 0;
		int l1 = (p1.poly).length;
		int l = (this.poly).length;
		
		int min_len;
		int max_len;
		if (l1 <= l){
			min_len = l1;
			max_len = l;
		}
		else{
			min_len = l;
			max_len = l1;
		}
		
		double [] coefx_res = new double[max_len];
		int [] expox_res = new expo[max_len];
		
		int res_len = 0;
		int idx_iter = 0;
		int idx1_iter = 0;
		for (int i = 0; i < (max_len + min_len) ; i = i + 1){
			if ( ((idx1_iter < l1) && ((p1.expo)[idx1_iter] == i)) ||( (idx_iter < l) && (expo[idx_iter] == i)) ){
				coefx_res[res_len] = 0;
				expox_res[res_len] = i;
				if ((idx1_iter < l1) && ((p1.expo)[idx1_iter] == i)){
					coefx_res[res_len] += (p1.coef)[idx1_iter];
					idx1_iter += 1;
				}
				if ((idx_iter < l) && (expo[idx_iter] == i)){
					coefx_res[res_len] += coef[idx_iter];
					idx_iter += 1;
				}
			}
			res_len += 1;
		}
		
		
		
		int idx = 0;
		for (int i = 0; i < min_len ; i = i + 1){
			poly_res[i] = (p1.poly)[i] + poly[i];
			idx = i + 1;
		}
		if(l1 < l){
			for (int i = idx; i < max_len ; i = i +1){
				poly_res[i] = poly[i];
			}
		}
		else{
			for (int i = idx; i< max_len ; i = i +1){
				poly_res[i] = (p1.poly)[i];
			}
		}
		Polynomial result = new Polynomial(poly_res);
		return result;
	}
	public double evaluate(double x){
		double mapto =0;
		int idx = 0;
		for (double coef:poly){
			if (coef == 0){
				idx = idx + 1;
				continue;
			}
			double exp = 1;
			for (int i = 1; i <= idx ; i = i + 1){
				exp = exp * x;
			}
			mapto = mapto + coef * exp;
			idx = idx + 1;
		}
		return mapto;
	}
	public boolean hasRoot(double x){
		if (evaluate(x) == 0){
			return true;
		}
		else{
			return false;
		}
	}
}
