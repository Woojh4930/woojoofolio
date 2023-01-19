# WOOJOOFOLIO

<hr/>

## SKILLS
<br/>

### SpringBoot
- [X] 그레이들 프로젝트 -> 스프링 부트 프로젝트로 변경
  - build.gradle에서 내용 변경


    buildscript {
        ext {
        springBootVersion = '2.1.7.RELEASE'
        }

        repositories {
        mavenCentral()
        }

        dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
        }
    }
    
    apply plugin: 'java'
    apply plugin: 'eclipse'
    apply plugin: 'org.springframework.boot'
    apply plugin: 'io.spring.dependency-management'
    
    
    group 'com.woojoofolio.project'
    version '1.0-SNAPSHOT'
    sourceCompatibility = 11
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
        implementation('org.springframework.boot:spring-boot-starter-web')
        testImplementation('org.springframework.boot:spring-boot-starter-test')
    }

  - [X] springBoot Application 자체 서버 실행
### Git
- [X] 깃허브 원격 저장소 생성
  - 상단 메뉴 Git -> GitHub -> Share Project on GitHub

### Gradle Test
- [X] 테스트 코드 작성
  - UserRepositoryTest
    - [X] User Entity 생성 테스트
    - [X] 생성날짜, 수정날짜 컬럼 자동기입 테스트
  - PostsRepositoryTest
    - [X] Posts Entity 생성 테스트
    - [X] 생성날짜, 수정날짜 컬럼 자동기입 테스트
  - IndexControllerTest
    - [X] 메인 페이지 로딩 테스트
  - PostApiControllerTest
    - [X] Posts CRUD 테스트
### Lombok
- [X] 롬복 설치
  - 플러그인 설치 : ctrl+shift+A -> plugins -> Lombok install -> reboot intelliJ
  - 롬복 의존성 추가


    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'

### JPA
- [X] Spring Data JPA 설치
  - JPA 의존성 추가

    
    implementation('org.springframework.boot:spring-boot-starter-data-jpa')
    implementation('com.h2database:h2')
- [X] JPA Auditing을 이용하여 생성시간/수정시간 자동화하기
  - domain 패키지에 BaseTimeEntity 생성
  - @MappedSuperclass는 다른 엔티티가 상속한 경우 칼럼으로 인식
  - @EntityListeners(클래스) 해당 클래스의 기능을 빌려옴
  - User, Posts에 BaseTimeEntity 상속
### mustache
- [X] mustache를 이용한 기본 페이지 만들기
  - plugins에서 mustache 검색 후 install
  - 재부팅 후 의존성 추가
  - IndexController 생성 후 이름 리턴


    implementation 'org.springframework.boot:spring-boot-starter-mustache'
- [X] 게시글 등록 화면 만들기
  - PostsApiController(REST) 생성 후 /api/v1/posts 관리
  - PostsRequestDto 생성 후 Posts Entity로 바꾸는 메서드 만들기
  - index.js의 ajax 를 통해 form 안의 정보들을 PostRequestDto로 변환
- [X] 전체 조회 화면 만들기
  - PostsRepository에서 @Query를 이용하여 내림차순 조회하는 쿼리문 만들기
  - PostsService에서 리스트에 있는 Posts를 map 하여 PostsListResponseDto로 변환(id,title,author,modifiedDate)
  - 조회용은 @Transactional에 readonly=true 하기
  - IndexController의 Model에 담기
  - index.mustache에서 mustache 문법을 이용하여 PostListResponseDto 정보 보여주기
- [X] 게시글 수정, 삭제 화면 만들기
  - 수정
    - 제목, 내용을 받을 PostsUpdateRequestDto 생성
    - Posts에서 PostsUpdateRequestDto 정보를 update하는 메서드 만들기
    - PostsService 에서 id를 통해 Posts를 찾고 PostsUpdateRequestDto를 주입하는 메서드 생성
    - index.js의 ajax를 통해 id와 제목, 내용 data 전달
  - 삭제
    - 삭제할 id를 통해 PostsRepository에서 삭제
    - index.js의 ajax를 통해 id 전달(@PathVariable)
### Spring Security
- [X] 스프링 시큐리티 설정
  - 의존성 추가


    implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'

### OAuth 2.0
- [X] 구글 로그인 연동
  - OAuthAttributes
    - registrationId를 통해 구글, 네이버 로그인 구분
    - userNameAttributeName을 통해 자격 인증
    - attributes를 통해 유저의 정보 저장
    - toEntity method를 통해 User 정보로 변환
  - SessionUser
    - User Entity와 유사하지만 직렬화를 지원
    - User Entity에서 받은 정보 중 필요한 정보만 session에 저장해주는 역할
  - CustomOAuth2UserService
    - attributes -> User Entity -> SessionUserDto 로 변경해주는 역할
  - SecurityConfig
    - User의 역할에 따라 접근 가능한 URL을 구분
  - application-oauth.properties를 통해 클라이언트 아이디와 비밀번호, scope 정보 입력
- [X] 네이버 로그인 연동
  - 네이버 로그인은 스프링 시큐리티를 지원하지 않기 때문에 application-oauth에 적어야 할 정보가 많음
### AWS
#### EC2
- [X] 인스턴스 생성
- [X] 보안그룹 생성
- [X] 탄력적 IP 할당
- [X] EC2 연결
  - 윈도우의 경우 개인 키(.pem) 다운받은 뒤 터미널에서 연결할 경로에 옮기고 EC2 인스턴스에서 연결에 들어가 ssh 연결 명령어를 터미널에 입력한다.

![img.png](img.png)
#### RDS
- [ ] RDS 인스턴스 생성
- [ ] 파라미터 그룹 설정
- [ ] EC2에서 RDS 접근 확인
#### S3
- [ ] 버킷 생성
#### CodeDeploy
- [ ] 에이전트 설치
- [ ] 권한 생성
- [ ] Github Action, S3, CodeDeploy 연동
#### IAM
- [ ] 역할 생성
- [ ] 사용자 생성
- [ ] EC2에 IAM역할 추가
### Nginx
- [ ] Nginx 설치
- [ ] Nginx 포트 연결
### Github Action
- [ ] 깃 푸시를 했을 때 자동으로 테스트 후 배포하는 deploy.yml 생성
### 기타
- 어노테이션을 이용해서 파라미터로 언제든지 세션에 있는 SessionUser 정보 조회
- h2 database session에 정보 저장하는 설정
  - application.properties에 "spring.session.store-type=jdbc" 추가
  - 의존성 추가


    implementation 'org.springframework.session:spring-session-jdbc'
-Jpa Auditing 기능 개선