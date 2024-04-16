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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/test")
    public void test() {
        System.out.println("test");
        SecurityContextHolderStrategy contextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
        System.out.println(">> contextHolderStrategy : " + contextHolderStrategy); // org.springframework.security.core.context.ThreadLocalSecurityContextHolderStrategy@7e1fbf12
        SecurityContext context = contextHolderStrategy.getContext();
        System.out.println(">> context : " + context); // SecurityContextImpl [Authentication=AnonymousAuthenticationToken
        Authentication authentication = context.getAuthentication();
        System.out.println(">> authentication : " + authentication); // AnonymousAuthenticationToken
        MemberContext memberContext = (MemberContext) authentication.getPrincipal();
        System.out.println(">> memberContext : " + memberContext); // ClassCastException
        String username = memberContext.getUsername();
        System.out.println(">> username : " + username);
    }
}
