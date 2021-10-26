package com.blackship.greenion.feed.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String user;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String imagePath;

    @Column
    private String tag;

    public Feed(String title, String user, String imagePath) {
        this(user, title, imagePath, null);
    }

    public Feed(String title, String user, String imagePath, String content) {
        this(title, user, imagePath, content, null);
    }

    @Builder
    public Feed(String title, String user, String imagePath, String content, String tag) {
        // TODO: validation code
        this.title = title;
        this.imagePath = imagePath;
        this.user = user;
        this.content = content;
        this.tag = tag;
    }
}
