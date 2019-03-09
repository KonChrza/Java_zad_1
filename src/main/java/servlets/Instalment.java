package servlets;

public class Instalment {

	private double credit;
	private double intrest;
	private double flatPayment;
	private double wholePayment;
	
	Instalment (double credit, double intrest, double flatPayment, double wholePayment){
		
		this.credit = credit;
		this.intrest = intrest;
		this.flatPayment = flatPayment;
		this.wholePayment  = wholePayment;
		
	}
	
	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getIntrest() {
		return intrest;
	}

	public void setIntrest(double intrest) {
		this.intrest = intrest;
	}

	public double getFlatPayment() {
		return flatPayment;
	}

	public void setFlatPayment(double flatPayment) {
		this.flatPayment = flatPayment;
	}

	public double getWholePayment() {
		return wholePayment;
	}

	public void setWholePayment(double wholePayment) {
		this.wholePayment = wholePayment;
	}

	
}
