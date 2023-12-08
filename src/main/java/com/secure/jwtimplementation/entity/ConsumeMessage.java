package com.secure.jwtimplementation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CONSUME_MESSAGE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsumeMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MSG_ID")
    private Long msgId;
    @Column(name = "MESSAGE")
    private String message;
}
