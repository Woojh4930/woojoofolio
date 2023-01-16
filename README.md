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

### Git
- [X] 깃허브 원격 저장소 생성
  - 상단 메뉴 Git -> GitHub -> Share Project on GitHub

### Gradle Test
- [ ] 테스트 코드 작성
### Lombok
- [ ] 롬복 설치
### JPA
- [ ] Spring Data JPA 설치
- [ ] Spring Data JPA 테스트 코드 작성
- [ ] JPA Auditing을 이용하여 생성시간/수정시간 자동화하기
### mustache
- [ ] mustach를 이용한 기본 페이지 만들기
- [ ] 게시글 등록 화면 만들기
- [ ] 전체 조회 화면 만들기
- [ ] 게시글 수정, 삭제 화면 만들기
### Spring Security
- [ ] 스프링 시큐리티 설정
### OAuth 2.0
- [ ] 구글 로그인 연동
- [ ] 네이버 로그인 연동
### AWS
#### EC2
- [ ] 인스턴스 생성
- [ ] 보안그룹 생성
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
