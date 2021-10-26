package com.blackship.greenion.feed.service;

import com.blackship.greenion.feed.domain.Feed;
import com.blackship.greenion.feed.domain.FeedRepository;
import com.blackship.greenion.feed.request.FeedRequest;
import com.blackship.greenion.feed.response.FeedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;

    public FeedResponse addFeed(FeedRequest feedRequest) {
        Feed feed = FeedRequest.of(feedRequest);
        return FeedResponse.of(feedRepository.save(feed));
    }

    @Transactional(readOnly = true)
    public Feed findByFeedId(Long id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 피드를 찾을 수 없습니다"));
    }
}
