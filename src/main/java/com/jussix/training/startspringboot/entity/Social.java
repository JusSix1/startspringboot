package com.jussix.training.startspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_social")
public class Social extends BaseEntity{

    @Column( length = 120)
    private String facebook;

    @Column( length = 120)
    private String line;

    @Column( length = 120)
    private String instagram;

    @Column( length = 120)
    private String tiktok;

    @OneToOne
    @JoinColumn(name = "m_user_id", nullable = false)
    private User user;
}
