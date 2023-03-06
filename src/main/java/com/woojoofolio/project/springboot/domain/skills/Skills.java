package com.woojoofolio.project.springboot.domain.skills;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String label;

    @Column(length = 500, nullable = false)
    private String src;

    @Column(length = 500, nullable = false)
    private String alt;

    @Builder
    public Skills(String label, String src, String alt) {
        this.label = label;
        this.src = src;
        this.alt = alt;
    }

    public void skillUpdate(String label, String src) {
        this.label = label;
        this.src = src;
    }
}
