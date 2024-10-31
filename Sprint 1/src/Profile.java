

package src;


public class Profile {

    //base variables
    private String name;
    private String DOB;
    private String email;
    private String phone;
    private String pronoun;
    private String password;
    private boolean notification;

    //base obj
    public Profile (){
    }

    //obj to be initialized
    public Profile(String name, String DOB, String email, String phone, String pronoun,
                   String password, boolean notification) {
        setName(name);
        setDOB(DOB);
        setEmail(email);
        setPhone(phone);
        setPronoun(pronoun);
        setPassword(password);
        setNotification(notification);

    }

    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) throws FormatException {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB)throws FormatException {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)throws FormatException {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPronoun() {
        return pronoun;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)throws FormatException {
        this.password = password;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public String toMask(String aString){return mask}

    //Profile display no masks
    private void getProfile() {
    }
    //Profile display with masks
    private void getProfileMasked() {
    }



    //masks
    /**
     * needed for:
     *  DOB
     *  email
     *  phone
     *  password
     */

    /**
     * toString for output
     */
}