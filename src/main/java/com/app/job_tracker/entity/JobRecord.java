package com.app.job_tracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="jobs")
public class JobRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String positionTitle;

    @Column(nullable = false)
    private String resumeVersion;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate applicationDate = LocalDate.now(); // Default to current date

    @Column
    private String applicationStatus = "Applied"; // Default status

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    // Optional fields
    private String location;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private String jobDescription;
    private String sourceOfJobListing;
    private String followUpDate;
    private String interviewDate;
    private String offerDetails;
    private String notes;
    private String outcome;
    private String salaryOffered;
    private String referralSource;

    public JobRecord(User user,String companyName, String positionTitle, String resumeVersion){
        this.user = user;
        this.companyName = companyName;
        this.positionTitle = positionTitle;
        this.resumeVersion = resumeVersion;
    }
}
