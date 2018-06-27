package com.inredec.ATutor_Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lessons")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Lesson implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 150)
    private String urlImage;

    @NotBlank
    @Size(max = 10)
    private String level;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "subheader_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Subheader subheader;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = true)
    private Test test;

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

    public Subheader getSubheader() {
        return subheader;
    }

    public void setSubheader(Subheader subheader) {
        this.subheader = subheader;
    }

    public Lesson(Long id, String name, String urlimage, String level) {
        this.id = id;
        this.name = name;
        this.urlImage = urlimage;
        this.level = level;
    }

    public Lesson(){}

    public Lesson(@NotBlank @Size(max = 100) String name, @NotBlank @Size(max = 150) String urlImage, @NotBlank @Size(max = 10) String level, Date createdAt, Date updatedAt, Subheader subheader) {
        this.name = name;
        this.urlImage = urlImage;
        this.level = level;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.subheader = subheader;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;

        Lesson lesson = (Lesson) o;

        return (id == lesson.id);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (urlImage != null ? urlImage.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (subheader != null ? subheader.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", level='" + level + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", subheader=" + subheader +
                '}';
    }
}

