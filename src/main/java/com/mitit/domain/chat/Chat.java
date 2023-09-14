package com.mitit.domain.chat;

import com.mitit.domain.Subcategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "chats")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Chat {
    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private State state;

    @Column(name = "last_message_datetime")
    private LocalDateTime lastMessageDateTime;

    @ManyToMany
    @JoinTable(
            name = "chats_subcategories",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "subcategory_id")
    )
    private List<Subcategory> subcategories = new ArrayList<>();

    public static class Builder {
        private final Long chatId;
        private String username;
        private String firstName;
        private String lastName;
        private Status status;
        private State state;
        private LocalDateTime lastMessageDateTime;
        private List<Subcategory> subcategories = new ArrayList<>();

        public Builder(Long chatId) {
            this.chatId = chatId;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder state(State state) {
            this.state = state;
            return this;
        }

        public Builder lastMessageDateTime(LocalDateTime lastMessageDateTime) {
            this.lastMessageDateTime = lastMessageDateTime;
            return this;
        }

        public Builder subcategories(List<Subcategory> subcategories) {
            this.subcategories = subcategories;
            return this;
        }

        public Chat build() {
            return new Chat(chatId, username, firstName, lastName,
                    status, state, lastMessageDateTime, subcategories);
        }
    }
}
