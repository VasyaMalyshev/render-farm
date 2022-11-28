package ru.malyshev.renderfarm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class StatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus taskStatus;

    @CreatedDate
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;
}