package com.narzullaev.TelegramBot.repositories;


import com.narzullaev.TelegramBot.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DetailsRepository extends JpaRepository<UserDetails, UUID> {
}
