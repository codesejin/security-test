package com.spring.security.dto.response;

import com.spring.security.domain.Member;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Generated
@Getter
@RequiredArgsConstructor
public final class SignupMemberResponseDto {

    private final String email;

    private final String name;

    private final LocalDate birthdate;

    private final String phone;

    public SignupMemberResponseDto(Member entity) {
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.birthdate = entity.getBirthdate();
        this.phone = entity.getPhone();
    }
}
