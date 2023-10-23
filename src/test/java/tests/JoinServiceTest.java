package tests;

import commons.BadRequestException;
import commons.LengthValidator;
import jakarta.servlet.http.HttpServletRequest;
import models.member.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DisplayName("회원가입 기능 단위테스트")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
public class JoinServiceTest {

    private JoinService joinService;
    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void init() {
        MemberDao.clearDate();
        joinService = ServiceManager.getInstance().joinService();
    }

    private Member getMember() {
        String userPw = "87654321";
        return Member.builder()
                .userId("user" + System.currentTimeMillis())
                .userPw(userPw)
                .confirmUserPw(userPw)
                .userNm("박호진")
                .phoneNum("01012345678")
                .email("parkhojin@naver.com")
                .nickNm("다니엘")
                .agree(true)
                .build();
    }

    @Test
    @DisplayName("회원가입 성공시 예외발생하지 않음")
    void joinSuccess() {
        assertDoesNotThrow(() -> {
            joinService.join(getMember());
        });
    }
    @Test
    @DisplayName("HttpServletRequest 요청 데이터로 성공 테스트")
    void joinSuccessByRequest(){
        Member member = getMember();
        given(request.getParameter("userId")).willReturn(member.getUserId());
        given(request.getParameter("userPw")).willReturn(member.getUserPw());
        given(request.getParameter("confirmUserPw")).willReturn(member.getConfirmUserPw());
        given(request.getParameter("userNm")).willReturn(member.getUserNm());
        given(request.getParameter("phoneNum")).willReturn(member.getPhoneNum());
        given(request.getParameter("email")).willReturn(member.getEmail());
        given(request.getParameter("nickNm")).willReturn(member.getNickNm());
        given(request.getParameter("agree")).willReturn(""+ member.isAgree());

        joinService.join(request);

    }

    @Test
    @DisplayName("필수 항목 검증(아이디, 비밀번호, 비밀번호 확인, 이름, 이메일, 닉네임, 회원가입약관 동의), 검증 실패시 BadRequestException 발생")
    void requiredFieldCheck() {
        assertAll(
                () -> {
                    // 아이디 검증(userId)
                    Member member = getMember();
                    member.setUserId(null);
                    fieldEachCheck(member, "아이디");

                    member.setUserId("  ");
                    fieldEachCheck(member, "아이디");
                },
                () -> {
                    // 비밀번호 검증(userPw)
                    Member member = getMember();
                    member.setUserPw(null);
                    fieldEachCheck(member, "비밀번호");

                    member.setUserPw("  ");
                    fieldEachCheck(member, "비밀번호");
                },
                () -> {
                    // 비밀번호 확인(confirmUserPw)
                    Member member = getMember();
                    member.setConfirmUserPw(null);
                    fieldEachCheck(member, "비밀번호를 확인");

                    member.setConfirmUserPw("  ");
                    fieldEachCheck(member, "비밀번호를 확인");
                },
                () -> {
                    // 이름 검증(userNm)
                    Member member = getMember();
                    member.setUserNm(null);
                    fieldEachCheck(member, "이름");

                    member.setUserNm("  ");
                    fieldEachCheck(member, "이름");
                },
                () -> {
                    // 휴대전화 번호 검증(userNm)
                    Member member = getMember();
                    member.setPhoneNum(null);
                    fieldEachCheck(member, "휴대전화");

                    member.setPhoneNum("  ");
                    fieldEachCheck(member, "휴대전화");
                },

                () -> {
                    // 이메일 검증(email)
                    Member member = getMember();
                    member.setEmail(null);
                    fieldEachCheck(member, "이메일");

                    member.setEmail("  ");
                    fieldEachCheck(member, "이메일");
                },
                () -> {
                    // 닉네임 검증(nickNm)
                    Member member = getMember();
                    member.setNickNm(null);
                    fieldEachCheck(member, "닉네임");

                    member.setNickNm("  ");
                    fieldEachCheck(member, "닉네임");
                },

                () -> {
                    // 약관 동의 검증(agree)
                    Member member = getMember();
                    member.setAgree(false);
                    fieldEachCheck(member, "약관");
                }
        );

    }

    private void fieldEachCheck(Member member, String word) {
        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            joinService.join(member);
        });

        assertTrue(thrown.getMessage().contains(word));

    }

    @Test
    @DisplayName("아이디(6자리 이상), 비밀번호(8자리 이상), 이름,(두글자 이상), 전화번호(11자리), 닉네임(세글자 이상) 최소 자리수 체크, 실패시 BadRequestException 발생")
    void fieldLengthCheck() {
        assertAll(
                () -> {
                    // 아이디 6자리 이상 검증
                    Member member = getMember();
                    member.setUserId("user");
                    fieldEachCheck(member, "아이디는 6자리");
                },
                () -> {
                    // 비밀번호 8자리 이상 검증
                    Member member = getMember();
                    member.setUserPw("1234");
                    fieldEachCheck(member, "비밀번호는 8자리");
                },
                () -> {
                    // 이름 두글자 이상 검증
                    Member member = getMember();
                    member.setUserNm("1");
                    fieldEachCheck(member, "이름은 두글자 이상");
                },
                () -> {
                    // 휴대전화 11자리 이상 검증
                    Member member = getMember();
                    member.setPhoneNum("0101234");
                    fieldEachCheck(member, "휴대전화 번호는 11자리");
                },
                () -> {
                    // 닉네임 세글자 이상 검증
                    Member member = getMember();
                    member.setNickNm("가나");
                    fieldEachCheck(member, "닉네임은 세글자 이상");
                }

        );
    }

    @Test
    @DisplayName("비밀번호, 비밀번호 확인 입력 데이터 일치 여부 체크, 검증 실패시 BadRequestException 발생")
    void passwordConfirmCheck() {
        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            Member member = getMember();
            member.setConfirmUserPw(member.getUserPw() + "**");
            joinService.join(member);
        });

        assertTrue(thrown.getMessage().contains("비밀번호가 일치"));
    }
    @Test@DisplayName("중복 가입 체크, 중복 가입인 경우 DuplicateMemberException 발생")
    void duplicateJoinCheck(){
        assertThrows(DuplicateMemberException.class, ()->{
            Member member = getMember();
            String userPw = member.getUserPw();
            joinService.join(member);

            member.setUserPw(userPw);
            joinService.join(member);
        });

    }

}