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
		Calendar c = java.util.Calendar.getInstance();
		double vlr = contract.getTotalValue()/months;
		Locale.setDefault(Locale.US);
		
		for(int i = 1;i<=months;i++) {
			
			c.setTime(contract.getDate());
			c.add(Calendar.MONTH, i);
			Double interest = onlinepaymentservice.interes(vlr, i);
			Double fee      = onlinepaymentservice.paymentFee(interest);
			installment.add(new Installment(c.getTime(),fee));
			
			
			
		}
		
		for(Installment list: installment) {
			if (list != null) {
			System.out.println(sdf.format(list.getDueDate()) + " - R$ " + list.getAmount());
			}
		}
		
	}
	
}
