import java.util.Scanner;

public class QN1_PartB_Answers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Vehicle Registration Number: ");
        String registrationNumber = scanner.nextLine();

        System.out.print("Enter Vehicle Cost: ");
        double vehicleCost = scanner.nextDouble();
        scanner.nextLine();

        String highestBidderName = "";
        double highestBid = 0;

        for (int i = 1; i <= 3; i++) {
            System.out.print("Enter name of Bidder " + i + ": ");
            String name = scanner.nextLine();
            System.out.print("Enter bid amount for " + name + ": ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount > highestBid) {
                highestBid = amount;
                highestBidderName = name;
            }
        }

        System.out.println("\n--- Auction Result ---");
        System.out.println("Vehicle Registration: " + registrationNumber);
        System.out.println("Vehicle Cost: " + vehicleCost);
        System.out.println("Highest Bidder: " + highestBidderName);
        System.out.println("Highest Bid Amount: " + highestBid);

        System.out.print("\nEnter expenses incurred on vehicle: ");
        double expenses = scanner.nextDouble();

        double profitOrLoss = highestBid - (vehicleCost + expenses);

        System.out.println("\n--- Financial Summary ---");
        if (profitOrLoss > 0) {
            System.out.println("Profit Made: " + profitOrLoss);
        } else if (profitOrLoss < 0) {
            System.out.println("Loss Incurred: " + Math.abs(profitOrLoss));
        } else {
            System.out.println("No Profit or Loss (Break-even).");
        }
        
        scanner.close();
    }
}
