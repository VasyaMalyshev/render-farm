package ru.malyshev.renderfarm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus taskStatus;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(name = "user_tasks",
            joinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<User> users;

    @CreatedDate
    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created")
    private Date created;

    @CreatedDate
    @JsonIgnore
    @Column(name = "completeDate")
    private Date completeDate;

}
