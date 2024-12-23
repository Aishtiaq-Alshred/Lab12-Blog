package com.example.lab12blog.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "title should not be empty")
    @Size(max = 25,message = "title to long")
    @Column(columnDefinition = "varchar(25) not null")
    private String title;
    @NotEmpty(message = "body should not be empty")
    @Column(columnDefinition = "varchar(300) not null")
    private String body;


    @ManyToOne
    @JsonIgnore
    private User user;
}
