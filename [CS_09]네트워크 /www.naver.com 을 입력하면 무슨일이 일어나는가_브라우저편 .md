# www.naver.com 을 입력했을 때무슨일이? (브라우저 편)

Created by: 이재현
Created time: 2023년 7월 30일 오전 12:46

## Q. [WWW.NAVER.COM](http://WWW.NAVER.COM) 을 입력하면 무슨 일이 일어나나요? (#1 브라우저 편)

---

![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled.png)

## `Step1. Handling Input: URL 창에 text 입력`

- 브라우저 프로세스 안에 위치한, 주소 입력창은 UI Thread 가 담당한다.
- 우리가 입력한 text 가 검색어(search query)인지 URL 인지  UI Thread 가 판단한다.
    - 검색어이면 검색 엔진에 해당 검색어를 보내서 검색할 준비를 한다.
    - URL 이면 network thread 로 URL 값을 전달할 준비를 한다.

![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled%201.png)

## `Step2. Start Navigation: enter 키 입력!`

- UI thread 가 네트워크 호출(network thread 에게 URL 을 전달한다.)을 시작한다.
    - **질문) 왜 Network Thread 가 아닌 UI Thread 가 네트워크 호출을 시작하는 역할을 맡는가?**
    - **정답보기**
        
        ![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled%202.png)
        
        검색창 좌측에 보면 돌아가는 파란 원이 있다.
        이러한 UI 작업은 UI Thread 가 담당하는데, 사용자에게 네트워크 호출 상황에 따른 UI 를 보여주기 위해 UI Thread 에게 네트워크 호출 역할을 맡긴 것이다!
        
- Network Thread 는 protocol 을 활용하여 DNS 에 연결한다.
    - HTTP 301(redirect) response 가 전달되면 UI Thread 로 redirect 한다.
        - 그러면 UI thread 는 다른 network 를 호출하려 시도한다.
    - redirect response 가 아니라면 다음 Step 으로!

![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled%203.png)

## `Step 3. Read Response: response body 가 Network thread 로 들어오는 경우`

- 필요에 따라 Network thread 는 response 의 데이터 스트림을 읽는다.
    - response header 에서 content-type 을 확인한다.
        
        ![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled%204.png)
        
        - HTML 형식이면 Renderer process 에게 해당 파일을 전달할 준비를 한다.
        - HTML 이 아니면 Download manager 에게 파일을 전달할 준비를 한다.
            
            ![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled%205.png)
            
- **SafeBrowsing**
    - 해당 domain 혹인 data 가 의심스러운 경우에는 경고 페이지를 보여준다.

## `Step 4. Find a renderer process: 해당 domain / data 가 안전하다고 판단한 뒤, 해당 사이트로 이동하려고 함`

- 이전 단계에서 Network Thread 가 `data is ready` 라고 UI Thread 에게 전달함.
- UI Thread 는 Renderer process 에게 data (html file) 을 전달함.
    - **질문) 일반적으로 Network request 는 대략 수백 ms 정도 소모될 수 있다. 그럼 해당 단계에서 UI Thread 가 Renderer process 에게 올바른 data 를 전달하기 위해 Renderer process 를 찾는 시간은 항상 발생하는 것인가?**
    - **정답보기**
        
        아니다! 그렇게 되면 사용자 입장에서 너무 답답함.
        이전 단계를 생각해보자. UI Thread 는 Step 2. 에서 Network Thread 에게 target URL 을 전달한 뒤 Step 4. 까지 오는 동안(Network Thread 가 작업하는 동안) 미리 Renderer process 를 찾는다. 그렇게 해서 최대한 delay 를 줄이는 것.
        

![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled%206.png)

## `Step 5. Commit Navigation: data, Renderer process 가 준비됨`

- HTML 파일은 Browser Process (내부의 UI Thread)가 가지고 있고, 이를 렌더링하기 위해서는 Renderer Process 에게 HTML 을 전달해주어야 한다.
    - 프로세스 → 프로세스 간 통신이 발생한다.
    이는 **`IPC ( Inter Process Communication )`** 라고 한다. ****

![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled%207.png)

- **********질문)********** 프로세스와 프로그램, 그리고 쓰레드의 차이점이 무엇인가요?
- **정답보기**
    
      간단하게 이야기 하자면, OS 로부터 메모리를 할당받아서 실행중인 프로그램을 프로세스라고 하며, 프로세스 내부에서 병렬적으로 동작하는 실행흐름 단위를 쓰레드라고 한다.
      프로세스는 프로세스마다 메모리를 할당받고, 쓰레드는 메모리를 공유한다. 
    
    - ************************************************************추가 질문) 크롬 브라우저의 각각의 Tab 은 프로세스일까요 쓰레드일까요?************************************************************
        
        크롬 브라우저는 멀티 프로세스 아키텍쳐로 설계된 브라우저로, 각각의 탭 마다 Renderer Process 가 존재한다. 하나의 탭에서 갑자기 응답없음 오류가 발생하면, 해당 탭을 완전히 종료했다가 다시 켜본 경험이 다들 있을텐데 다른 탭들이 영향을 받던가요..?
        → **각각의 탭은 프로세스이다.**
        
- 멀티 프로세스 환경에서는 여러 프로세스가 동시에 존재하는데, 각각은 독립적인 메모리를 할당받으므로 데이터를 전달하기 위해서는 IPC 를 활용한다.

![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled%208.png)

- 커널이 제공하는 API 를 이용해서, 송신 프로세스는 Message Queue 에 enqueue 하고 수신 프로세스는 Message Queue 에 dequeue 해서 통신을 수행할 수 있다.
- 혹은 프로세스 간 특정 공통 메모리 영역을 공유하도록 하여 상호 통신을 수행하는 방법도 있다.
    
    ![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled%209.png)
    

- 여튼 UI Thread 가 Renderer Process 에게 HTML file 을 IPC 를 통해 전달을 한다.
    
    ![Untitled](www%20naver%20com%20%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%8B%E1%85%B5%E1%86%B8%E1%84%85%E1%85%A7%E1%86%A8%E1%84%92%E1%85%A2%E1%86%BB%E1%84%8B%E1%85%B3%E1%86%AF%20%E1%84%84%E1%85%A2%E1%84%86%E1%85%AE%E1%84%89%E1%85%B3%E1%86%AB%E1%84%8B%E1%85%B5%E1%86%AF%E1%84%8B%E1%85%B5%20(%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%20a8e5e1e3f7f34f24ac38e9e134357ff4/Untitled%2010.png)
    
- 그러면 Renderer Process 는 Browser Rendering 을 수행한다.
    - **브라우저 렌더링이란?**

`**후속 주제**`

- [www.naver.com](http://www.naver.com) 을 입력하면 일어나는 일 (**#2 서버 관점)**

---

## Reference

[Inside look at modern web browser (part 2) - Chrome Developers](https://developer.chrome.com/blog/inside-browser-part2/)

[CS 지식의 정석 | 디자인패턴 네트워크 운영체제 데이터베이스 자료구조 - 인프런 | 강의](https://www.inflearn.com/course/개발자-면접-cs-특강/dashboard)

[브라우저에 URL을 입력하면 어떤 과정이 진행될까?](https://www.youtube.com/watch?v=ipwfEUslfQA)
