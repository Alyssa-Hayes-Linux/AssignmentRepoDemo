
import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class PillPopperz {


    public static void main(String[] args) throws DuplicateProfileException {
        ArrayList<Profile> profileList = new ArrayList<>();
        HashSet<String> emailTree = new HashSet<>();
        boolean exit = false;

        while (!exit) {
            int choice = mainMenu();
            switch (choice) {
                case 1: // Select or Create Profile
                    int selected = menuProfileSelect(profileList);
                    if (selected == profileList.size() + 1) {
                        Profile newProfile = newProfile(emailTree);
                        if (newProfile != null) {
                            profileList.add(newProfile);
                            System.out.println("Profile successfully created and saved.");
                        }
                    } else if (selected <= profileList.size()) {
                        // Logic to access an existing profile
                        Profile selectedProfile = profileList.get(selected - 1);
                        mainMenu(selectedProfile); // View or edit selected profile
                    }
                    break;
                case 2: // View All Profiles
                    viewProfiles(profileList);
                    break;
                case 3: // Exit
                    exit = true;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Please select a valid option.");
            }
        }
    }

    /**
     * Main menu display and selection
     *
     * @return user's menu selection
     */
    public static int mainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Main Menu:");
        System.out.println("1. Select or Create Profile");
        System.out.println("2. View All Profiles");
        System.out.println("3. Exit");
        System.out.print("Please select an option: ");
        return input.nextInt();
    }

    /**
     * This method displays all profiles in profileList.
     *
     * @param profileList list of profiles
     */
    public static void viewProfiles(ArrayList<Profile> profileList) {
        if (profileList.isEmpty()) {
            System.out.println("No profiles available.");
        } else {
            System.out.println("Current profiles:");
            for (int i = 0; i < profileList.size(); i++) {
                System.out.println((i + 1) + ". " + profileList.get(i).getName());
            }
        }
    }


    /**
     * Main menu for selected profile actions
     *
     * @param profile user profile
     */



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
        if(name.equals("test")) {
            return new Profile("test");
        }
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

                if(emailTree.contains(email)){
                    throw new DuplicateProfileException();
                }

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
    //TODO: NEEDS SUPPORT FOR CHOOSING AN INVALID OPTION
    public static void mainMenu(Profile profile) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please select an option: \n\t1. View profile\n\t2. View medications");
        int option = input.nextInt();
        if (option == 1) {
            System.out.println(profile.toStringMasked());
        } else if (option == 2) {
            medMenu(profile);
        }

    }//TODO: NEEDS SUPPORT FOR CHOOSING AN INVALID OPTION
    //TODO: ADD SUPPORT FOR VIEWING MEDICATIONS
    public static void medMenu(Profile profile) {
        int listNum = 1;
        System.out.println("Please select an option:");
        if(!profile.medList.isEmpty()) {
            for (Med s : profile.medList) {
                System.out.println("\t" + listNum + ". " + s.getName());
            }
        }
        System.out.println("\t" + listNum + ". Add new medication");
        Scanner input = new Scanner(System.in);
        int selected = input.nextInt();
        if(selected == profile.medList.size() + 1) {
            newMed(profile);
        }
    }

    public static void newMed(Profile profile) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the medication name or nickname:");
        String tempName = input.nextLine();
        Med newMed = new Med();
        boolean flag = true;
        while(flag) {
            try{
                newMed.setName(tempName);
                flag = false;
            } catch(Exception e) {
                System.out.println("Please enter a valid medication name");
                tempName = input.nextLine();
            }
        }
        flag = true;
        ArrayList<DayOfWeek> tempDays = new ArrayList<>();
        while(flag) {
            boolean flag2 = true;
            System.out.println("Please enter a day of the week:");
            while(flag2) {
                try {
                    DayOfWeek tempDay = DayOfWeek.valueOf(input.nextLine().toUpperCase());
                    tempDays.add(tempDay);
                    flag2 = false;
                    System.out.println("Successfully added");
                } catch (Exception e) {
                    System.out.println("Please enter a valid day of the week:");
                }
            }
            System.out.println("Would you like to add another day of the week? \n\t1. yes\n\t2. no");
            int selected = input.nextInt();
            //DO NOT REMOVE
            input.nextLine();
            flag2 = true;
            while(flag2) {
                if(selected == 2) {
                    flag2 = false;
                    flag = false;
                }
                else if(selected == 1) {
                    flag2 = false;
                }
                else {
                    System.out.println("Please select an available option:");
                    selected = input.nextInt();
                }
            }

        }
        newMed.setDaysOfWeek(tempDays);
        flag = true;
        System.out.println("Please enter a time in 24 hour format (00:00): ");
        LocalTime tempTime;
        while(flag) {
            try{
                tempTime = LocalTime.parse(input.nextLine());
                newMed.setTime(tempTime);
                flag = false;
            } catch (Exception e) {
                System.out.println("Please enter a valid time:");
            }
        }
        profile.medList.add(newMed);
        System.out.println("Medication successfully added");
    }
}