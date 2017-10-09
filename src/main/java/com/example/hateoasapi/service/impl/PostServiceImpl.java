package com.example.hateoasapi.service.impl;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.example.hateoasapi.domain.Post;
import com.example.hateoasapi.repository.PostRepository;
import com.example.hateoasapi.service.PostService;
import com.example.hateoasapi.webform.PostQuery;

@Service
public class PostServiceImpl implements PostService {

    private MongoTemplate mongoTemplate;
    private PostRepository postRepository;

    public PostServiceImpl(
        MongoTemplate mongoTemplate,
        PostRepository postRepository
    ) {
        this.mongoTemplate = mongoTemplate;
        this.postRepository = postRepository;
    }

    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }
    
    public void save(Post post) {
        postRepository.save(post);
    }
    
    public void delete(Post post) {
        postRepository.delete(post);
    }
    
    public void deleteById(String id) {
        postRepository.deleteById(id);
    }

    public List<Post> queryAllPosts(PostQuery postQuery, int pageSize) {
        int page = 0;
        if(postQuery.getPage() != null)
            page = postQuery.getPage();

        Criteria criteria = where("_id").ne(null);
        if(postQuery.getTitle() != null)
            criteria.and("title").is(postQuery.getTitle());
        
        if(postQuery.getCategoryId() != null)
            criteria.and("categoryId").is(postQuery.getCategoryId());

        if(postQuery.getTags() != null)
            criteria.and("tags").elemMatch(where("tag").in(postQuery.getTags()));
        
        return mongoTemplate.find(
                    query(criteria).with(PageRequest.of(page, pageSize)),
                    Post.class
                    );
    }
}