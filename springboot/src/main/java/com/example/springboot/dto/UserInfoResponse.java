package com.example.springboot.dto;

import java.util.Date;
import java.util.List;

public class UserInfoResponse {
    private Long id;
    private String username;
    private String phoneNumber;
    private Date birthDate;
    private List<String> hobbies;
    private String gender;
    private String location;
    private Date registrationTime;

    // Getter 和 Setter
    // ...（省略）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // 其他字段的 Getter 和 Setter
    // ...

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }
}
