package com.inredec.ATutor_Backend.payload;

import java.time.Instant;
import java.util.Date;

public class UserProfile     {
        private Long id;
        private String username;
        private String name;
        private Date joinedAt;

        public UserProfile(Long id, String username, String name, Date joinedAt) {
            this.id = id;
            this.username = username;
            this.name = name;
            this.joinedAt = joinedAt;

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getJoinedAt() {
            return joinedAt;
        }

        public void setJoinedAt(Date joinedAt) {
            this.joinedAt = joinedAt;
        }


}