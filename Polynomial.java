public class Polynomial{
	//Fields
	double [] poly; 
	
	//Methods
	public Polynomial(){
		poly = new double [1];
		poly[0] = 0;
	}
	public Polynomial(double [] px){
		this.poly = px;
	}
	public Polynomial add( Polynomial p1){
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
		double [] poly_res = new double[max_len];
		
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