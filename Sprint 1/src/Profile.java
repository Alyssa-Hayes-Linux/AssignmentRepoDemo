import java.io.IOException;
public class Profile {

    //base variables
    private String name;
    private String dateOfBirth;
    private String email;
    private String phone;
    private String pronoun;
    private String password;
    private boolean notification;

    //base obj
    public Profile {
        this.name = null;
        this.dateOfBirth = null;
        this.email = null;
        this.phone = null;
        this.pronoun = null;
        this.password = null;
        this.notification = false;

    }

    //obj to be initialized
    public Profile(String name, String dateOfBirth, String email, String phone, String pronoun,
                   String password, boolean notification) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.pronoun = pronoun;
        this.password = password;
        this.notification = notification;


    }

    //getters and setters

    public String getName() {
        try {
            return name;
        }catch (NullPointerException e){
            System.out.println("please enter a valid field");
        }catch (InputMismatchException e){
            System.out.println("please enter a valid field");

        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        try {
            return dateOfBirth;
        } catch (NullPointerException e) {
            System.out.println("please enter a valid field");

        } catch (InputMismatchException e) {
            System.out.println("please enter a valid field");

        }
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
       try {
           return email;
       }catch (NullPointerException e){
           System.out.println("please enter a valid field");

       }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
       try{ return phone;
       }catch (NullPointerException e){
           System.out.println("please enter a valid field");

       }catch (InputMismatchException e){
           System.out.println("please enter a valid field");

       }
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPronoun() {
        try{ return pronoun;
        }catch (NullPointerException e){
            System.out.println("please enter a valid field");

        }catch (InputMismatchException e){
            System.out.println("please enter a valid field");

        }
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public String getPassword() {
       try {
           return password;
       }catch (NullPointerException e){
           System.out.println("please enter a valid field");

       }
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

    //masks
    /**
     * needed for:
     *  DOB
     *  email
     *  phone
     *  password
     */

    public String toMask(String aString){
        return "X".repeat(aString.length());
    }

    //Profile display no masks
    private void getProfile() {
    }
    //Profile display with masks
    private void getProfileMasked() {
    }

    /**
     * private String name;
     *     private String dateOfBirth;
     *     private String email;
     *     private String phone;
     *     private String pronoun;
     *     private String password;
     *     private boolean notification;
     */
    public void toString(){
        System.out.println("User Profile:" +
                "\nName:" + name +
                "\nDate of Birth:" + toMask(dateOfBirth) +
                "\nEmail: " + email +
                "\nPhone: " + toMask(phone) +
                "\nPronouns: " + pronoun +
                "\nPassword: " + password + );
    }
}