package pl.przybylowski.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import java.util.Date;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Project name is required")
    private String projectName;

    @NotBlank(message = "Project Identifier is required")
    @Size(min = 4, max = 5, message = "Pleas use 4-5 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    @NotBlank(message = "Description is required")
    private String description;

    @JsonFormat(pattern = "yyy-mm-dd")
    private Date  start_date;

    @JsonFormat(pattern = "yyy-mm-dd")
    private Date end_Date;

    @JsonFormat(pattern = "yyy-mm-dd")
    private Date created_At;

    @JsonFormat(pattern = "yyy-mm-dd")
    private Date update_At;

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.update_At = new Date();
    }

    public Project() {
    }
}
