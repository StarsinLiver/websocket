package com.example.backend.model;

import lombok.*;

/**
 * packageName : com.example.backend.model
 * fileName : Message
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


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String type;
    private String sender;
    private String receiver;
    private Object data;
}