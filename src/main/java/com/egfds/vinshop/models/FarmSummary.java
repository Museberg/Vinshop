package com.egfds.vinshop.models;

import javax.persistence.*;

@Entity
public class FarmSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(1500)")
    private String aboutFarm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAboutFarm() {
        return aboutFarm;
    }

    public void setAboutFarm(String aboutFarm) {
        this.aboutFarm = aboutFarm;
    }

    @Override
    public String toString() {
        return "FarmSummary{" +
                "id=" + id +
                ", aboutFarm='" + aboutFarm + '\'' +
                '}';
    }
}
