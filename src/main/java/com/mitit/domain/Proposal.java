package com.mitit.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "proposals")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String price;

    @Column(name = "posted_date")
    private LocalDateTime date;

    private String description;
    private String link;
    private String additional_info_tags;

    @OneToOne
    private FreelanceSite freelanceSite;

    @ManyToMany
    @JoinTable(
            name = "proposals_subcategories",
            joinColumns = @JoinColumn(name = "proposal_id"),
            inverseJoinColumns = @JoinColumn(name = "subcategory_id"))
    private List<Subcategory> subcategories = new ArrayList<>();
}
