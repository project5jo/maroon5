package com.spring.mood.projectmvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Integer topicId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "topic_content", length = 255)
    private String topicContent;

    @OneToMany(mappedBy = "topic")
    @JsonIgnore
    private List<ChatRoom> chatRooms;
}