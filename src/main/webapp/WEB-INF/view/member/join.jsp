<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<c:url var="action" value="/member/join" />

<layout:main title="회원가입">
        <h1>회원가입</h1>
        <form method="POST" action="${action}" target="ifrmProcess">
            <dl>
                <dt>아이디</dt>
                <dd>
                    <input type="text" name="userId">
                </dd>
            </dl>
            <dl>
                <dt>비밀번호</dt>
                <dd>
                    <input type="password" name="userPw">
                </dd>
            </dl>
            <dl>
                <dt>비밀번호 확인</dt>
                <dd>
                    <input type="password" name="confirmUserPw">
                </dd>
            </dl>
            <dl>
                <dt>이름</dt>
                <dd>
                    <input type="text" name="userNm">
                </dd>
            </dl>
            <dl>
                <dt>휴대전화 번호</dt>
                <dd>
                    <input type="text" name="phoneNum">
                </dd>
            </dl>
            <dl>
                <dt>이메일</dt>
                <dd>
                    <input type="email" name="email">
                </dd>
            </dl>
            <dl>
                <dt>닉네임(별명)</dt>
                <dd>
                    <input type="text" name="nickNm">
                </dd>
            </dl>


        <div>
            <input type="checkbox" name="agree" value="true" id="agree" >
            <label for="agree">회원가입 약관에 동의합니다.</label>
        </div>
        <button type="reset">다시입력</button>
        <button type="submit">가입하기</button>
    </form>
</layout:main>