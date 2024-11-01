//Pill Popperz Super Class

package src;
// Main class for Pill Popperz
import java.util.Scanner;

public class PillPopperz {

    private Profile profile;
    private boolean profileComplete = false;

    public static void main(String[] args) {
        PillPopperz app = new PillPopperz();
        app.run();
    }

    private void run() {
        System.out.println("Hello");
        int select = 0;
        while (select != 2) {
            select = homeMenu();
            if (!profileComplete) {
                createProfile();
            } else {
                profile.getProfileMasked(); // Display masked profile
            }
        }
        System.out.println("Good Bye :)");
    }

    private void createProfile() {
        Scanner in = new Scanner(System.in);
        Profile temp = new Profile();

        while (!profileComplete) {
            try {
                System.out.println("Creating a new profile");

                System.out.print("Please enter your first and last name: ");
                String name = in.nextLine().trim();

                System.out.print("Please enter your date of birth (MM/DD/YYYY): ");
                String dateOfBirth = in.nextLine().trim();

                System.out.print("Please enter your pronouns: ");
                String pronoun = in.nextLine().trim();

                System.out.print("Please enter your phone number (XXX)XXX-XXXX: ");
                String phone = in.nextLine().trim();

                System.out.print("Please enter your email address: ");
                String email = in.nextLine().trim();

                String password = "";
                String passwordCheck = "";
                boolean match = false;

                while (!match) {
                    System.out.print("Please enter your password: ");
                    password = in.nextLine().trim();

                    System.out.print("Please reenter your password: ");
                    passwordCheck = in.nextLine().trim();

                    if (password.equals(passwordCheck)) {
                        match = true;
                    } else {
                        System.out.println("Passwords do not match. Try again.");
                    }
                }

                System.out.print("Would you like to turn on notifications? (1 for Yes, 2 for No): ");
                boolean notification = in.nextInt() == 1;
                in.nextLine(); // Consume the newline

                // Create a new Profile
                temp = new Profile(name, dateOfBirth, email, phone, pronoun, password, notification);
                profile = temp;
                profileComplete = true;
                System.out.println("Profile created successfully");

            } catch (Exception ex) {
                System.out.println("An error occurred while creating the profile. Please try again.");
            }
        }
    }

    private int homeMenu() {
        System.out.println("Welcome to Pill Popperz");
        System.out.println("Home Page");
        if (!profileComplete) {
            System.out.println("1. New Profile");
            System.out.println("2. Exit");
        } else {
            System.out.println("1. View Profile");
            System.out.println("2. Exit");
        }
        int select = 0;
        Scanner in = new Scanner(System.in);
        while (select != 1 && select != 2) {
            System.out.print("Enter your choice: ");
            select = in.nextInt();
        }
        return select;
    }
}

