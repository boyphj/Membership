package models.member;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class Member {
    private String userId; // 아이디
    private String userPw;  // 비밀번호
    private String confirmUserPw; // 비밀번호 확인
    private String userNm; // 이름
    private String phoneNum; // 휴대전화 번호
    private String email; // 이메일
    private String nickNm; // 닉네임

    private boolean agree; // 약관


}

