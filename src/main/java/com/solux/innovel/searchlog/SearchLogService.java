package com.solux.innovel.searchlog;

import java.util.List;
import com.solux.innovel.models.User;
import com.solux.innovel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SearchLogService {
    private final RedisTemplate<String, SearchLog> redisTemplate;
    private final UserRepository userRepository;

    public void saveRecentSearchLog(Long memberId, SearchLogRequest request) {
        User user = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        String now = LocalDateTime.now().toString();
        String key = "SearchLog" + user.getId();
        SearchLog value = SearchLog.builder()
                .name(request.getName())
                .createdAt(now)
                .build();

        Long size = redisTemplate.opsForList().size(key);
        if (size == 10) {
            // rightPop을 통해 가장 오래된 데이터 삭제
            redisTemplate.opsForList().rightPop(key);
        }

        redisTemplate.opsForList().leftPush(key, value);
    }

    public List<SearchLog> findRecentSearchLogs(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        String key = "SearchLog" + user.getId();
        List<SearchLog> logs = redisTemplate.opsForList().
                range(key, 0, 10);

        return logs;
    }

    public void deleteRecentSearchLog(Long memberId, SearchLogRequest request) {
        User member = userRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        String key = "SearchLog" + member.getId();
        SearchLog value = SearchLog.builder()
                .name(request.getName())
                .createdAt(request.getCreatedAt())
                .build();

        long count = redisTemplate.opsForList().remove(key, 1, value);

        if (count == 0) {
            throw new RuntimeException("Search Log Not Exist");
        }
    }
}