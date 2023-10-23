<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<fmt:setBundle basename="messages.commons" />
<c:url var="loginUrl" value="/member/login" />
<c:url var="joinUrl" value="/member/join" />
<c:url var="logoutUrl" value="/member/logout" />
<c:url var="mypageUrl" value="/member/mypage" />



<layout:main title="메인 페이지">
    <c:if test="${sessionScope.member == null}"> <%-- 미 로그인 상태 --%>
        <br>
        <br>
        <a href="${loginUrl}">
        <button type="submit">로그인</button>
        </a>
        <br>
        <br>
        <a href="${joinUrl}">
        <button type="submit">회원 가입</button>
        </a>
    </c:if>
    <c:if test="${sessionScope.member != null}"> <%-- 로그인 상태 --%>
        <fmt:message key="LOGIN_MSG">
            <fmt:param>${sessionScope.member.userNm}</fmt:param>
            <fmt:param>${sessionScope.member.userId}</fmt:param>
        </fmt:message>
        <br>
        <br>
        <br>
        <a href="${logoutUrl}">
        <button type="submit">로그아웃</button>
        </a>
        <br>
        <br>
        <a href="${mypageUrl}">
        <button type="submit">마이 페이지</button>
        </a>
    </c:if>
</layout:main>
