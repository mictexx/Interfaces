package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {


	public static void main(String[] args) throws ParseException {
	
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		
		System.out.println("Enter contract data");
		System.out.print("Number:");
		int numero = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy):");
		Date date = sdf.parse(sc.next());
		System.out.print("Contract value:");
		Double amount = sc.nextDouble();
		System.out.print("Enter number of installments:");
		int installments = sc.nextInt();
		
		Contract contract = new Contract(numero,date,amount);
		
		System.out.println("Contract number: "+contract.getNumber());
		System.out.println("Contract date: "+contract.getDate());
		System.out.println("Contract amount: "+contract.getTotalValue());
		
		PaypalService paypal = new PaypalService();
		
		ContractService contractservice = new ContractService(paypal);
		
		contractservice.processContract(contract, installments);
		
		sc.close();	
	}

}
