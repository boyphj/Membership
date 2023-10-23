package models.member;

import commons.BadRequestException;
import commons.LengthValidator;
import commons.RequiredValidator;
import commons.Validator;

public class JoinValidator implements Validator<Member>, RequiredValidator, LengthValidator {

    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @Override
    public void check(Member member) {

        String userId = member.getUserId();
        String userPw = member.getUserPw();
        String confirmUserPw = member.getConfirmUserPw();
        String userNm = member.getUserNm();
        String phoneNum = member.getPhoneNum();
        String nickNm = member.getNickNm();


        // 필수 항목 검증 Start
        requiredCheck(userId, new BadRequestException("아이디를 입력하세요."));
        requiredCheck(userPw, new BadRequestException("비밀번호를 입력하세요."));
        requiredCheck(confirmUserPw, new BadRequestException("비밀번호를 확인하세요."));
        requiredCheck(member.getUserNm(), new BadRequestException("이름을 입력하세요."));
        requiredCheck(member.getPhoneNum(), new BadRequestException("휴대전화 번호를 입력하세요."));
        requiredCheck(member.getEmail(), new BadRequestException("이메일을 입력하세요."));
        requiredCheck(member.getNickNm(), new BadRequestException("닉네임을 입력하세요."));
        requiredTrue(member.isAgree(),new BadRequestException("가입 약관에 동의하세요."));
        // 필수 항목 검증 End

        // 아이디, 비밀번호, 이름, 휴대전화, 닉네임 자리수 체크 Start
        lengthCheck(userId, 6, new BadRequestException("아이디는 6자리 이상 입니다."));
        lengthCheck(userPw, 8, new BadRequestException("비밀번호는 8자리 이상 입니다."));
        lengthCheck(userNm, 2, new BadRequestException("이름은 두글자 이상 입니다.."));
        lengthCheck(phoneNum, 11, new BadRequestException("휴대전화 번호는 11자리 이상 입니다.."));
        lengthCheck(nickNm, 3, new BadRequestException("닉네임은 세글자 이상 입니다."));
        // 아이디, 비밀번호, 닉네임 자리수 체크 End

        // 비밀번호, 비밀번호 확인 일치여부 체크
        requiredTrue(userPw.equals(confirmUserPw), new BadRequestException("비밀번호가 일치하지 않습니다."));

        // 중복 가입 여부 체크
        requiredTrue(!memberDao.exists(userId), new DuplicateMemberException());


    }
}
