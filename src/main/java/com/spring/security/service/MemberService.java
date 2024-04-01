package com.spring.security.service;


import com.spring.security.domain.Member;
import com.spring.security.dto.request.SignupMemberRequestDto;
import com.spring.security.dto.response.SignupMemberResponseDto;
import com.spring.security.exception.member.MemberBadRequestException;
import com.spring.security.repository.mysql.MemberRepository;
import com.spring.security.util.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.spring.security.exception.member.MemberErrorMessage.DUPLICATED_EMAIL;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    @Transactional
    public ResponseDTO<SignupMemberResponseDto> signUp(SignupMemberRequestDto signupMemberRequestDto) {
        validateEmailNotDuplicated(signupMemberRequestDto.getEmail());
        Member entity = toEntity(signupMemberRequestDto);
        memberRepository.save(entity);
        return ResponseDTO.getSuccessResult(new SignupMemberResponseDto(entity));
    }

    @Transactional(readOnly = true)
    public void validateEmailNotDuplicated(String email) {
        boolean isDuplicated = memberRepository.existMemberByEmail(email);
        if (isDuplicated) {
            throw new MemberBadRequestException(DUPLICATED_EMAIL);
        }
    }

    private Member toEntity(SignupMemberRequestDto signupMemberRequestDto) {
        return Member.create(
                signupMemberRequestDto.getEmail(),
                passwordEncoder.encode(signupMemberRequestDto.getPassword()),
                signupMemberRequestDto.getName(),
                signupMemberRequestDto.getBirthdate(),
                signupMemberRequestDto.getPhone(),
                signupMemberRequestDto.getRole());
    }
}
