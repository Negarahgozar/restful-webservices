package ir.housework.rest.webserives.restfulwebserives.models;

import javax.jnlp.IntegrationService;
import java.util.Date;

public class Post {

    private Integer postId;
    private String title;
    private Date Date;
    private String description;

    public Post(Integer postId, String title, Date date, String description) {
        this.postId = postId;
        this.title = title;
        Date = date;
        this.description = description;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
