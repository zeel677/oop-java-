import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();
        try {
            bank.loadState();
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            System.out.println("\n===== MINIBANK =====");
            System.out.println("1. Open Savings Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. List Accounts");
            System.out.println("6. Generate Report");
            System.out.println("7. Save and Exit");
            System.out.print("Enter choice: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1: {
                        System.out.print("Customer ID: ");
                        String customerId = sc.nextLine();
                        System.out.print("Customer name: ");
                        String name = sc.nextLine();
                        System.out.print("Mobile: ");
                        String mobile = sc.nextLine();
                        if (!Validator.validMobile(mobile)) {
                            throw new BankException("Invalid mobile number");
                        }
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        if (!Validator.validEmail(email)) {
                            throw new BankException("Invalid email");
                        }
                        System.out.print("Account number: ");
                        String number = sc.nextLine();
                        System.out.print("Opening balance (Rs.): ");
                        double balance = Double.parseDouble(sc.nextLine());
                        Customer customer = new Customer(
                                customerId, name, mobile, email,
                                new Address("Nadiad", "Gujarat", "387001"));
                        Account account = new SavingsAccount(
                                number, customer,
                                Validator.rupeesToPaise(balance));
                        bank.openAccount(account);
                        System.out.println("Account opened successfully.");
                        break;
                    }
                    case 2:
                        System.out.print("Account number: ");
                        String depositAccount = sc.nextLine();
                        System.out.print("Amount (Rs.): ");
                        double depositAmount =
                                Double.parseDouble(sc.nextLine());
                        bank.deposit(depositAccount, depositAmount);
                        System.out.println("Deposit successful.");
                        break;
                    case 3:
                        System.out.print("Account number: ");
                        String withdrawAccount = sc.nextLine();
                        System.out.print("Amount (Rs.): ");
                        double withdrawAmount =
                                Double.parseDouble(sc.nextLine());
                        bank.withdraw(withdrawAccount, withdrawAmount);
                        System.out.println("Withdrawal successful.");
                        break;
                    case 4:
                        System.out.print("From account: ");
                        String from = sc.nextLine();
                        System.out.print("To account: ");
                        String to = sc.nextLine();
                        System.out.print("Amount (Rs.): ");
                        double transferAmount =
                                Double.parseDouble(sc.nextLine());
                        bank.transfer(from, to, transferAmount);
                        System.out.println("Transfer successful.");
                        break;
                    case 5:
                        for (Account account : bank.listAccounts()) {
                            System.out.println(account);
                        }
                        break;
                    case 6:
                        ReportGenerator.generate();
                        break;
                    case 7:
                        bank.saveState();
                        System.out.println("Data saved. Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (BankException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }
}
