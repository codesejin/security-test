package com.spring.security.exception.member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class MemberErrorMessage {

    public static final String CHECK_REQUEST_EMAIL = "이메일 포맷은 'xxx@xxx.xx' 혹은 'xxx@xxx.xxx' 입니다.";
    public static final String CHECK_REQUEST_PHONE = "휴대폰 번호의 포맷은 '010-123-1234' 혹은 '010-1234-1234' 입니다.";
    public static final String CHECK_REQUEST_PSWD_FORMAT = "비밀번호는 영단어 소문자, 숫자 각각 1개씩 포함되어야 합니다.";
    public static final String CHECK_REQUEST_PSWD_LENGTH = "{0}자 이상의 {1}자 이하의 비밀번호를 입력해주세요.";
    public static final String DUPLICATED_EMAIL = "이미 해당 이메일이 존재합니다.";
    public static final String EMAIL_MUST_NOT_EMPTY = "이메일을 입력해주세요.";
    public static final String PSWD_MUST_NOT_EMPTY = "비밀번호를 입력해주세요.";
    public static final String HASHED_PSWD_MUST_NOT_EMPTY = "암호화된 비밀번호는 null일 수 없습니다.";
    public static final String NAME_MUST_NOT_EMPTY = "이름을 입력해주세요.";
    public static final String BIRTHDATE_MUST_NOT_EMPTY = "생년월일을 입력해주세요.";
    public static final String PHONE_MUST_NOT_EMPTY = "휴대폰 번호를 입력해주세요.";
    public static final String ROLE_MUST_NOT_EMPTY = "회원 권한을 입력해주세요.";
    public static final String CHECK_EMAIL_OR_PSWD = "아이디 또는 비밀번호가 일치하지 않습니다.";
    public static final String DO_NOT_MATCHES_PSWD = "비밀번호가 일치하지 않습니다.";
    public static final String AUTHORIZED_BUT_NOT_AUTHENTICATED = "인증되었지만 접근 권한이 없습니다.";
    public static final String NOT_EXIST_MEMBER = "존재하지 않는 유저 입니다.";
}
