#  spring-product-api

스프링 부트를 활용한 **위시리스트(WishList)** 관리 REST API 프로젝트입니다.
<br><br>
---
## 추가 구현 기능(07.15)
- JdbcClient -> JPA로 리팩토링 진행
- Service Layer에서 Transactional의 Default 값을 ReadOnly = true로 설정(OneToOne, OneToMany, LazyLoding을 정상적으로 수행하기 위해) https://resilient-923.tistory.com/415 해당 글 참고하였습니다!
- 커스텀 Exception등 RuntimeException의 일환이므로 Transactional내에서 자동으로 롤백되니 따로 rollback for을 지정해주지 않았음.
- 복합키는 연관관계 매핑이 포함된 복합키이므로 @IdClass대신 @EmbeddedId를 이용해서 구현.
- RepositoryImpl은 다 제거하고 인터페이스 내에서 JPA가 기본적으로 제공하는 메소드를 제외하고 커스텀 메소드만 남겨둠.
- 여러가지 사용하지 않는 메소드와 필요없는 클래스 제거 및 의도가 불분명한 클래스 네이밍 변경.

---

## 구현 기능

###  상품 목록 조회

- **URL**: `GET /products`
- **설명**: 등록된 모든 상품 목록을 조회합니다.
---

###  상품 단건 조회

- **URL**: `GET /products/{id}`
- **설명**: ID에 해당하는 상품 정보를 조회합니다.
---

###  상품 추가

- **URL**: `POST /products`
- **설명**: 새로운 상품을 등록합니다.
- **요청 바디 예시**:
```json
{
  "name": "초코 케이크",
  "price": 5000,
  "imageUrl": "https://example.com/choco.jpg"
}
```
###  상품 삭제

- **URL**: `DELETE /products/{id}`
- **설명**: 지정한 ID의 상품을 삭제합니다.
---

## 관리자 페이지(Thymeleaf 기반)

### 상품 목록 (홈 화면)

- **URL**: GET /product-page
- **설명**: 관리자용 상품 리스트 페이지(HTML 기반)
---

### 상품 등록 폼

- **URL**: GET /product-page/new   
- **설명**: 새로운 상품을 등록하는 폼 페이지
---

### 상품 수정 폼

- **URL**: GET /product-page/{id}  
- **설명**: 기존 상품 정보를 수정하는 폼 페이지
---

### 상품 삭제 요청

- **URL**: POST /product-page/{id}/delete   
- **설명**: HTML 페이지에서 상품 삭제 요청을 전송합니다

### 기술 스택
Java 21

Spring Boot 3.5.3

Spring Web (REST API)

Spring JPA

Thymeleaf (관리자 페이지용)

H2 Database (in-memory)

JUnit5 (E2E 테스트 코드 작성)

Jwt(Spring Security 사용 X): refreshToken accessToken을 이용한 회원 로그인
-> 미션의 난이도를 고려해 RefreshToken은 명시만 해놓되 이용은 최대한 자제.(멘토님 조언)
