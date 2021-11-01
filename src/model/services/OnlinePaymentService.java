package model.services;

public interface OnlinePaymentService {
	
	public abstract Double paymentFee(Double amount);
	public abstract Double interes(Double amount, Integer months );

}
