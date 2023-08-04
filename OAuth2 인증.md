# Oauth2 인증 과정

Created by: 이재현
Created time: 2023년 8월 3일 오후 9:29

### 먼저, `인증` 이란?

- 식별 가능한 정보로, 서비스에 등록된 유저의 신원을 입증하는 과정
    - 사원증을 받은 신입 사원은 회사 건물에 출입할 수 있다.
- 그 신입 사원은 회사 내 모든 시설에 접근이 가능한가?

### 보안 시설은 `접근 권한` 이 있는 사원만 출입가능 하다.

- 이때, 인증된 사용자에 대하여 자원 접근 권한을 확인하는 것을 `**인가**` 라고 말한다.

<aside>
📌 **즉, `인증과 인가` 라는 것은 자원을 적절하고 유효한 사용자에게 전달/공개하기 위한 방법을 말한다.**

</aside>

---

### Web 에서의 `인증(**Authentication**)과 인가(Authorization)` 는..?

1. ****************인증하기 - `Request Header`**
2. ******************************************인증 상태 유지하기 - `Browser`**
3. **안전하게 인증하기 - `Server`**
4. **효율적으로 인증하기 - `Token`**
5. **다른 채널을 통해 인증하기 - `OAuth`**

---

### OAuth (Open Authorization )는,

- 다른 웹 사이트 상의 자신들의 정보에 대해 **`접근 권한을 부여`**할 수 있는 공통적인 수단이자 개방형 표준이다.
    - 인터넷 사용자들이 비밀번호를 제공하지 않고 다른 웹사이트 상의 자신의 정보들에 대해 접근 권한을 부여할 수 있는 공통 수단이다.

![Untitled](Oauth2%20%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A5%E1%86%BC%20e2e4bacbce054c50bf696e89daa60200/Untitled.png)

- 위와 같은 소셜 로그인 자체를 OAuth 라고 부르면 잘못된 것이다.
    - 소셜 로그인들이 사용하는 인증 절차를 **`OAuth`** 라고 한다. ✅
- 초기 버전인 **`OAuth 1.0`** 은 2010 년에 발표되었다!
    - 당시 OAuth1.0 에는 구조적인 문제들이 존재했고 이들을 개선한 `**OAuth2.0`** 이 많이 사용되고 있다.

---

### 주요 키워드들,

![Untitled](Oauth2%20%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A5%E1%86%BC%20e2e4bacbce054c50bf696e89daa60200/Untitled%201.png)

- `**Resource Owner`** 은 서비스를 사용하는 User 을 의미한다.
- **************`Client`** 는 서비스를 의미한다.
- **********************************`Resource Server`** 는 Google, Facebook, ,Twitter 등 OAuth 를 통한 접근을 지원하는 제공자를 의미한다
    - 보다 세분화하면 **`Resource Server`** 와 `**Authorization Server`** 으로 나눌 수 있지만 일단 통칭하여 Resource Server 라고 부르겠다.

---

### 소셜 로그인을 사용했던 경험으로 부터,

- 소셜 계정이 존재한다면 원클릭 만으로 인증, 인가가 이루어진다.
    - 👶 사용자 입장에서는,
    `ID` , `Password` 를 넘겨주지 않아도 된다. ✅
    - 👨‍💻 서비스 입장에서는
    `ID` , `Password` 정보를 보관/관리하려 애쓰지 않아도 된다. ✅

> **즉, OAuth2 는 사용자가 서비스에게 ID, Password 와 같은 개인 정보를 제공하지 않고 원래 가입되어 있던 서비스 제공자의 서비스를 이용할 수 있는 방법을 제공한다.**
> 

### 잠깐, 카카오에서는? (REST API)

![Untitled](Oauth2%20%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%8C%E1%85%B3%E1%86%BC%20%E1%84%80%E1%85%AA%E1%84%8C%E1%85%A5%E1%86%BC%20e2e4bacbce054c50bf696e89daa60200/Untitled%202.png)

### 복잡해보이지만 차근차근 살펴보면,

### `0. Register`

- Client 는 Resource Server 에 등록 과정을 거친다.
    - Client ID
    - Client Secret
    - Authorized redirect URLs
        - 사용자의 인증이 성공했을 경우 해당 URL 으로 redirect 하여 **`Authorization Code(인증 코드)`** 를 클라이언트에게 전달해준다.
        - 이 인가 코드는 Kakao Auth Server 로 접근할 수 있는 Access token 에 대한 교환권과 같은 개념으로 사용된다.
- **Q. 인증 코드를 반환하기 보다 그냥 바로 Access token 을 반환하면 안되나요?**
    - Redirect URI 를 통해 데이터를 전달하는 방법은 URI 에 데이터를 실어서 전달하는 방법 밖에 없는데, 이는 브라우저를 통해 쉽게 노출된다는 위험이 있다.
    
    - Access token 을 바로 반환하면, token 탈취 위험이 증가하므로 인가 코드를 반환한 다음 Backend 를 거쳐서 Access token 을 얻어올 수 있도록 구현하는 것이 권장된다.

### `1. Login`

- Client 가 제공한 소셜 로그인 버튼을 통해 Resource Owner 는 OAuth 과정에 진입한다.
- Auth Server 가 제공한 Login 페이지로부터 사용자는 ID, Password 를 제공한다.
    - Auth Server 내에서 사용자를 인증하고 문제가 없다면 Authorization Code(인증 코드)를 redirect 한다.
- Front 쪽에서 받은 인증 코드를 Backend 로 넘겨준다.

### ******************`2. Issuse Access Token`******************

- Backend 에서는 인증 코드를 Auth Server 로 넘겨주며 Access Token 을 요청한다.
- Auth Server 에서 해당 인증 코드가 유효하다고 판단되면 Access Token 을 Backend 에게 발급해준다.

**이 부분은 구현하기 나름이지만,**

- 받은 Access Token 으로 부터 사용자의 정보를 조회한 다음, 우리 서비스 DB 내에 존재하는 회원인지 확인한다.
    - 존재하지 않는 서비스 신규 회원 가입 절차
    - 존재한다면 서비스 로그인 절차
        - 이 부분에서 Access Token / Refresh Token 과 같은 추가적인 구현 사항을 적용할 수도 있다.
- 이후 자체 Token 을 발급한 뒤 사용자에게 반환해준다.

### `3. Resource request with Token`

- 이제 사용자는 발급받은 Token 을 request Authorization header 필드에 담아서 API 요청을 한다.
- Backend 에서는 Request header 에 Authorization 필드에 들어있는 token 을 꺼내서 사용자에 대한 인증 인가 작업을 거친 뒤 이상이 없으면 API 를 작동시킨다.

---

## References

[[10분 테코톡] 🎡토니의 인증과 인가](https://www.youtube.com/watch?v=y0xMXlOAfss)

[OAuth 2.0 설명](https://wordbe.tistory.com/entry/OAuth-20-설명)

[스프링 시큐리티 인 액션 - 예스24](https://www.yes24.com/Product/Goods/112200347)
