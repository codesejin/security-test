package com.spring.security.config.security.service;

import com.spring.security.domain.Member;
import com.spring.security.repository.mysql.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.spring.security.exception.member.MemberErrorMessage.NOT_EXIST_MEMBER;


@RequiredArgsConstructor
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_EXIST_MEMBER));
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(member.getRole().name()));
        return new MemberContext(member, roles);
    }
}