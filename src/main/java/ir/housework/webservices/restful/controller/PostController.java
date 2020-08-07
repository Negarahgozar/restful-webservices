package ir.housework.webservices.restful.controller;

import ir.housework.webservices.restful.exception.PostNotFoundException;
import ir.housework.webservices.restful.exception.UserNotFoundException;
import ir.housework.webservices.restful.models.Post;
import ir.housework.webservices.restful.models.User;
import ir.housework.webservices.restful.service.PostDaoService;
import ir.housework.webservices.restful.service.UserDaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private PostDaoService postDaoService;
    private UserDaoService userDaoService;

    public PostController(PostDaoService postDaoService, UserDaoService userDaoService) {
        this.postDaoService = postDaoService;
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<Object> retrieveAllPostsForSpecialUser(@PathVariable Integer userId){
        Optional<User> user = userDaoService.findOne(userId);
        if (!user.isPresent())
            throw new UserNotFoundException("id-" + userId);
        List<Post> posts = postDaoService.findAllForSpecialUser(user.get());
        if (posts.size()>0)
            return new ResponseEntity(posts, HttpStatus.OK);
        else
            return new ResponseEntity("This user_id does not have any post", HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public Post retrievePostForSpecialUser(@PathVariable Integer userId, @PathVariable Integer postId){
        Optional<User> user = userDaoService.findOne(userId);
        if (!user.isPresent())
            throw new UserNotFoundException("id-" + userId);
        Optional<Post> post = postDaoService.findOne(user.get(), postId);
        if (!post.isPresent())
            throw  new PostNotFoundException("post-" + postId);
        return post.get();
    }

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer userId, @RequestBody Post post){
        Optional<User> user = userDaoService.findOne(userId);
        if (!user.isPresent())
            throw new UserNotFoundException("id-" + userId);
        postDaoService.save(user.get(), post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{postId}").buildAndExpand(post.getPostId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
