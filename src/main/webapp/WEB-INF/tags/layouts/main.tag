<%@ tag body-content="scriptless" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<layout:common title="${title}">
    <jsp:attribute name="header">
       <h1>첫번째 개인 프로젝트</h1>
       <nav>
            <a href="#">오늘의 특가</a><br><br>
            <a href="#">베스트 100</a><br><br>
            <a href="#">인기 급상승</a><br><br>
            <br>
            <br>
            <br>
       </nav>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <h4>Daniel의 코딩 연습소에 오신 걸 환영합니다.</h4>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody />
    </jsp:body>
</layout:common>