package project_contract.services;

public class PaypalService implements OnlinePaymentService{
	
	//Taxa pagamento de 2%
	@Override
	public double paymentFee(double amount) {
		return amount * 0.02;
	}
	
	//Taxa de juros simples mensal 1%
	@Override
	public double interest(double amount, int months) {
		return amount * 0.01 * months;
	}

	
}
