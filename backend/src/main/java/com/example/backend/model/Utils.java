package com.example.backend.model;

/**
 * packageName : com.example.backend.model
 * fileName : Utils
 * author : san26
 * date : 2024-03-08
 * description :
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * —————————————————————————————
 * 2024-03-08         san26          최초 생성
 */

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Utils() {
    }

    public static <T> T getObject(final String message, Class<T> valueType) throws Exception {
        return objectMapper.readValue(message, valueType);
    }

    public static <T> String getString(final T message) throws Exception {
        return objectMapper.writeValueAsString(message);
    }

}