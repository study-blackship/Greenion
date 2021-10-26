package com.blackship.greenion.feed.response;

import com.blackship.greenion.feed.domain.Feed;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedResponse {

    private Long id;
    private String user;
    private String title;
    private String content;
    private String imagePath;
    private String tag;

    @Builder
    private FeedResponse(Long id, String user, String title, String content, String imagePath, String tag) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.tag = tag;
    }

    public static FeedResponse of(Feed feed) {
        return FeedResponse.builder()
                .id(feed.getId())
                .user(feed.getUser())
                .title(feed.getTitle())
                .imagePath(feed.getImagePath())
                .content(feed.getContent())
                .tag(feed.getTag())
                .build();
    }
}
