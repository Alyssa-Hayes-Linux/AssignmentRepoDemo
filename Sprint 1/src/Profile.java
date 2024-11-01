import java.io.IOException;
import java.io.*;
import java.util.HashSet;
import java.util.regex.Pattern;

    public class Profile {

        // Static variable to store used emails
        private static HashSet<String> emailTree = new HashSet<>();

        // Base variables
        private String name;
        private String dateOfBirth; // Format: MM/DD/YYYY
        private String email;
        private String phone; // Format: (XXX)XXX-XXXX
        private String pronoun;
        private String password;
        private boolean notification;

        // Default constructor
        public Profile() {
            this.name = "Not on file";
            this.dateOfBirth = "Not on file";
            this.email = "Not on file";
            this.phone = "Not on file";
            this.pronoun = "Not on file";
            this.password = "";
            this.notification = false;

        }

        // Constructor to initialize fields with validation
        public Profile(String name, String dateOfBirth, String email, String phone, String pronoun,
                       String password, boolean notification) throws FormatingException, DuplicateProfileException {
            setName(name);
            setDateOfBirth(dateOfBirth);
            setEmail(email);
            setPhone(phone);
            this.pronoun = pronoun;
            setPassword(password);
            this.notification = notification;
        }

        // Setters with validation
        public void setName(String name) throws FormatingException {
            if (name == null || name.trim().isEmpty()) {
                throw new FormatingException("Invalid name format.");
            }
            this.name = name;
        }

        public void setDateOfBirth(String dateOfBirth) throws FormatingException {
            // Simple regex for MM/DD/YYYY format
            if (!Pattern.matches("(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})", dateOfBirth)) {
                throw new FormatingException("Invalid date of birth format. Use MM/DD/YYYY.");
            }
            this.dateOfBirth = dateOfBirth;
        }

        public void setEmail(String email) throws FormatingException, DuplicateProfileException {
            if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) {
                throw new FormatingException("Invalid email format.");
            }
            if (emailTree.contains(email)) {
                throw new DuplicateProfileException("Email is already in use.");
            }
            this.email = email;
            emailTree.add(email); // Add email to the set
        }

        public void setPhone(String phone) throws FormatingException {
            // Regex for phone number format (XXX)XXX-XXXX
            if (!Pattern.matches("\\(\\d{3}\\)\\d{3}-\\d{4}", phone)) {
                throw new FormatingException("Invalid phone number format. Use (XXX)XXX-XXXX.");
            }
            this.phone = phone;
        }

        public void setPronoun(String pronoun) {
            this.pronoun = pronoun;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isNotification() {
            return notification;
        }

        public void setNotification(boolean notification) {
            this.notification = notification;
        }

        // Masks sensitive information
        public String toMask(String aString) {
            StringBuilder s = new StringBuilder();
            for(char c : aString.toCharArray()) {
                s.append("X");
            }
            return s.toString();
        }

        // Profile display with masks
        public void getProfileMasked() {
            System.out.println("User Profile (Masked):" +
                    "\nName: " + toMask(name) +
                    "\nDate of Birth: " + toMask(dateOfBirth) +
                    "\nEmail: " + toMask(email) +
                    "\nPhone: " + toMask(phone) +
                    "\nPronouns: " + toMask(pronoun) +
                    "\nPassword: " + toMask(password));
        }

        // Override toString method
        @Override
        public String toString() {
            return "User Profile:" +
                    "\nName: " + name +
                    "\nDate of Birth: " + dateOfBirth +
                    "\nEmail: " + email +
                    "\nPhone: " + phone +
                    "\nPronouns: " + pronoun +
                    "\nPassword: " + password;
        }
    }
