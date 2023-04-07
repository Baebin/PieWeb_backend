package com.piebin.pieweb.service;

import com.piebin.pieweb.domain.Account;
import com.piebin.pieweb.domain.Post;
import com.piebin.pieweb.dto.PostSaveRequestDto;
import com.piebin.pieweb.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private static Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    PostRepository repository;

    public Post save(PostSaveRequestDto dto, Account account) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        account.addPost(post);

        logger.info(dto.getTitle() + " " + dto.getDescription());
        logger.info(account.getId());
        return repository.save(
                post
        );
    }
}