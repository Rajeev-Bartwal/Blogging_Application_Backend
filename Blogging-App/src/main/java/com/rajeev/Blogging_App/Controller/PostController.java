package com.rajeev.Blogging_App.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajeev.Blogging_App.Configuration.AppConstants;
import com.rajeev.Blogging_App.Payloads.ApiResponse;
import com.rajeev.Blogging_App.Payloads.PostDTO;
import com.rajeev.Blogging_App.Payloads.PostResponse;
import com.rajeev.Blogging_App.Services.ImageService;
import com.rajeev.Blogging_App.Services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ImageService imageService;

    @Value("${project.image}")
    private  String path;

    @Autowired
    private ModelMapper mod;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestParam(value = "post",required = true) String post,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId,
                                              @RequestParam("image") MultipartFile image) throws IOException {

       String imageName = imageService.uploadImage(path,image);
        ObjectMapper obj = new ObjectMapper();
        PostDTO postDto = obj.readValue( post,PostDTO.class);
        System.out.println(postDto.getContent());
        System.out.println(postDto.getTitle());
        postDto.setImageName(imageName);

       PostDTO savedPost =  postService.createPost(postDto,userId,categoryId,image);
       return  new ResponseEntity<>(savedPost , HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getAllPostsByUser(
            @PathVariable Integer userId,
            @RequestParam(value = "pageNumber" ,defaultValue = AppConstants.PAGE_NO , required = false) Integer pageNumber,
            @RequestParam(value = "pageSize" ,defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize,
            @RequestParam(value = "sortBy" ,defaultValue = AppConstants.SORT_BY , required = false) String sortBy,
            @RequestParam(value = "order",defaultValue = AppConstants.ORDER , required = false) String order)
    {

        return new ResponseEntity<>(postService.getAllPostByUser(userId, pageNumber ,  pageSize , sortBy , order) , HttpStatus.FOUND);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getAllPostsByCategories(
            @PathVariable Integer categoryId,
            @RequestParam(value = "pageNumber" ,defaultValue = AppConstants.PAGE_NO , required = false) Integer pageNumber,
            @RequestParam(value = "pageSize" ,defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize,
            @RequestParam(value = "sortBy" ,defaultValue = AppConstants.SORT_BY , required = false) String sortBy,
            @RequestParam(value = "order",defaultValue = AppConstants.ORDER , required = false) String order){

        return new ResponseEntity<>(postService.getAllPostByCategory(categoryId,pageNumber,pageSize,sortBy,order) , HttpStatus.FOUND);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber" ,defaultValue = AppConstants.PAGE_NO , required = false) Integer pageNumber,
            @RequestParam(value = "pageSize" ,defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize,
            @RequestParam(value = "sortBy" ,defaultValue = AppConstants.SORT_BY , required = false) String sortBy,
            @RequestParam(value = "order",defaultValue = AppConstants.ORDER , required = false) String order){

        return new ResponseEntity<PostResponse>((postService.getAllPosts( pageNumber ,  pageSize , sortBy , order)), HttpStatus.FOUND);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
        return new ResponseEntity<>(postService.getPostById(postId),HttpStatus.FOUND);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post Deleted SuccessFully ", true),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDto,
                                              @PathVariable Integer postId){

             return new ResponseEntity<>( postService.updatePost(postDto , postId) , HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPost(@PathVariable String keywords){

        return new ResponseEntity<>(postService.searchPostsByTitle(keywords) , HttpStatus.FOUND);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadImage(@RequestParam(value ="image") MultipartFile image,
                                                     @PathVariable Integer postId) throws IOException {
        String imageName = imageService.uploadImage(path,image);

        PostDTO post = postService.getPostById(postId);
        post.setImageName(imageName);
        postService.updatePost(post , postId);
        return new ResponseEntity<>(postService.updatePost(post , postId) , HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> serveImage(@PathVariable String imageName,
                                        HttpServletResponse response) throws IOException {
        InputStream image = imageService.getImage(path , imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(image,response.getOutputStream());
        return ResponseEntity.ok(image);
    }
}
