package project_contract.services;

import java.time.LocalDate;

import project_contract.entities.Contract;
import project_contract.entities.Installment;

public class ContractService {
	
	//Dependencia de um serviço de acordo com o projeto
	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {
		double basicQuota = contract.getTotalValue() / months;

		for (int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			double interest = onlinePaymentService.interest(basicQuota, i); 
			double fee = onlinePaymentService.paymentFee(basicQuota + interest);
			double quota = basicQuota + interest + fee;
			
			contract.getInstallments().add(new Installment(dueDate, quota));
		}
	}
}
