package com.rajeev.Blogging_App.Services.Impl;

import com.rajeev.Blogging_App.Exception.ResourceNotFoundException;
import com.rajeev.Blogging_App.Model.Category;
import com.rajeev.Blogging_App.Model.Post;
import com.rajeev.Blogging_App.Model.User;
import com.rajeev.Blogging_App.Payloads.PostDTO;
import com.rajeev.Blogging_App.Payloads.PostResponse;
import com.rajeev.Blogging_App.Repository.CategoryRepo;
import com.rajeev.Blogging_App.Repository.PostRepository;
import com.rajeev.Blogging_App.Repository.UserRepo;
import com.rajeev.Blogging_App.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private ModelMapper mod;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId, MultipartFile image) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user" ,"user ID" , userId));
        Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category" , "Category Id", categoryId));

//        Post p = mod.map(postDto,Post.class);
//        p.setAddedDate(new Date());
//        p.setImageName(image.getOriginalFilename());
//        p.setUser(user);
//        p.setCategory(cat);
//        Post savedPost = postRepo.save(p);
//        mod.map(p, PostDTO.class);
//        return mod.map(savedPost, PostDTO.class);

        Post p = new Post();
        p.setCategory(cat);
        p.setUser(user);
        p.setTitle(postDto.getTitle());
        p.setContent(postDto.getContent());
        p.setImageName(postDto.getImageName());
        p.setAddedDate(new Date());

        Post savedPost = postRepo.save(p);
        return mod.map(savedPost,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() ->new ResourceNotFoundException("Post" , "Id"  , postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddedDate(postDto.getAddedDate());

        Post p =postRepo.save(post);
        return mod.map( p , PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post =  postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post" , "PostId" , postId));
        postRepo.delete(post);
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Post" , "PostId" , postId));
        return  mod.map(post , PostDTO.class);
    }


    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy,String order) {

        Sort sort = (order.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() :  Sort.by(sortBy).descending();

        Pageable p = PageRequest.of( pageNumber , pageSize , sort);
        Page<Post> pagePost = postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();

        List<PostDTO> posts =  allPosts.stream().map( post -> mod.map(post , PostDTO.class)).toList();

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(posts);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber , Integer pageSize , String sortBy , String order) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category" ,"CategoryId" ,categoryId));

        Sort sort = order.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber ,pageSize , sort);
        Page<Post> pagePosts = postRepo.findByCategory(category , p);
        List<PostDTO> allPosts = pagePosts.stream().map(post -> mod.map(post , PostDTO.class)).toList();

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(allPosts);
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setLastPage(pagePosts.isLast());

       return postResponse;
    }

    @Override
    public PostResponse getAllPostByUser(Integer userId, Integer pageNumber , Integer pageSize , String sortBy , String order){

        Sort sort = (order.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() :  Sort.by(sortBy).descending();

        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" ,"UserId" ,userId));
        Pageable p = PageRequest.of(pageNumber ,  pageSize , sort);
        Page<Post> pagePost = postRepo.findByUser(user , p);

        List<PostDTO> posts = pagePost.stream().map(post -> mod.map(post , PostDTO.class)).toList();

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(posts);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public List<PostDTO> searchPostsByTitle(String keywords) {
        List<Post> posts = postRepo.searchByTitle(keywords);
        return posts.stream().map( (post) -> mod.map(post , PostDTO.class)).toList();
    }

}
