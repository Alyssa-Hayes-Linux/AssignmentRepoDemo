
import java.lang.reflect.Array;
import java.util.*;

public class PillPopperz {


    public static void main(String[] args) throws DuplicateProfileException {
        ArrayList<Profile> profileList = new ArrayList<>();

        int selected = menuProfileSelect(profileList);
        if(selected == profileList.size() + 1) profileList.add(newProfile());

    /**
     * @param profileList list of profiles
     * @return number of profile selected or create new profile
     */
    public static int menuProfileSelect(ArrayList<Profile> profileList) {
        int profileNumber = 1;
        System.out.println("Please select a profile option:");
        if (!profileList.isEmpty()) {
            for (Profile p : profileList) {
                System.out.println("\t" + profileNumber + ". " + p.getName());
                profileNumber++;
            }
        }
        System.out.println("\t" + profileNumber + ". New profile");
        Scanner input = new Scanner(System.in);
        int selected = input.nextInt();
        while (selected < profileList.size() || selected < 1) {
            System.out.println("Input not valid. Please choose an available option.");
            selected = input.nextInt();
        }
        return selected;
    }

    /**
     * This method creates a new profile
     *
     * @return
     * @throws DuplicateProfileException
     */
    public static Profile newProfile(HashSet<String> emailTree) throws DuplicateProfileException {
        Scanner input = new Scanner(System.in);

        Profile toReturn = new Profile();
        //Ask for entries
        System.out.println("Creating a new profile");

        System.out.println("Please enter your first and last name separated by a space:");

        boolean flag = true;
        String name = input.nextLine();
        while (flag) {
            flag = false;
            try {
                toReturn.setName(name.trim());
            } catch (FormatingException e) {
                System.out.println("Please enter your first and last name seperated by a space.");
                name = input.nextLine();
                flag = true;
            }
        }

        System.out.println("Please enter your date of birth in MM/DD/YYYY format:");

        flag = true;
        String dateOfBirth = input.nextLine();
        while (flag) {
            flag = false;
            try {
                toReturn.setDateOfBirth(dateOfBirth.trim());
            } catch (FormatingException e) {
                System.out.println("Please enter a valid date.");
                dateOfBirth = input.nextLine();
                flag = true;
            }
        }

        System.out.println("Please enter your pronouns:");
        String pronoun = input.nextLine();
        toReturn.setPronoun(pronoun.trim());

        System.out.println("Please enter your phone number in (XXX)XXX-XXXX format:");
        flag = true;
        String phone = input.nextLine();
        while (flag) {
            flag = false;
            try {
                toReturn.setPhone(phone.trim());
            } catch (FormatingException e) {
                System.out.println("Please enter a valid phone number.");
                phone = input.nextLine();
                flag = true;
            }
        }


        System.out.println("Please enter your email address, this will be your username:");
        flag = true;
        String email = input.nextLine();
        while (flag) {
            flag = false;
            try {
                toReturn.setEmail(email.trim());
                emailTree.add(email); // Add email to the set
            } catch (FormatingException e) {
                System.out.println("Please enter a valid email.");
                email = input.nextLine();
                flag = true;
            } catch (DuplicateProfileException e) {
                System.out.println("Email is already in use, please enter a new one");
                email = input.nextLine();
                flag = true;
            }
        }

        String password = "";
        String passwordCheck = "0";
        flag = false;
        while (!password.equals(passwordCheck)) {

            if (flag) System.out.println("Passwords do not match");
            flag = true;

            System.out.println("Please enter your password:");
            password = input.nextLine();
            password = password.trim();

            System.out.println("Please reenter your password:");
            passwordCheck = input.nextLine();
            passwordCheck = passwordCheck.trim();

        }
        toReturn.setPassword(password);
        System.out.println("Would you like to turn on a notifications?");
        int tempSelect = 0;
        System.out.println("1. Yes");
        System.out.println("2. No");
        tempSelect = input.nextInt();
        toReturn.setNotification(tempSelect == 1);

        //Display entries
        System.out.println("Is the following information correct?");
        System.out.println("Name: " + name);
        System.out.println("Date of birth: " + dateOfBirth);
        System.out.println("Phone number: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Notifications on? " + toReturn.isNotification());

        System.out.println("1. Yes / Save");
        System.out.println("2. No / Try Again");
        System.out.println("3. Back To Home");

        while (true) {
            tempSelect = input.nextInt();
            switch (tempSelect) {
                case 1:
                    System.out.println("Profile created successfully");
                    return (toReturn);
                case 2:
                    emailTree.remove(email); //remove unused email
                    return (newProfile(emailTree));
                case 3:
                    return null;
                default:
                    System.out.println("Please select an available option.");
            }
        }
    }


//NEW METHOD HERE

    /**
     * This method will accept a profile obj and give you the options to look into the details of whatever profile has been input
     *
     * @param profile user profile entered in main interface
     */
    public static void mainMenu(Profile profile) {
        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 to go to view profile");
        System.out.println("Press 2 to go to medication list");
        int option = input.nextInt();
        if (option == 1) {
            System.out.println(profile.toStringMasked());

        } else if (option == 2) {
            //Later
        }

    }
}