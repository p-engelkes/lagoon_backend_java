package com.pengelkes.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by pengelkes on 25.10.2016.
 */
@Entity
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    private String content;

    @ManyToOne
    @JsonIgnore
    private Photo photo;

    private Long photoId;
    private String username;

    public Long getCommentId()
    {
        return commentId;
    }

    public void setCommentId(Long commentId)
    {
        this.commentId = commentId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Photo getPhoto()
    {
        return photo;
    }

    public void setPhoto(Photo photo)
    {
        this.photo = photo;
    }

    public Long getPhotoId()
    {
        return photoId;
    }

    public void setPhotoId(Long photoId)
    {
        this.photoId = photoId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
