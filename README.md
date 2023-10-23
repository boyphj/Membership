# Membership 프로젝트 2023년 10월 23일

## TDD를 통한 설계
1. 의존성(pom.xml)
    - junit5
    - mockito
    - servlet-api
    - servlet.jsp-api
    - jstl-api
    - jstl-implementation
    - lmbok
    - maven-sure-fire plugin

2. 톰캣 서버 설정

3. 회원 가입 기능 설계(JoinService)
   - models/member/JoinService.java
   - 필수 항목 검증(아이디, 비밀번호, 비밀번호 확인, 이름, 이메일, 전화번호, 닉네임, 가입 약관 동의)
   - 아이디(6자리 이상),비밀번호(8자리 이상), 이름(두글자 이상), 전화번호(11자리 이상), 닉네임(세글자 이상) 체크
   - '비밀번호'와 '비밀번호 확인' 입력 데이터 일치 체크
   - 아이디 중복 여부 체크(중복 된 경우 가입 불가)   
   - 회원 정보 저장

4. 로그인 기능 설계(LoginService)
   - models/member/LoginService.java
   - 필수 항목 검증(아이디, 비밀번호)
   - 아이디에 해당하는 회원 정보가 있는지 체크
   - 로그인 처리(세션에 회원 정보를 저장)

5. 기능 통합
   - 회원 가입
     - Controller : /member/join
       - controllers/member/JoinController.java
       - GET : 회원 가입 양식
       - POST : 회원 가입 처리
     - View : /WEB-INF/view/member/join.jsp
   - 로그인
    - Controller : /member/login
        - controllers/member/LoginController.java
        - GET : 로그인 양식
        - POST : 로그인 처리
     - View : /WEB-INF/view/member/login.jsp
   - 메인페이지
      - 로그인 상태
         - 이름(아이디)님 로그인 메세지 출력
         - 로그아웃(/member/logout), 마이페이지(/mypage) 링크
      - 미로그인 상태
        - 회원 가입(/member/join), 로그인(/member/login) 링크
   - 로그아웃
     - Controllers /member/logout
       - controllers/member/LogoutController.java
     - GET, POST 메서드 상관 없이 기능을 수행할 수 있도록 처리

## 완성 화면
### 회원 가입
![회원 가입](https://raw.githubusercontent.com/boyphj/Membership/master/images/join.png)

### 로그인
![로그인](https://raw.githubusercontent.com/boyphj/Membership/master/images/login.png)

### 로그인 후
![로그인 후](https://raw.githubusercontent.com/boyphj/Membership/master/images/afterlogin.png)

### 메인 페이지
![메인 페이지](https://raw.githubusercontent.com/boyphj/Membership/master/images/main.png)