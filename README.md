# 멋사 해커톤 195팀 백엔드

## 개발환경
### Spring Boot : 2.7.14
### Java : 11.0.20


---
## 커밋 컨벤션

### 제목은 최대 50글자까지 아래에 작성: ex) Feat: Add Key mapping

### 본문은 아래에 작성

### 꼬릿말은 아래에 작성: ex) Github issue #23

### --- COMMIT END ---
###   <타입> 리스트
###   feat        : 기능 (새로운 기능)
###   fix         : 버그 (버그 수정)
###   refactor    : 리팩토링
###   design      : CSS 등 사용자 UI 디자인 변경
###   comment     : 필요한 주석 추가 및 변경
###   style       : 스타일 (코드 형식, 세미콜론 추가: 비즈니스 로직에 변경 없음)
###   docs        : 문서 수정 (문서 추가, 수정, 삭제, README)
###   test        : 테스트 (테스트 코드 추가, 수정, 삭제: 비즈니스 로직에 변경 없음)
###   chore       : 기타 변경사항 (빌드 스크립트 수정, assets, 패키지 매니저 등)
###   init        : 초기 생성
###   rename      : 파일 혹은 폴더명을 수정하거나 옮기는 작업만 한 경우
###   remove      : 파일을 삭제하는 작업만 수행한 경우
### ------------------
###   제목 첫 글자를 대문자로
###   제목은 명령문으로
###   제목 끝에 마침표(.) 금지
###   제목과 본문을 한 줄 띄워 분리하기
###   본문은 "어떻게" 보다 "무엇을", "왜"를 설명한다.
###   본문에 여러줄의 메시지를 작성할 땐 "-"로 구분
### ------------------
###   <꼬리말>
###   필수가 아닌 optioanl
###   Fixes        :이슈 수정중 (아직 해결되지 않은 경우)
###   Resolves     : 이슈 해결했을 때 사용
###   Ref          : 참고할 이슈가 있을 때 사용
###   Related to   : 해당 커밋에 관련된 이슈번호 (아직 해결되지 않은 경우)
###   ex) Fixes: #47 Related to: #32, #21  

# 계층분리 및 기능
## Controller (컨트롤러) 계층
- **설명:** 사용자의 요청을 받아 처리하고, 적절한 서비스 계층의 메서드를 호출하거나 응답을 반환하는 계층입니다.
- **스프링 구성:** `@Controller` 또는 `@RestController` 어노테이션을 사용하여 클래스를 정의합니다.

## Service (서비스) 계층
- **설명:** 비즈니스 로직을 처리하는 계층입니다. 데이터의 CRUD 연산이나 트랜잭션 관리 등의 로직을 포함합니다.
- **스프링 구성:** `@Service` 어노테이션을 사용하여 클래스를 정의합니다.

## Repository (리포지토리) 계층
- **설명:** 데이터베이스와의 직접적인 연동을 담당하는 계층입니다. CRUD 연산을 수행합니다.
- **스프링 구성:** `@Repository` 어노테이션을 사용하거나, Spring Data JPA를 사용할 경우 `JpaRepository` 인터페이스를 확장하여 인터페이스를 정의합니다.

## DTO (Data Transfer Object)
- **설명:** 계층 간 데이터 전송을 위한 객체입니다. 주로 컨트롤러와 서비스 계층 간에 데이터를 전달할 때 사용됩니다.
- **스프링 구성:** 특별한 어노테이션 없이 POJO (Plain Old Java Object)로 정의됩니다.

## Domain (도메인) 계층
- **설명:** 비즈니스 도메인을 표현하는 객체나 엔터티가 포함된 계층입니다.
- **스프링 구성:** JPA를 사용할 경우 `@Entity` 어노테이션을 사용하여 클래스를 정의합니다.

