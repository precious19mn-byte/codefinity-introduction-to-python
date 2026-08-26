import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<User> users = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        users.add(new User("admin", "1234", "Admin Business"));

        while (true) {        javac Admin.java        javac Admin.java
            System.out.println("\n=== Mobile Services App ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser();
                    if (currentUser != null) {
                        showDashboard();
                    }
                    break;
                case "3":
                    System.out.println("Thank you for using the app.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim();

        if (username.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }

        for (User user : users) {
            if (user.username.equalsIgnoreCase(username)) {
                System.out.println("Username already exists. Please choose another one.");
                return;
            }
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine().trim();

        System.out.print("Enter your business name: ");
        String businessName = scanner.nextLine().trim();

        if (password.isEmpty() || businessName.isEmpty()) {
            System.out.println("Password and business name are required.");
            return;
        }

        users.add(new User(username, password, businessName));
        System.out.println("Registration successful. You can now log in.");
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        for (User user : users) {
            if (user.username.equalsIgnoreCase(username) && user.password.equals(password)) {
                currentUser = user;
                System.out.println("Login successful. Welcome, " + currentUser.businessName + "!");
                return;
            }
        }

        System.out.println("Invalid username or password.");
        currentUser = null;
    }

    private static void showDashboard() {
        while (currentUser != null) {
            System.out.println("\n=== Dashboard ===");
            System.out.println("1. Buy Airtime");
            System.out.println("2. Pay Membership");
            System.out.println("3. Buy Electricity");
            System.out.println("4. View Profile");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    buyAirtime();
                    break;
                case "2":
                    payMembership();
                    break;
                case "3":
                    buyElectricity();
                    break;
                case "4":
                    viewProfile();
                    break;
                case "5":
                    currentUser = null;
                    System.out.println("You have logged out.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void buyAirtime() {
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine().trim();
        System.out.print("Enter airtime amount: ");
        String amountText = scanner.nextLine().trim();

        try {
            double amount = Double.parseDouble(amountText);
            System.out.printf("Airtime purchase of %.2f for %s was successful.%n", amount, phoneNumber);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
        }
    }

    private static void payMembership() {
        System.out.print("Enter membership plan name: ");
        String plan = scanner.nextLine().trim();
        System.out.print("Enter membership amount: ");
        String amountText = scanner.nextLine().trim();

        try {
            double amount = Double.parseDouble(amountText);
            System.out.printf("Membership payment of %.2f for %s was successful.%n", amount, plan);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
        }
    }

    private static void buyElectricity() {
        System.out.print("Enter meter number: ");
        String meterNumber = scanner.nextLine().trim();
        System.out.print("Enter electricity amount: ");
        String amountText = scanner.nextLine().trim();

        try {
            double amount = Double.parseDouble(amountText);
            System.out.printf("Electricity purchase of %.2f for meter %s was successful.%n", amount, meterNumber);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid amount.");
        }
    }

    private static void viewProfile() {
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        System.out.println("=== User Profile ===");
        System.out.println("Username: " + currentUser.username);
        System.out.println("Business Name: " + currentUser.businessName);
    }

    private static class User {
        private final String username;
        private final String password;
        private final String businessName;

        public User(String username, String password, String businessName) {
            this.username = username;
            this.password = password;
            this.businessName = businessName;
        }
    }
}
