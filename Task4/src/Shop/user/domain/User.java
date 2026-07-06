package Shop.user.domain;

public class User {

    private String id;

    private String firstName;
    private String lastName;
    private int age;

    private String email;
    private String password;

    private String profileDescription;


    public User(String id, String firstName, String email, String password, String profileDescription) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
    }

    public User(String id,String firstName, String lastName,int age){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(String email, String password, String profileDescription) {
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }
}
