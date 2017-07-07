package com.example.rayan.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Nabeel on 4/15/2017.
 */
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "email", unique=true, nullable=false)
    private String email;
    @Column(name = "username", unique=true, nullable=false)
    private String username;
    @Column(name = "password" , nullable = false)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "enable")
    private Boolean enable;
    @Column(name = "gender")
    private Boolean gender;
    @JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
    @OneToOne
    private DoctorType doctorType;

    public Doctor() {
        super();
    }

    public Doctor(Long id, String email, String username, String password, String firstName, String lastName, boolean enable,boolean gender, Set<Role> roles, DoctorType doctorType) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enable = enable;
        this.gender = gender;
        this.roles = roles;
        this.doctorType = doctorType;
    }

    public Doctor(Doctor doctor){
        this.id = doctor.getId();
        this.email = doctor.getEmail();
        this.username = doctor.getUsername();
        this.password = doctor.getPassword();
        this.firstName = doctor.getFirstName();
        this.lastName = doctor.getLastName();
        this.enable = doctor.getEnable();
        this.roles = doctor.getRoles();
        this.doctorType = doctor.getDoctorType();
    }



    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getGender() { return gender; }

    public void setGender(Boolean gender) { this.gender = gender; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }

    public DoctorType getDoctorType() { return doctorType; }

    public void setDoctorType(DoctorType doctorType) { this.doctorType = doctorType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doctor doctor = (Doctor) o;

        if (enable != doctor.enable) return false;
        if (!id.equals(doctor.id)) return false;
        if (!email.equals(doctor.email)) return false;
        if (!username.equals(doctor.username)) return false;
        if (!password.equals(doctor.password)) return false;
        if (firstName != null ? !firstName.equals(doctor.firstName) : doctor.firstName != null) return false;
        if (lastName != null ? !lastName.equals(doctor.lastName) : doctor.lastName != null) return false;
        if (!roles.equals(doctor.roles)) return false;
        return doctorType.equals(doctor.doctorType);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        result = 31 * result + roles.hashCode();
        result = 31 * result + doctorType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", passWord='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", enable=" + enable +
                ", roles=" + roles +
                ", doctorType=" + doctorType +
                '}';
    }
}
