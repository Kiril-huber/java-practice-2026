package ru.itis.Shop.user.api.dto;

public class UserDto {

    private Integer id;

    private String firstName;
    private String lastName;
    private Integer age;

    private String email;

    private String profileDescription;

    public UserDto(Integer id, String firstName, String lastName, Integer age, String email, String profileDescription) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.profileDescription = profileDescription;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                '}';
    }
}
