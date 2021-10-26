package com.blackship.greenion.feed;

import com.blackship.greenion.feed.domain.Feed;
import com.blackship.greenion.feed.request.FeedRequest;
import com.blackship.greenion.feed.response.FeedResponse;
import com.blackship.greenion.feed.service.FeedService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FeedServiceTest {

    private final FeedService feedService;

    public FeedServiceTest(FeedService feedService) {
        this.feedService = feedService;
    }

    @Test
    @DisplayName("유저가 로그인 시 피드를 생성한다")
    void create_feed() {
        // given
        FeedRequest feedRequest = addRequest();

        // when
        FeedResponse feedResponse = feedService.addFeed(feedRequest);
        Feed feed = feedService.findByFeedId(feedResponse.getId());

        // then
        assertThat(feed.getContent()).isEqualTo(feedRequest.getContent());
        assertThat(feed.getTitle()).isEqualTo(feedRequest.getTitle());
        assertThat(feed.getImagePath()).isEqualTo(feedRequest.getImagePath());
        assertThat(feed.getUser()).isEqualTo(feedRequest.getAuthor());
        assertThat(feed.getTag()).isEqualTo(feedRequest.getTag());
    }

    private FeedRequest addRequest() {
        return FeedRequest.builder()
                .author("함승훈")
                .content("나의 피드")
                .imagePath("/fakePath/image/test.jpg")
                .title("피드")
                .tag("식물")
                .build();
    }
}
