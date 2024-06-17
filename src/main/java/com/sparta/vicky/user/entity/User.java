package com.sparta.vicky.user.entity;

import com.sparta.vicky.base.entity.Timestamped;
import com.sparta.vicky.user.dto.ProfileRequest;
import com.sparta.vicky.user.dto.SignupRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // 사용자 ID

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name; // 사용자 이름

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String introduce; // 소개

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status; // 사용자 상태 [JOINED, WITHDRAWN]

    @Column(nullable = false)
    private LocalDateTime statusUpdatedAt;

    /**
     * 생성자
     */
    public User(SignupRequest requestDto, String encodedPassword) {
        this.username = requestDto.getUsername();
        this.password = encodedPassword;
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.introduce = requestDto.getIntroduce();
        this.status = UserStatus.JOINED;
        this.statusUpdatedAt = LocalDateTime.now();
    }

    /**
     * 회원 탈퇴
     */
    public void withdraw() {
        this.status = UserStatus.WITHDRAWN;
        this.statusUpdatedAt = LocalDateTime.now();
    }

    /**
     * 프로필 수정
     */
    public void updateProfile(ProfileRequest requestDto) {
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.introduce = requestDto.getIntroduce();
    }

    /**
     * 비밀번호 수정
     */
    public void updatePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    /**
     * 검증 메서드
     */
    public void verifyUser(Long userId) {
        if (!this.id.equals(userId)) {
            throw new IllegalArgumentException("사용자의 id가 해당 id와 다릅니다.");
        }
    }

}
