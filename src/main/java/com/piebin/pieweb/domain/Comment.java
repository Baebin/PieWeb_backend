package com.piebin.pieweb.domain;

import com.piebin.pieweb.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String comment;

    @CreatedDate
    private LocalDateTime created_date;
    @LastModifiedDate
    private LocalDateTime motified_date;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Account account;

    public Comment(CommentRequestDto dto) {
        this.comment = dto.getComment();
    }
}