package com.spring.security.controller;


import com.spring.security.config.security.service.MemberContext;
import com.spring.security.dto.request.SignupMemberRequestDto;
import com.spring.security.service.MemberService;
import com.spring.security.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(@RequestBody @Valid final SignupMemberRequestDto signupMemberRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signUp(signupMemberRequestDto));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/test")
    public void test(@AuthenticationPrincipal MemberContext memberContext) {
        System.out.println("test");

        System.out.println("memberContext : " + memberContext); // null
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication : " + authentication); // anonymousUser
        MemberContext memberContext1 = (MemberContext) authentication.getPrincipal();
        System.out.println("memberContext1 : " + memberContext1); // ClassCastException
    }
}
