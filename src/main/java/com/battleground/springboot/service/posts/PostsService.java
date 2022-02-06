package com.battleground.springboot.service.posts;

import com.battleground.springboot.domain.posts.Posts;
import com.battleground.springboot.web.dto.PostsResponseDto;
import com.battleground.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.battleground.springboot.domain.posts.PostsRepository;
import com.battleground.springboot.web.dto.PostsSaveRequestDto;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 업슴 id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }


    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 업슴 id=" + id));

        return new PostsResponseDto(entity);
    }
}