package com.narzullaev.TelegramBot.entities;


import com.narzullaev.TelegramBot.entities.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_details")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    UUID id;
    
    
    @Column(name = "user_name")
    String username;
    
    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "registred_at")
    LocalDateTime  registeredAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
}
