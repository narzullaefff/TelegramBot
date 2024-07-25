package com.narzullaev.TelegramBot.entities.user;


import com.narzullaev.TelegramBot.entities.Action;
import com.narzullaev.TelegramBot.entities.Role;
import com.narzullaev.TelegramBot.entities.UserDetails;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @Column(name = "id")
    Long chatId;

    @Enumerated(EnumType.STRING)
    Role role;

    @Enumerated(EnumType.STRING)
    Action action;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_details_id")
    UserDetails details;
}
