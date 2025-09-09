# Product Service

## 모듈 설명
Product Service는 테스트용으로 작성된 제품 데이터를 관리하고 RESTful API를 통해 제공하는 Spring Boot 기반 애플리케이션입니다. 데이터는 자바 맵 리스트로 관리됩니다.

## Endpoint 별 기능

### 1. GET /products
- **기능**: 모든 제품 목록을 조회합니다.
- **예시 Input**: 없음
- **예시 Output**:
  ```json
  {
    "status": "success",
    "data": [
      {
        "id": 1,
        "name": "Product A",
        "price": 100,
        "creatorId": 1
      },
      {
        "id": 2,
        "name": "Product B",
        "price": 200,
        "creatorId": 1
      }
    ]
  }
  ```

### 2. GET /products/{id}
- **기능**: 특정 ID에 해당하는 제품 정보를 조회합니다.
- **예시 Input**: 없음
- **예시 Output (성공)**:
  ```json
  {
    "status": "success",
    "data": {
      "id": 1,
      "name": "Product A",
      "price": 100,
      "creatorId": 1
    }
  }
  ```
- **예시 Output (에러)**:
  ```json
  {
    "status": "error",
    "reason": "Product not found"
  }
  ```

### 3. POST /products
- **기능**: 새로운 제품을 추가합니다. 제품 ID는 자동 증가하며, 요청 헤더에 사용자 정보를 포함해야 합니다.
- **헤더**:
  ```json
  {
    "X-User-Id": 1
  }
  ```
- **예시 Input**:
  ```json
  {
    "name": "Product C",
    "price": 150
  }
  ```
- **예시 Output**:
  ```json
  {
    "status": "success",
    "data": {
      "id": 3,
      "name": "Product C",
      "price": 150,
      "creatorId": 1
    }
  }
  ```

## 기술 스택
- **언어**: Java 17
- **프레임워크**: Spring Boot 3.55
- **빌드 도구**: Gradle

## 빌드 방법
1. 프로젝트 클론:
   ```bash
   git clone <repository-url>
   ```
2. 디렉토리 이동:
   ```bash
   cd apigateway-demo/product
   ```
3. 빌드 및 실행:
   ```bash
   ./gradlew bootRun
   ```
