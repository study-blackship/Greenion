package com.blackship.greenion.feed.request;

import com.blackship.greenion.feed.domain.Feed;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedRequest {

    private String author;

    private String title;

    private String imagePath;

    private String content;

    private String tag;

    @Builder
    public FeedRequest(String author, String title, String imagePath, String content, String tag) {
        this.author = author;
        this.title = title;
        this.imagePath = imagePath;
        this.content = content;
        this.tag = tag;
    }

    public static Feed of(FeedRequest request) {
        return Feed.builder()
                .user(request.author)
                .title(request.getTitle())
                .imagePath(request.getImagePath())
                .content(request.content)
                .tag(request.getTag())
                .build();
    }
}
