package com.woojoofolio.project.springboot.domain;

import lombok.Getter;
import net.bytebuddy.asm.Advice;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
}
