package com.mitit.repository;

import com.mitit.domain.chat.Chat;
import com.mitit.domain.chat.State;
import com.mitit.domain.chat.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ChatRepo extends JpaRepository<Chat, Long> {
    @Transactional
    @Modifying
    @Query("update Chat c set c.lastMessageDateTime = ?1 where c.chatId = ?2")
    int updateLastMessageDateTimeByChatId(LocalDateTime lastMessageDateTime, Long chatId);
    @Transactional
    @Modifying
    @Query("update Chat c set c.state = ?1 where c.chatId = ?2")
    int updateStateByChatId(State state, Long chatId);
    @Transactional
    @Modifying
    @Query("update Chat c set c.status = ?1 where c.chatId = ?2")
    int updateStatusByChatId(Status status, Long chatId);
    Optional<Chat> findByChatId(Long chatId);
}
