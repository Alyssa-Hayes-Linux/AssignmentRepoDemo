//Pill Popperz Super Class

package src;
import java.util.Scanner;

public class PillPopperz {

    private Profile profile;
    private boolean profileComplete = false;


    public static void main(String[] args) {
            System.out.println("Hello");
            int select = 0; // character to indicate program section
        while(select != 2) {
            select = homeMenu();
            //if profile not already create profile else ask for login
            if (profileComplete == false) {
                createProfile();
            }
            //Display profile
            else {
                profile.getProfileMasked();
            }

        }


            System.out.println("Good Bye :)");
    }

    //method to create profile and catch incomplete or incorrect information
    private void createProfile() {
        Scanner in = new Scanner(System.in);
        Profile temp = new Profile();

        while (profileComplete == false) {
            try {
                //Ask for entries
                System.out.println("Creating a new profile");

                System.out.println("Please enter your first and last name separated by a space:");
                String name = in.nextLine();
                name = name.trim();

                System.out.println("Please enter your date of birth in MM/DD/YYYY Format:");
                String dateOfBirth = in.nextLine();
                dateOfBirth = dateOfBirth.trim();

                System.out.println("Please enter your pronouns:");
                String pronoun = in.nextLine();
                pronoun = pronoun.trim();

                System.out.println("Please enter your phone number in (XXX)XXX-XXXX) Format:");
                String phone = in.nextLine();
                phone = phone.trim();

                System.out.println("Please enter your email address, this will be your username:");
                String email = in.nextLine();
                email = email.trim();

                String password = "";
                String passwordCheck = "";
                boolean match = false;
                while (!match) {
                    System.out.println("Please enter your password:");
                    String password = in.nextLine();
                    password = password.trim();

                    System.out.println("Please reenter your password:");
                    String passwordCheck = in.nextLine();
                    passwordCheck = passwordCheck.trim();

                    if (password.equals(passwordCheck)) {
                        match = true;
                    } else {
                        System.out.println("Passwords do not match");
                    }
                }

                System.out.println("Would you like to turn on a notifications?");
                int tempSelect = 0;
                System.out.println("1. Yes");
                System.out.println("2. No");
                tempSelect = in.nextInt();
                boolean notification = false;
                if (tempSelect == 1) {
                    notification = true
                }

                //Display entries
                System.out.println("Is the following information correct?");
                System.out.println("Name: " + name);
                System.out.println("Date of birth: " + dateOfBirth);
                System.out.println("Phone number: " + phone);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("Notifications on? " + notification);

                System.out.println("1. Yes / Save");
                System.out.println("2. No / Try Again");
                System.out.println("3. Back To Home");
                tempSelect = in.nextInt();

                //if yes try creating profile if no then ask for reentry or exit
                if (tempSelect == 2) {
                    continue;
                } else if (tempSelect == 2) {
                    temp = new Profile(name, dateOfBirth, email, phone, pronoun,
                            password, notification);
                } else {
                    break;
                }
                //if no exception thrown then setProfile
                System.out.println("Profile created successfully");
                profile = temp;
                profileComplete = true;

            } catch (FormatingExeption ex) {
                int tempSelect = 0;
                System.out.println("Invalid or incomplete information entered, Profile not created. Would you like to try again?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("3. Back To Home");
                if (tempSelect == 1) {
                    continue;
                } else {
                    break;
                }
            }
        }
    }


    //Formating Exception
    public FormatingException extends Exception {
        public FormatingException() {}

        public FormatingException(String message) {
            super(message);
        }
    }

    private int homeMenu(){
            System.out.println("Welcome to the Pill Popperz");
            System.out.println("Home Page");
            if(profileComplete == false){
                System.out.println("1. New Profile");
                System.out.println("2. Exit");
            }
            else {
                System.out.println("1. Profile");
                System.out.println("2. Exit");
            }
            int select = 0;
            while (select != 1 && select !=2) {
                System.out.println("Enter your choice:");
                Scanner in = new Scanner(System.in);
                select = in.nextInt();
            }
            return select;

    }

}