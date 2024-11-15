import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.regex.*;

    public class Profile {

        // Static variable to store used emails


        // Base variables
        private String name;
        private String dateOfBirth; // Format: MM/DD/YYYY
        private String email;
        private String phone; // Format: (XXX)XXX-XXXX
        private String pronoun;
        private String password;
        private boolean notification;
        ArrayList<Med> medList = new ArrayList<>();

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
        public Profile(String string) {
            this.name = "Ben Herbst";
            this.dateOfBirth = "08/25/2005";
            this.email = "herbstb@vcu.edu";
            this.phone = "(703)869-2363";
            this.pronoun = "he/him";
            this.password = "";
            this.notification = false;

        }

        // Setters with validation

        //DO NOT CHANGE
        public void setName(String name) throws FormatingException {
           if (name == null || name.trim().isEmpty() || !name.contains(" ") || name.indexOf(" ") == 0 || name.indexOf(" ") == name.length()-1) {
                throw new FormatingException("Invalid name format.");
            }
            this.name = name;
        }

        public String getName() {
            return name;
        }


        public void setDateOfBirth(String dateOfBirth) throws FormatingException {
            // Simple regex for MM/DD/YYYY format
            if (!Pattern.matches("(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})", dateOfBirth)) {
                throw new FormatingException("Invalid date of birth format. Use MM/DD/YYYY.");
            }
            this.dateOfBirth = dateOfBirth;
        }

        public void setEmail(String email) throws FormatingException, DuplicateProfileException {
            String[] domains = {"@gmail.com", "@aol", "@yahoo.com", "@outlook.com", "@vcu.edu"};
            if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) {
                throw new FormatingException("Invalid email format.");
            }
            boolean flag = false;
            for(String s : domains) {
                if (email.endsWith(s)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) throw new FormatingException();
            this.email = email;
        }

        public void setPhone(String phone) throws FormatingException {
            // Regex for phone number format (XXX)XXX-XXXX
            if (!Pattern.matches("\\(\\d{3}\\)\\d{3}-\\d{4}", phone)) {
                throw new FormatingException("Invalid phone number format. Use (XXX)XXX-XXXX.");
            }
            this.phone = phone;
        }

        public void setPronoun(String pronoun) throws FormatingException {
            // Regex for pronoun format (A-z)/(A-z)
            if (!Pattern.matches("^[A-Za-z]+/[A-Za-z]+$", pronoun) || !pronoun.contains("/")) {
                throw new FormatingException("Invalid pronoun format");
            }
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
                if((int)c <48 || (int)c > 90) {
                    s.append(c);
                }
                else s.append("X");
            }
            return s.toString();
        }

        public String toMaskPassword(String password) {
            StringBuilder s = new StringBuilder();
            for(char c : password.toCharArray()) {
                s.append("X");
            }
            return s.toString();
        }

        public String toMaskEmail(String email){
            StringBuilder s = new StringBuilder();
            for(char c : email.toCharArray()) {
                if(c == '@') break;
                s.append('X');
            }
           return String.valueOf(s.append(email.substring(email.indexOf("@"))));
        }

        // Profile display with masks
        public String toStringMasked() {
            return("User Profile (password not entered):" +
                    "\nName: " + name +
                    "\nDate of Birth: " + toMask(dateOfBirth) +
                    "\nEmail: " + toMaskEmail(email) +
                    "\nPhone: " + toMask(phone) +
                    "\nPronouns: " + pronoun +
                    "\nPassword: " + toMaskPassword(password));
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
//        public void main(){
//            Public("Alyssa Hayes", "04/30/2005", "hayesae9@vcu.edu", "888-555-5555", "she/her",
//                    "blablabla", true)
//            Public("Alyssa Hayes", "04/30/2005", "hayesae9@vcu.edu", "888-555-5555", "she/her",
//                    "blablabla", true)
//            Public("SpongeBob Squarepants", "May/5/1999", "bob99@vcu.edu", "888-555-5555", "he/him",
//                    "krabbypatty", false)
//            Public("Alyssa Hayes", "04/30/2005", "hayesae9@vcu.edu", "she/her",
//                    "blablabla", true)
//        }
    }
