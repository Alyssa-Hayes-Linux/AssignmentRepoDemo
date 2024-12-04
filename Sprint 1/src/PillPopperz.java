import java.time.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.Toolkit;

public class PillPopperz {

    public static void main(String[] args) {
        ArrayList<Profile> profileList = new ArrayList<>(); // List to store user profiles
        HashSet<String> emailTree = new HashSet<>(); // Set to track unique emails
        boolean exit = false;

        // Main loop to display the menu and process user options
        while (!exit) {
            int choice = mainMenu(); // Display main menu
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
                        // Access an existing profile
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
     * Displays the main menu and prompts the user to select an option.
     *
     * @return user's menu selection
     */
    public static int mainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Main Menu:");
        System.out.println("\t1. Select or Create Profile");
        System.out.println("\t2. View All Profiles");
        System.out.println("\t3. Exit");
        System.out.print("Please select an option:\n");
        return input.nextInt();
    }

    /**
     * Displays all profiles in profileList.
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
     * Displays the menu for selecting or creating a profile.
     *
     * @param profileList list of profiles
     * @return the index of the selected profile or option to create a new profile
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
        profileNumber++;
        System.out.println("\t" + profileNumber + ". Go Back");
        Scanner input = new Scanner(System.in);
        int selected = input.nextInt();
        while (selected < profileList.size() - 1 || selected < 1) {
            System.out.println("Input not valid. Please choose an available option.");
            selected = input.nextInt();
        }
        return selected;
    }

    /**
     * Creates a new profile by gathering input from the user, validates fields,
     * and ensures the email is unique using emailTree.
     *
     * @return the created Profile object or null if creation is cancelled
     */
    public static Profile newProfile(HashSet<String> emailTree) {
        Scanner input = new Scanner(System.in);

        Profile toReturn = new Profile(); // Temporary profile object for data input
        System.out.println("Creating a new profile");

        // Input and validate name
        System.out.println("Please enter your first and last name separated by a space:");
        boolean flag = true;
        String name = input.nextLine();
        if (name.equals("test")) {
            emailTree.add("Herbstb@vcu.edu");
            return new Profile("test");

        }
        while (flag) {
            flag = false;
            try {
                toReturn.setName(name.trim());
            } catch (FormattingException e) {
                System.out.println("Please enter your first and last name separated by a space.");
                name = input.nextLine();
                flag = true;
            }
        }

        // Input and validate date of birth
        System.out.println("Please enter your date of birth in MM/DD/YYYY format:");
        flag = true;
        String dateOfBirth = input.nextLine();
        while (flag) {
            flag = false;
            try {
                toReturn.setDateOfBirth(dateOfBirth.trim());
            } catch (FormattingException e) {
                System.out.println("Please enter a valid date.");
                dateOfBirth = input.nextLine();
                flag = true;
            }
        }

        // Input pronouns
        System.out.println("Please enter your pronouns in XXX/XXX format:");
        flag = true;
        while (flag) {
            try {
                String pronoun = input.nextLine();
                toReturn.setPronoun(pronoun.trim());
                flag = false;

            } catch (FormattingException e) {
                System.out.println("Please enter valid pronouns in XXX/XXX format");
            }
        }
        // Input and validate phone number
        System.out.println("Please enter your phone number in (XXX)XXX-XXXX format:");
        flag = true;
        String phone = input.nextLine();
        while (flag) {
            flag = false;
            try {
                toReturn.setPhone(phone.trim());
            } catch (FormattingException e) {
                System.out.println("Please enter a valid phone number.");
                phone = input.nextLine();
                flag = true;
            }
        }

        // Input and validate email
        System.out.println("Please enter your email address, this will be your username:");
        flag = true;
        String email = input.nextLine();
        while (flag) {
            flag = false;
            try {
                toReturn.setEmail(email.trim());

                if (emailTree.contains(email)) {
                    throw new DuplicateProfileException();
                }

                emailTree.add(email); // Add email to the set

            } catch (FormattingException e) {
                System.out.println("Please enter a valid email.");
                email = input.nextLine();
                flag = true;
            } catch (DuplicateProfileException e) {
                System.out.println("Email is already in use, please enter a new one");
                email = input.nextLine();
                flag = true;
            }
        }

        // Input and confirm password
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

        // Ask for notification preference
        System.out.println("Would you like to turn on notifications?");
        int tempSelect;
        System.out.println("1. Yes");
        System.out.println("2. No");
        while (flag) {
            try {
                tempSelect = input.nextInt();
                switch (tempSelect) {
                    case 1:
                        toReturn.setNotification(true);
                        flag = false;
                        break;
                    case 2:
                        toReturn.setNotification(false);
                        flag = false;
                        break;
                    default:
                        throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option");
                input.nextLine();
            }

        }


        // Confirm profile information
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

        // Handle profile save or retry
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

    /**
     * Displays a menu for actions related to a specific profile.
     *
     * @param profile user profile entered in main interface
     */
    public static void mainMenu(Profile profile) {
        //TODO: add password section
        boolean loggedIn = Login(profile);
        if (loggedIn) {
            boolean flag = true;
            Scanner input = new Scanner(System.in);

            System.out.println("Login Sucessful!");

            if (profile.isNotification() && !profile.medList.isEmpty()) {
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                scheduler.scheduleAtFixedRate(() -> {
                    LocalTime now = LocalTime.now();
                    LocalDate today = LocalDate.now();
                    DayOfWeek currentDay = today.getDayOfWeek();

                    for (Med med : profile.medList) {
                        // Check if today is a scheduled day for the medication
                        if (med.getDaysOfWeek().contains(currentDay) && !med.isTaken()) {
                            // Check if the current time is within 1 minute of the scheduled medication time
                            LocalTime medTime = med.getTime();
                            if (now.isAfter(medTime.minusMinutes(1)) && now.isBefore(medTime.plusMinutes(1))) {
                                // Check if more than an hour has passed since the last notification
                                if (med.getLastNotification() == null || Duration.between(med.getLastNotification(), now).toHours() >= 1) {
                                    Toolkit.getDefaultToolkit().beep(); // Notify user
                                    System.out.println("Time to take your medication: " + med.getName());

                                    // Update the last notification time
                                    med.setLastNotification(LocalDateTime.from(now));
                                }
                            }
                        }
                    }
                }, 0, 5, TimeUnit.SECONDS); // Runs every 5 seconds
            }

            while (flag) {
                System.out.println("Welcome " + profile.getName() + "! Please select an option: \n\t1. View profile\n\t2. View medications\n\t3. Go Back");

                int option = input.nextInt();
                if (option == 1) {
                    System.out.println(profile.toStringMasked());

                    System.out.println("\nWould you like to see your information?\n\t1.Yes\n\t2.No");
                    option = input.nextInt();
                    while (option != 2) {
                        if (option == 1) {
                            loggedIn = Login(profile);
                            if (loggedIn) {
                                System.out.println(profile.toString());
                                break;
                            }
                        } else {
                            System.out.println("Please Select a valid option.");
                            option = input.nextInt();
                        }
                    }

                } else if (option == 2) {
                    medMenu(profile);
                    flag = false;
                } else if (option == 3) {
                    break;
                } else {
                    System.out.println("Please select a valid option");
                }

            }
        }
    }

    /**
     * Displays medication options for a profile and allows adding a new medication.
     *
     * @param profile user profile to manage medications
     */

    public static void medMenu(Profile profile) {
        int listNum = 1;
        System.out.println("Please select an option:");
        if (!profile.medList.isEmpty()) {
            for (Med s : profile.medList) {
                if (s.isTaken()) {
                    System.out.println("\t" + listNum++ + ". " + s.getName() + " (taken)");
                } else {
                    System.out.println("\t" + listNum++ + ". " + s.getName() + " (not taken)");
                }

            }
        }
        System.out.println("\t" + listNum + ". Add new medication");
        System.out.println("\t" + (listNum + 1) + ". Go Back");
        Scanner input = new Scanner(System.in);
        boolean flag = true;
        int selected = input.nextInt();
        if (selected != (listNum + 1)) {
            while (flag) {

                while (selected < 1 || selected > profile.medList.size() + 1) {
                    try {
                        selected = Integer.parseInt(input.nextLine());

                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number.");
                    }
                }

                if (selected == profile.medList.size() + 1) {
                    newMed(profile);
                    flag = false;
                } else if (selected <= profile.medList.size()) {
                    System.out.println(profile.medList.get(selected - 1).medDetails());
                    String subSelect = input.nextLine();
                    do {
                        if (subSelect.equals("1")) {
                            profile.medList.get(selected - 1).setTaken(!profile.medList.get(selected - 1).isTaken());

                            if(profile.medList.get(selected-1).isTaken()){
                                System.out.println("\nThank you for taking your medication!");
                            } else{
                                Duration duration = Duration.between(LocalTime.now(), profile.medList.get(selected-1).getTime());
                                long hours = duration.toHours();
                                long minutes = duration.toMinutes() % 60;
                                if(LocalTime.now().isBefore(profile.medList.get(selected-1).getTime()) && profile.medList.get(selected-1).getDaysOfWeek().contains(LocalDate.now().getDayOfWeek())){
                                    System.out.println("Time left: " + hours + "hour(s) " + minutes +" minute(s)");
                                } else if ((LocalTime.now().isAfter(profile.medList.get(selected-1).getTime()) || LocalTime.now().equals(profile.medList.get(selected-1).getTime()))
                                        && profile.medList.get(selected-1).getDaysOfWeek().contains(LocalDate.now().getDayOfWeek()) ) {
                                    System.out.println("Medication overdue! Was supposed to be taken at " + profile.medList.get(selected-1).getTime() + " - make sure to take your "
                                    + profile.medList.get(selected-1).getName());
                                }
                            }

                            System.out.println(profile.medList.get(selected - 1).medDetails());

                        }
                        subSelect = input.nextLine();

                    } while (!subSelect.equals("2"));
                    break;
                } else {
                    System.out.println("Please choose a valid option");
                }
            }
        }

    }

    /**
     * Prompts the user to add a new medication to their profile.
     *
     * @param profile the profile to add medication to
     */
    public static void newMed(Profile profile) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the medication name (generic or brand name):");

        String tempName = input.nextLine();

        Med newMed = new Med();

        // Input and validate medication name
        boolean flag = true;
        if (tempName.equals("test")) {
            profile.medList.add(new Med("test"));
        } else {
            while (flag) {
                try {

                    for (ValidMeds med : ValidMeds.values()) {
                        if (tempName.equalsIgnoreCase(med.toString())) {
                            newMed.setName(tempName);
                            flag = false;
                            break;
                        }

                    }
                    if (flag) {
                        throw new Exception();
                    }

                } catch (Exception e) {
                    System.out.println("Please enter a valid medication name");
                    tempName = input.nextLine();
                }
            }

            System.out.println("Please enter the medication dosage (including measurement i.e. mg, mL)");
            String tempDosage = input.nextLine();
            flag = true;
            while (flag) {
                try {
                    newMed.setDosage(tempDosage);
                    flag = false;
                } catch (FormattingException e) {
                    System.out.println("Please enter a valid dosage");
                    tempDosage = input.nextLine();
                }
            }


            flag = true;
            // Input and validate medication days
            ArrayList<DayOfWeek> tempDays = new ArrayList<>();
            boolean flag1 = true;
            while (flag1) {
                for (DayOfWeek d : DayOfWeek.values()) {
                    System.out.println("Would you like to take on " + d + "?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    while (flag) {
                        switch (input.nextInt()) {
                            case 1:
                                tempDays.add(d);
                                flag = false;
                                break;
                            case 2:
                                flag = false;
                                break;
                            default:
                                System.out.println("Please select a valid option");
                        }
                    }
                    flag = true;
                }
                if (tempDays.isEmpty()) {
                    System.out.println("Please select at least one day.");
                } else {
                    newMed.setDaysOfWeek(tempDays);
                    flag1 = false;
                }
            }
            //input new medication time
            System.out.println("Please enter a time in 24 hour format (00:00): ");
            LocalTime tempTime;
            while (flag) {
                try {
                    //Fixed extra error message caused by the time set code block
                    input.nextLine();
                    tempTime = LocalTime.parse(input.nextLine());
                    newMed.setTime(tempTime);
                    flag = false;
                } catch (Exception e) {
                    System.out.println("Please enter a valid time:");
                }
            }
            //add new medication to list
            profile.medList.add(newMed);
            System.out.println("Medication successfully added");
        }
    }

    public static boolean Login(Profile selected) {
        System.out.println("Please enter password or type 'exit' to go back:");
        Scanner input = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            String entered = input.nextLine();
            if (entered.equalsIgnoreCase("exit")) {
                flag = false;
                return false;
            }
            if (entered.equals(selected.getPassword())) {
                flag = false;
                return true;
            } else {
                System.out.println("Login attempt unsucesssful, please try again.");
            }
        }

        return false;
    }

}
