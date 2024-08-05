package com.solux.innovel.search.searchlog;

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


    public void saveRecentSearchLogBySocialId(String socialId, String searchKeyword) {
        User user = userRepository.findBySocialId(socialId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        String now = LocalDateTime.now().toString();
        String key = "SearchLog" + user.getId();
        SearchLog value = SearchLog.builder()
                .name(searchKeyword)
                .createdAt(now)
                .build();

        Long size = redisTemplate.opsForList().size(key);
        if (size == 5) {
            // rightPop을 통해 가장 오래된 데이터 삭제
            redisTemplate.opsForList().rightPop(key);
        }

        redisTemplate.opsForList().leftPush(key, value);
    }

    public List<SearchLog> findRecentSearchLogsBySocialId(String socialId) {
        User user = userRepository.findBySocialId(socialId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        String key = "SearchLog" + user.getId();
        List<SearchLog> logs = redisTemplate.opsForList().
                range(key, 0, 5);

        return logs;
    }
}