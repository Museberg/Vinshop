package com.egfds.vinshop.models;

import javax.persistence.*;

@Entity
public class FarmSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(1500)")
    private String aboutFarm;
    @Column
    private String url1;
    @Column
    private String url2;
    @Column
    private String url3;

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

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }
}
