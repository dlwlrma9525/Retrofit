# REST API
![](http://dl.dropbox.com/s/1r6wlhma4oyuuza/REST%20API.png)

REST는 Representational State Transfer의 약자로, World Wide Web과 같은      
분산 하이퍼미디어 시스템에서 운영되는 **HTTP 기반 소프트웨어 아키텍처 스타일**  

--------------------------------------------------

## 로이 필딩 (Roy Fielding)
HTTP/1.0, 1.1 스펙 작성에 참여한 주요 저자 그리고 Apache HTTP Server 프로젝트의 공동설립자  

REST는 2000년도에 로이 필딩 (Roy Fielding)의 박사학위 논문에서 최초로 발표.      
**Web(HTTP) 설계의 우수성, 장점을 최대한 활용할 수 있는 아키텍처** 로써 REST 발표.  

REST는 HTTP/1.1 스펙과 동시에 만들어졌는데, **HTTP Protocol을 정확히 의도에 맞게 활용한 디자인**     
기반으로 디자인 기준의 명확성, 의미적인 범용성을 지니므로 중간 계층 컴포넌트의 서비스 최적화에 도움.      
**REST의 기본 원칙을 성실히 지킨 서비스 디자인은 RESTful 하다. 라고 흔히 표현.**  

--------------------------------------------------

## REST 구성

**자원(Resource)** → URI      
**행위(Verb)** → HTTP Method      
**표현(Representations)**  

--------------------------------------------------

## REST 특징

**Uniform (유니폼 인터페이스)**    
Uniform Interface는 URI로 지정한 리소스에 대한 조작을 표준화하고       
한정적인 인터페이스로 수행하는 아키텍처 스타일.

**Stateless (무상태성)**      
REST는 작업을 위한 상태정보를 따로 저장하고 관리하지 않는다.   
**서비스의 자유도 상승, API 구현의 단순화**        
세션정보나 쿠키정보를 따로 저장하고 관리하지 않아서 API Server는 들어오는 요청만 처리하면 됨.

**Cacheable (캐시가능)**    
REST는 HTTP Protocol을 그대로 사용하므로 Web이 사용하는 기존 인프라를 그대로 활용 가능.      
따라서, HTTP가 가진 캐싱 기능이 적용.    
HTTP Protocol 표준에서 사용하는 Last-Modified Tag or E-Tag를 이용하면 캐싱 구현이 가능.  

**Self-descriptiveness (자체 표현 구조)**  
REST는 REST API Message 만 보고도 이를 쉽게 이해할 수 있는 자체 표현 구조로 구성.

**Client - Server 구조**  
REST Server는 API 제공, Client는 사용자 인증, Context(세션, 로그인 정보..)등을 직접 관리하는 구조로    
각각의 역활이 확실히 구분되므로 Client와 Server의 개발 내용이 명확해지고 서로간의 의존성 감소.

**계층형 구조**  
REST Server는 다중 계층으로 구성될 수 있으며 보안, 로드 밸런싱, 암호화 계층을 추가하여       
구조상의 유연성을 추구하고 Proxy, Gateway 같은 네트워크 기반의 중간 매체를 사용할 수 있도록 한다.

--------------------------------------------------

## REST API 디자인 가이드

**첫 번째**, URI는 정보의 리소스(주소)만을 표현해야 한다.  
**두 번째**, 리소스에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE)로 표현한다.

--------------------------------------------------

## URI
**Uniform Resource Identifier (URI)는 리소스를 식별하는데 사용되는 문자열**      
URI는 특정 Protocol을 사용하는 네트워크 (일반적으로 World Wide Web) 기반       
리소스의 주소 표현과 데이터 교환을 가능하게 한다.

**URI의 URL, URN**  
URN은 사람의 이름으로 비유할 수 있으며 URL은 주소로 비유할 수 있다.   
URN은 리소스을 식별하고 URL은 리소스를 찾기 위한 주소와 방법을 표현한다.

## URL
**Web 상에 수많은 정보가 (웹페이지, 이미지, 동영상..) 위치하는 유니크한 주소 정보를 나타내는 URI**  
URI의 가장 일반적인 형태는 URL (Uniform Resource Locator, Web Address).    
URL은 단순히 네트워크를 통해 리소스를 가리키는 URI

## URN  
**URN은 특정 네임 스페이스의 리소스를 식별하는 메커니즘을 제공하여 URL을 보완하도록 설계한 URI**    
**URN 은 리소스의 이름을 지정하는 URI**  

URN은 리소스에 대해 세계적으로 유일하고 영구적인 식별자 지정.   
예로, URN urn:isbn:0201896834은 Donald Knuth의 Computer Porgramming 의 책 1권을 고유하게 식별.

* 책 1권의 이름의 URI → **URN**  
* 책 1권이 있는 도서관 주소의 URI → **URL**  

--------------------------------------------------

## URI Syntax
URI 참조의 문법은 RFC 2396 (1998.08)으로 정의 되었으며, RFC 3986 (2005.01)으로 완성.  
**RFC (Request for Comments)**

```html
scheme : [ // [user:password@] host [:port]] [/] path [?query string] [#fragment (bookmark)]  
```

```html
hierarchical part
┌───────────────────┴─────────────────────┐
     authority               path
┌───────────────┴───────────────┐┌───┴────┐
abc://username:password@example.com:123/path/data?key=value&key2=value2#fragid1
└┬┘   └───────┬───────┘ └────┬────┘ └┬┘           └─────────┬─────────┘ └──┬──┘
scheme  user information     host     port                  query         fragment

urn:example:mammal:monotreme:echidna
└┬┘ └──────────────┬───────────────┘
scheme              path
```

--------------------------------------------------

```html
http://codingeverybody.com/codingeverybody_html_tutorial/url_72/example2.html?mode=view#book
```

|Syntax      |Example                                            |
|------------|---------------------------------------------------|
|Scheme      |http://                                            |
|host        |codingeverybody.com                                |
|path        |/codingeverybody_html_tutorial/url_72/example2.html|
|query String|?mode=view                                         |
|fragment    |#book                                              |

--------------------------------------------------

## Scheme
**통신에 사용되는 방식. Protocol** (디지털 정보통신 시의 정보 체계 및 정의)  
Scheme 뒤의 Path에 해당되는 데이터가 어떤 통신규약에 따라 주고 받아져야 되는지 규정

**```//``` (Two Slash)**   
Authority가 없는 경우, Path는 두 개의 슬래시로 시작할 수 없다.

--------------------------------------------------

## Authority (User + Password + Host + Port)

**Username + Password**  
사용자 이름과 암호의 선택적 인증 섹션. at 기호 ```@```로 구분

**Host**  
리소스가 위치하고 있는 컴퓨터의 식별자 또는 주소

* **IP (Internet Protocol Address)**   
* **Domain Address**  

**Port**   
 Web Server 어플리케이션에 할당된 Port 번호

--------------------------------------------------

## Path (Path + Query String + Fragment)

**Path**  
Hosts의 Root Directory ```/```로 부터 리소스가 위치한 Directory 까지의 경로와 리소스의 파일 이름

**Query String**  
Web Server로 넘기는 추가적인 질문 (Query), Parameter

```html
? mode = view
? VariableName = value & VariableName = value..
```

* HTML WebPage는 ```?```로 전달하는 Query 정보를 식별하여 차등된 다른 정보 출력
* ```?``` Query String의 시작
* ```&``` 다수의 Sequence 표현
* Attribute = Value Pair(쌍) Sequence 구조

**Fragment (Bookmark)**  
WebPage 문서 안에서 북마크를 통해서 특정한 지점으로 링크, 스크롤 시킬 수 있다.  
하이퍼링크 (HyperlInk) 그리고 ```#``` 으로 시작  

--------------------------------------------------

## REST API 중심규칙

#### URI는 정보의 자원을 표현해야 한다. (리소스 이름은 동사보다는 명사로 사용)
```html
GET /member/delete/1
```
위와 같은 방식은 REST를 제대로 적용하지 않은 URI  
**URI는 자원을 표현하는데 중점을 두어야 한다. delete와 같은 행위가 들어가면 안된다.**

#### 자원의 행위는 HTTP Method(GET, POST, PUT, DELETE)로 표현
위의 잘못된 URI를 HTTP Method로 수정하면 다음과 같다.
```html
GET /member/1
```
회원정보를 가져올 때는 GET, 회원 추가 시의 행위를 표현하고자 할 때는 POST Method를 사용하여 표현.

**회원정보를 가져오는 URI**
```html
GET /members/show/1     (x)
GET /members/1          (o)
```

**회원정보을 추가하는 URI**
```html
GET /members/insert/2 (x)  - GET 메서드는 리소스 생성에 맞지 않다.
POST /members/2       (o)
```

## HTTP Method
Web Client와 Web Server가 상호작용 하는 방식  
**Web Client가 Web Serve에게 요청의 목적/종류를 알리는 메소드**

## HTTP 요청 Method의 위치
HTTP 요청 메시지의 첫쨰 줄 (GET /image/logo.gif HTTP/1.1)

## GET
**Web Client가 Web Server에게 정보를 요청해서 가져오는 방식**  
**GET를 통해 해당 리소스를 조회. 리소스를 조회하고 해당 도큐먼트에 대한 자세한 정보를 가져온다**  
URL에 Query String이 보이는 방식

## POST
**Web Client의 정보를 Web Server에게 전달하는 방식**  
**POST를 통해 해당 URI를 요청하면 리소스 생성**  
URL에 Query String, Request Data를 감추는 방식

## PUT
**Web Client가 수정한 정보를 Web Server에게 전달하는 방식**  
**PUT를 통해 해당 리소스를 수정**  
Web Server는 Web Client가 제시한 URL을 그대로 사용

## DELETE
**Web Client가 Web Server에게 해당 URL의 정보 삭제 요청**  
**DELETE를 통해 리소스를 삭제**

--------------------------------------------------

## URI 설계규칙

#### 슬래시 구분자 ```/```는 계층 관계를 나타내는 데 사용
```html
http://restapi.example.com/houses/apartments
http://restapi.example.com/animals/mammals/whales
```

#### URI의 마지막 문자로 슬래시 ```/```를 포함하지 않는다.
URI에 포함되는 모든 글자는 리소스의 유일한 식별자로 사용되어야 하며   
URI가 다르다는 것은 리소스가 다르다는 것이고, 역으로 리소스가 다르면 URI도 달라져야 한다.   
**REST API는 분명한 URI를 만들어 통신** 하기 때문에 혼동을 주지 않도록   
URI 경로의 마지막에는 슬래시 ```/```를 사용하지 않는다.

```html
http://restapi.example.com/houses/apartments/ (X)
http://restapi.example.com/houses/apartments  (0)
```

#### 하이픈 ```-```은 URI 가독성을 높이는데 사용
URI를 쉽게 읽고 해석하기 위해, 긴 URI경로를 사용하게 된다면 하이픈을 사용해 가독성을 높일 수 있다.

#### URI 경로에는 소문자가 적합하다.
URI 경로에 대문자 사용은 피한다. 대소문자에 따라 다른 리소스로 인식하게 되기 때문이다.    
RFC 3986(URI 문법 형식)은 URI Scheme, Host를 제외하고는 대소문자를 구별하도록 규정.

```html
RFC 3986 is the URI (Unified Resource Identifier) Syntax document
```

#### 파일 확장자는 URI에 포함시키지 않는다.
```html
http://restapi.example.com/members/soccer/345/photo.jpg (X)
```
REST API는 메시지 바디 내용의 포맷을 나타내기 위한 파일 확장자를 URI 안에 포함시키지 않는다.   
Accept header를 사용해야 한다.

```html
GET /members/soccer/345/photo HTTP/1.1 Host: restapi.example.com Accept: image/jpg
```

--------------------------------------------------

## 리소스 간의 관계표현
**REST 리소스 사이는 연관 관계가 있을 수 있고, 이 경우 다음과 같이 표현.**    
```html
/리소스명/리소스 ID/관계가 있는 다른 리소스명
ex) GET : /users/{userid}/devices (일반적으로 소유 ‘has’의 관계를 표현할 때)
```

**만약에 관계명이 복잡하다면 이를 서브 리소스에 명시적으로 표현.**       
사용자가 ‘좋아하는’ 디바이스 목록을 표현해야 할 경우 다음과 같은 형태로 사용될 수 있다.
```html
GET : /users/{userid}/likes/devices (관계명이 애매하거나 구체적 표현이 필요할 때)
```

--------------------------------------------------

## 자원을 표현하는 Colllection과 Document
Collection과 Document로 URI 설계가 한 층 더 쉬워진다.   
**Document는 단순히 문서, 객체 하나.**     
**Collection은 문서들의 집합, 객체들의 집합.**     
Collection과 Document는 모두 리소스라고 표현할 수 있으며 URI에 표현.

```html
http:// restapi.example.com/sports/soccer
```
sports라는 Collection과 soccer라는 Document로 표현

```html
http:// restapi.example.com/sports/soccer/players/13
```
sports, players Collection과 soccer, 13(13번인 선수)를 의미하는 Document로 URI가 구성.   
여기서 중요한 점은 Collection은 복수로 사용하고 있다는 점.     
좀 더 직관적인 REST API를 위해서는 Collection과 Document 사용 시     
단수 복수도 지켜준다면 좀 더 이해하기 쉬운 URI를 설계할 수 있다.  

--------------------------------------------------

## HTTP 응답 상태 코드
**잘 설계된 REST API는 URI만 잘 설계된 것이 아닌 그 리소스에 대한 응답을 잘 내어주는 것까지 포함.**  
정확한 응답의 상태코드만으로도 많은 정보를 전달할 수가 있으므로 상태코드 값을 명확히 돌려주는 것이 중요.
만약 200이나 4XX관련 특정 코드 정도만 사용하고 있다면    
처리 상태에 대한 좀 더 명확한 상태코드 값을 사용할 수 있기를 권장.

#### 상태코드
|Code|Description                                                                         |
|----|------------------------------------------------------------------------------------|     
|200 |Client의 요청을 정상적으로 수행함
|201 |Client가 어떠한 리소스 생성을 요청, 해당 리소스가 성공적으로 생성됨
|    |(POST를 통한 리소스 생성 작업 시)

#### 상태코드
|Code|Description                                                                         |
|----|------------------------------------------------------------------------------------|     
|400 |Client의 요청이 부적절 할 경우 사용하는 응답 코드
|401 |Client가 인증되지 않은 상태에서 보호된 리소스를 요청했을 때 사용하는 응답 코드
|    |(로그인 하지 않은 유저가 로그인 했을 때, 요청 가능한 리소스를 요청했을 때)
|403 |유저 인증상태와 관계 없이 응답하고 싶지 않은 리소스를 Client가 요청했을 때의 응답 코드
|    |(403 보다는 400이나 404를 사용할 것을 권고. 403 자체가 리소스가 존재한다는 뜻)
|405 |Client가 요청한 리소스에서는 사용 불가능한 Method를 이용했을 경우 사용하는 응답 코드

#### 상태코드
|Code|Description                                                                         |
|----|------------------------------------------------------------------------------------|     
|301 |Client가 요청한 리소스에 대한 URI가 변경 되었을 때 사용하는 응답 코드
|    |(응답 시 Location header에 변경된 URI 명시)
|500 |Server에 문제가 있을 경우 사용하는 응답 코드

--------------------------------------------------

## 참고

* https://opentutorials.org/
* https://ko.wikipedia.org/wiki/REST
* https://en.wikipedia.org/wiki/Uniform_Resource_Identifier
* http://meetup.toast.com/posts/92
* https://spoqa.github.io/2012/02/27/rest-introduction.html

--------------------------------------------------

## Network → Internet
생활코딩 https://opentutorials.org/course/1688/9483

전세계에 있는 컴퓨터들이 네트워크(연결망)를 통해서 연결되어 데이터를 주고받을 수 있는 거대한 네트워크
**Web이 동작하는 플랫폼**
**컴퓨터간 TCP/IP Protocol을 이용해 데이터를 주고받는 네트워크**

--------------------------------------------------

## IP (Internet Protocol)  
**Internet 상의 여러 Host(컴퓨터) 간 패킷 교환 네트워크에서 데이터를 주고받는 데 사용하는 규약**

## IP Address
Internet 상의 여러 Host(컴퓨터) 간 패킷 교환 네트워크에서 **데이터 주고받는 데 필요한 고유 주소**

![](http://dl.dropbox.com/s/5gzyfke45jg4mat/ClientServerIP.png)

**Server와 Client 모두 데이터 교환을 위해 각각 IP 주소가 필요하다.**  
Client가 Server에게 데이터 요청 시, Client의 IP주소와 Server IP주소를 모두 전달한다.

![](http://dl.dropbox.com/s/4zh9ir1rophkgrs/IPv4IPv6.png)

## IPv4
**0.0.0.0 ~ 255.255.255.255**  
대략 42억개의 IP주소 할당 가능, 그러나 인터넷이 폭발적으로 성장하면서    
전세계적으로 컴퓨터, 스마트폰, IoT..와 같은 디바이스에 모두 할당하지 못한다.

## IPv6
**IPv4 주소의 고갈의 근본적인 해결책으로 도입된 IP 주소체계**  
2^128개의 IP주소 할당 가능  
IPv4가 적용된 디바이스에 당장은 적용불가

## 공유기
![](http://dl.dropbox.com/s/iz7ihsgt55nw29z/Router.png)
![](http://dl.dropbox.com/s/ne6okgcawkcb7vr/Wifi.png)

## 공인 IP

**통신사와 계약을 맺어 할당받는 IP주소 하나와 인터넷 회선**  
**전세계에서 유일무이한 IP**  
통신사와 직접 연결하는 회선, 하나의 회선마다 요금 책정  
대표번호에 비유  

![](http://dl.dropbox.com/s/e857d0ktsad78nm/PublicIP.png)

인터넷 회선에 공유기를 접속하면 공유기는 공인 IP를 할당.  
공유기로 접속한 컴퓨터에서 IP를 검색하면 컴퓨터 자신의 IP가 아닌 공유기의 IP가 검색된다.    
(Server 컴퓨터 사이에 공유기가 있으면, 공유기에 접속하게 된다..)

![](http://dl.dropbox.com/s/drm6ovhlug4ojpu/RouterIP.png)

## 사설 IP

![](http://dl.dropbox.com/s/2bpk8ntpdqv9l05/PrivateIP.png)

**하나의 공인 IP가 연결된 공유기 안에서만 할당되는 중복되지 않는 IP주소**
내선번호의 비유

#### 유동 IP (Dynamic Adress)  

![](http://dl.dropbox.com/s/2eb41hmze6oqz27/DynamicIP0.png)  
![](http://dl.dropbox.com/s/u5l6s9tr1ft5rd8/DynamicIP1.png)   

* **통신사가 제공하는 IP 주소가 변화하는 방식**
* **한정적인 IP주소를 운영하는 방식**  
* 인터넷을 사용하는 가정용 공인 IP가 모두 해당
* 컴퓨터에 장시간 네트워킹이 없으면, 통신사는 할당한 공인 IP 회수  
  그리고 새로운 가입자나 다른 컴퓨터에 할당. 기존의 컴퓨터는 네트워킹을 재개하면 새로운 공인 IP 할당
* **그러므로, Web Server를 운영할 때에는 웹 호스팅이나, 통신사로 부터 고정 IP를 할당받아 사용해야 한다.**

--------------------------------------------------

### Domain

![](http://dl.dropbox.com/s/pt7p8lne9tzv89c/DomainName.png)
![](http://dl.dropbox.com/s/hnod30ryl2kzye3/DomainSite.png)

**치명적인 단점인 기억하기 어려운 IP 주소를 도메인 네임으로 표현**
**IP 주소를 사람이 식별하기 쉽도록 문자등으로 변경한 주소**

* 사실, 컴퓨터는 도메인을 통해서 Server에 접속할 수 없고, 오직 IP주소를 통해서만 접속 가능!

### DNS (Domain Name System)

![](http://www.dropbox.com/s/twtud63naicfgsp/DNS.png)

**도메인 네임이 동작하는 시스템**
**도메인 네임과 IP주소를 매칭시키고 관리하는 기술**

* Web Client와 Web Server 사이의 특수한 도메인 네임 Server를 통해 도메인 네임에 매칭된 IP주소 응답
* 도메인 네임은 구매 후 도메인 네임 Server에 등록해야 한다.  

--------------------------------------------------

### Port

![](http://dl.dropbox.com/s/68w2huz19aczl7q/Port.png)

* 한글로 항구, 배가 정박하는 곳
* Client가 특정한 Web Server에 접속했을 때, 정박하는 곳
* **Port가 필요한 이유는 컴퓨터 내에는 다양한 프로그램이 설치되어 있고**  
  **각각의 프로그램들은 인터넷에 연결되어 있기 때문에,**   
  **IP주소 만으로는 컴퓨터만 구분하므로 어떤 프로그램과 통신 해야하는지 구분해야 하기 때문이다.**

```html
http://222.109.62.43:80/index.html
```

![](http://dl.dropbox.com/s/ki1r51ekhydlcs9/PortAddress.png)

* 222.109.62.43 IP 주소를 가진 Web Server에 http 프로토콜로 접속
  (웹 브라우저는 기본적으로 http 프로토콜로 Web에 접근하므로 http:// 생략하여 표현하기도 함)
* 80번 Port를 이용 (Port를 생략하면 설정된 Port로 자동 접속)

![](http://dl.dropbox.com/s/b1abes3bt8pb3uv/PortMySQL.png)
![](http://dl.dropbox.com/s/sr9wys86pmzinb8/PortServer.png)

* **인터넷에 연결된 컴퓨터 안에는 대략 65000개의 가상의 문이 있이 있고 Port로 명칭**
* Port 하나 당 고유한 번호 할당
* 80번 Port로 Web Server에 접속한다는 것은 **80번 Port를 열고 그 안에 설치된 Web Server에 접속한다는 의미**
* Web Server는 기본적으로 80번 Port에 설치

### Port Forwarding
Client가 사설 IP를 할당받은 Web Server에 어떻게 접속할까?
* 공인 IP가 부여된 공유기가 사설 IP를 할당받은 웹Server가 설치된 컴퓨터에게 토스!
* 공유기의 관리자 모드에서 설정 (해당 사설 IP의 컴퓨터의 해당 포트로 Client 접속을 토스)

![](http://dl.dropbox.com/s/xcbomhlvhdieeco/PortFowarding.png)
![](http://dl.dropbox.com/s/wy5800s7r4tl1kc/PortForwardingToss.png)

--------------------------------------------------

![](http://dl.dropbox.com/s/er6y7iiljl8gclg/Path.png)
![](http://dl.dropbox.com/s/jfzhdrq1zsj9tyy/PathURL.png)

### 절대경로
Web Server와는 상관없는 위치의 리소스를 로드하거나, Web Server의 바깥에 있는 링크로 이동 시 사용

* **절대적인 위치 경로**
* **나의 위치와 무관한 위치 경로**
* http://localhost/index.php
* /index.php
  - **```/``` 는 경로 상에서 가장 최상위 디렉토리를 의미**
  - http://localhost/ 디렉토리를 의미

절대 경로의 경우, 경로에 따라 URL의 Host 표현이 바뀔수 있다.  
(접속은 IP주소로 했는데 특정 웹 페이지에서 localhost로 바뀔 수 있다..)

### 상대경로
하나의 Web Server 안에 있는 리소스를 로드하거나, 다른 경로에 있는 링크로 이동 시 사용

* **현재 나의 위치에 따라 달라지는 경로**
* ```../```은 부모 디렉토리 하위의 경로를 의미
* http://localhost/path/1.html 상에서 ../index.php라는 경로는  
  path의 부모 디렉토리(여기서는 루트 디렉토리) 상의 index.php를 의미

-------------------------------------------------
