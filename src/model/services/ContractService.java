package model.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.time.LocalDate;
import java.util.Calendar;

import model.entities.Contract;
import model.entities.Installment;
import java.util.Locale;

public class ContractService {
	
	private OnlinePaymentService onlinepaymentservice;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	

	public ContractService(OnlinePaymentService onlinepaymentservice) {
		this.onlinepaymentservice = onlinepaymentservice;
	}




	public void processContract(Contract contract, Integer months) {
		

		List<Installment> installment = new ArrayList<>();
		
		double vlr = contract.getTotalValue()/months;
		Locale.setDefault(Locale.US);
		
		for(int i = 1;i<=months;i++) {
			
			double updatedQuota = vlr + onlinepaymentservice.interes(vlr, i); 
			double fullQuota    = updatedQuota + onlinepaymentservice.paymentFee(updatedQuota);
			
			Date dueDate = addMonths(contract.getDate(),i);
			//installment.add(new Installment(dueDate,fullQuota));
			contract.getInstallments().add(new Installment(dueDate,fullQuota));
			
			
		}
		

		
		
		

		
	}

	
	
	private Date addMonths(Date date, int N) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, N);
		
		return c.getTime();
	}
	
}
