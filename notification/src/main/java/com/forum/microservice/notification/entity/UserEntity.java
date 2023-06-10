package com.forum.microservice.notification.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "first_name")
  @NotNull
  @NotBlank(message = "First name is mandatory")
  private String firstName;

  @Column(name = "last_name")
  @NotNull
  @NotBlank(message = "Last name is mandatory")
  private String lastName;

  @Column(name = "email")
  @Email(message = "Entered email is not valid.")
  private String email;

  @Column(name = "password")
  @Size(message = "Password should contain at least 5 characters", min = 5)
  private String password;

  @OneToMany(
      mappedBy = "receiver",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JsonIgnore
  private List<NotificationEntity> notifications;

  public UserEntity() {}

  public UserEntity(String firstName, String lastName, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public List<NotificationEntity> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<NotificationEntity> notifications) {
    this.notifications = notifications;
  }

  @Override
  public String toString() {
    return "User{"
        + "firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", email='"
        + email
        + '\''
        + '}';
  }
}
