interface PaymentProcessor {
    void processPayment(String customerId, double amountInDollars);
}

class OldPayGateway {
    public void chargeInCents(int cents, String accountRef) {
        System.out.println("[OldPayGateway] Charged " + cents + " cents to account: " + accountRef);
    }
}

public class PaymentAdapter implements PaymentProcessor {

    private final OldPayGateway gateway;

    public PaymentAdapter(OldPayGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void processPayment(String customerId, double amountInDollars) {
        int cents = (int) Math.round(amountInDollars * 100.0);
        gateway.chargeInCents(cents, customerId);
    }


    public static void main(String[] args) {
        String studentId = "250103061";
        System.out.println("Student ID: " + studentId);

        PaymentProcessor processor = new PaymentAdapter(new OldPayGateway());
        processor.processPayment(studentId, 142.75);
    }
}
