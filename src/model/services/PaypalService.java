package model.services;

public class PaypalService implements OnlinePaymentService{

	
	Double interest = 0.00;
	Double fee      = 0.00;
	
	public PaypalService() {

	}

	
	public PaypalService(Double interest, Double fee) {
		this.interest = interest;
		this.fee = fee;
	}

	@Override
	public Double paymentFee(Double amount) {

		fee = interest * 1.02;
		
		return fee;
	}

	@Override
	public Double interes(Double amount, Integer months) {
		
		double vlr = amount;
		interest = vlr + (vlr * 0.01 * months);
		
		return interest;
	}

}
