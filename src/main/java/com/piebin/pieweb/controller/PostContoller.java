package com.piebin.pieweb.controller;

import com.piebin.pieweb.domain.Post;
import com.piebin.pieweb.dto.PostSaveRequestDto;
import com.piebin.pieweb.jwt.UserDetail;
import com.piebin.pieweb.repository.PostRepository;
import com.piebin.pieweb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostContoller {
    private static Logger logger = LoggerFactory.getLogger(PostContoller.class);

    private final PostService postService;
    private final PostRepository postRepository;

    @PostMapping("/api/post/save")
    public ResponseEntity save(
            @RequestBody PostSaveRequestDto dto,
            @AuthenticationPrincipal UserDetail userDetail) {
        logger.info(
            "Title: " + dto.getTitle() + "\n"
            + "Description: " + dto.getDescription() + "\n"
            + "UserDetail: " + userDetail.getAccount().getName());
        Post post = postService.save(dto, userDetail.getAccount());
        if (post == null)
            throw new IllegalArgumentException("에러가 발생하였습니다");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/posts")
    public Page<Post> getPosts(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Post> posts = postRepository.findAllByOrderByIdxDesc(pageable);
        return posts;
    }
}
