package com.example.hateoasapi.service;

import java.util.List;
import java.util.Optional;

import com.example.hateoasapi.domain.Post;
import com.example.hateoasapi.model.PostQuery;

public interface PostService {

    public Optional<Post> findById(String id);

    public void save(Post post);

    public void delete(Post post);

    public void deleteById(String id);

    public List<Post> queryAllPosts(PostQuery postQuery, int pageSize);
}