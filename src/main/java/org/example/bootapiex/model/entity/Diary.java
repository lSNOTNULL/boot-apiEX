package org.example.bootapiex.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // lombok으로 했으니 private
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 2000)
    private String content;
    //Nullable 첨부를 안해도 되도록.
    private String filename;

    @CreationTimestamp
    LocalDateTime createdAt = LocalDateTime.now();
}
