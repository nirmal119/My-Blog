package com.project.blog.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

//    @NotBlank(message="is required")
    @Column(name="title")
    private String title;

//    @NotBlank(message="is required")
    @Column(name="excerpt")
    private String excerpt;

//    @NotBlank(message="is required")
    @Column(name="content")
    private String content;

//    @NotBlank(message="is required")
    @Column(name="author")
    private String author;

//    @NotBlank(message="is required")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="published_at")
    @CreationTimestamp
    private Date publishedAt;

//    @NotBlank(message="is required")
    @Column(name="is_published")
    private boolean isPublished;

//    @NotBlank(message="is required")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    @CreationTimestamp
    private Date createdAt;

//    @NotBlank(message="is required")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    @UpdateTimestamp
    private Date updatedAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", publishedAt=" + publishedAt +
                ", isPublished=" + isPublished +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
