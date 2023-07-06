package project_contract.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import project_contract.entities.Contract;
import project_contract.entities.Installment;
import project_contract.services.ContractService;
import project_contract.services.PaypalService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println("Entre com os dados do contrato:");
		System.out.print("Número: ");
		int number = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		System.out.print("Valor do contrato: ");
		double totalValue = sc.nextDouble();

		Contract contract = new Contract(number, date, totalValue);
		
		System.out.println("Entre com o numero de parcelas: ");
		int n = sc.nextInt();
		
		//Aqui envio para o contract a classe que implementa os métodos para os cálculos de juros
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, n);
		
		System.out.println("Parcelas: ");
		for(Installment installment : contract.getInstallments()) {
			System.out.println(installment);
		}
		
		sc.close();
	}

}
