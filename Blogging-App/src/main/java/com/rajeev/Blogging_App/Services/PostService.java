package com.rajeev.Blogging_App.Services;

import com.rajeev.Blogging_App.Payloads.PostDTO;
import com.rajeev.Blogging_App.Payloads.PostResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface PostService {

    PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId, MultipartFile image);

    PostDTO updatePost(PostDTO postDto, Integer postId);

    void deletePost(Integer postId);

    PostDTO getPostById(Integer postId);

    PostResponse getAllPosts(Integer pageNumber , Integer pageSize , String sortBy , String order);

    PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber , Integer pageSize , String sortBy , String order);

    PostResponse getAllPostByUser(Integer userId, Integer pageNumber , Integer pageSize , String sortBy , String order);

    List<PostDTO> searchPostsByTitle(String keyword);
}
