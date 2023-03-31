package com.piebin.pieweb.domain;

import lombok.*;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user")
public class Account {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @NonNull
    @Column(unique = true)
    private String id;

    @NonNull
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String number;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime signup_date;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Post> posts = new HashSet<>();

    public void addPost(Post post) {
        posts.add(post);
        post.setAccount(this);
    }

    public Account(@NonNull String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Account() {}
}
