package com.narzullaev.TelegramBot.repositories;


import com.narzullaev.TelegramBot.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
