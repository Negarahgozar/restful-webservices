package ir.housework.webservices.restful.service;

import ir.housework.webservices.restful.models.Post;
import ir.housework.webservices.restful.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostDaoService {

    private static int postCount = 6;

    public List<Post> findAllForSpecialUser(User user) {
        List<Post> posts = user.getPosts();
        return posts;
    }

    public Optional<Post> findOne(User user, int postId){
        List<Post> posts = user.getPosts();
        for (Post p: posts){
            if (p.getPostId().equals(postId))
                return Optional.ofNullable(p);
        }
        return Optional.ofNullable(null);
    }

    public Post save(User user, Post post){
        if (post.getPostId() == null)
            post.setPostId(++postCount);
        user.addPost(post);
        return post;
    }
}
