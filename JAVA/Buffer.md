# Buffer

스캐너 보다 몇 배는 빠른 입력 속도를 자랑한다.

## Buffer란?

Buffer ⇒ 임시 저장 공간

속도가 빠른 장치에서 들어오는 모든 데이터를 잠시 저장해놓고 버퍼가 꽉 차거나, 특정 커맨드를 입력 받았을 때 일괄적으로 내보내고 버퍼를 초기화.

하드디스크는 속도가 느리다. 그리고 외부장치와 데이터 입출력도 시간이 꽤 걸린다. 

그러니 속도가 느린 장치에서 일을 여러번 하지 않도록 속도가 빠른 장치에서 미리미리 처리해두고 버퍼라는 임시 공간에 보관 해두었다가 한번에 넘겨 주는 것

버퍼가 없는 동작은 입력을 받자마자 바로 가져가는 방식

버퍼가 있는 동작은 입력을 모았다가 한번에 가져 가는 것

## Buffer와 캐시 비교

캐시와 비슷함 ⇒ 속도 차이가 있는 두 장치 사이에서 그 차이로 인한 문제를 해결하기 위해 사용

차이점 ⇒ 데이터를 사용 후에 폐기하냐 안하냐의 차이

---

## BufferReader

### Scanner vs BufferReader

1. **Scanner**는 **띄어쓰기**와 **개행문자**를 경계로 하여 입력 값을 인식하는 반면, **BufferReader**는 **개행 문자**만 경계로 인식한다.
2. **Scanner**는 **따로 가공할 필요가 없다**. 반면 **BufferReader**는 타입이 **String으로 고정**되기 때문에 따로 데이터를 가공해야 한다.

위의 차이점들만 보면 굳이 BufferReader를 사용해야 하는지 의문이 들 것이다.

하지만 가장 큰 차이점이 남아 있기에 BufferReader를 선택할 수 밖에 없다.

바로 **속도 차이**다.

Scanner는 버퍼 사이즈가 1024 char이기 떄문에 많은 입력이 필요한 경우 성능상 좋지 못한 결과가 나온다.

그에 비해 BufferReader는 8192char로 입력이 많을 때 유리하다.

또한 BufferReader는 동기화가 되기 때문에 멀티 쓰레드 환경에서 안전한데 Scanner는 동기화 되지 않기 때문에 멀티 쓰레드 환경에서 안전하지 않다.

### 사용법

- **import**

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
```

```java
// 선언
BufferReader br = new BufferReader(new InputStreamReader(System.in));
// 입력
String s = br.readline();
// String으로 리턴 값이 고정이기 때문에
// 다른 타입으로 입력 받고 싶으면 반드시 형변환 필수
int i = Integer.parseInt(br.readLine());
```

- 입력 받은 데이터는 개행문자 단위로 나누어진다.
- 이 데이터를 공백 단위로 가공하고 싶으면 StringTokenizer나 String.split()함수를 사용한다.

### 메서드

- **close()** : 입력 스트림을 닫고, 사용하던 자원을 해제 (타입 : void)
- **mark(int, readAheadLimit)** : 스트림의 현재 위치를 마킹 (타입 : void)
- **read()** : 한 글자만 읽어 정수형으로 반환 (타입 : int)
- **readLine()** : 한 줄을 읽음 ( 타입 : String)
- **ready()** : 입력 스트림이 사용할 준비가 되었는지 확인

---

## BufferedWriter

### System.out.println(””) vs BufferWritter

평소에 쓰던 System.out.println(””);을 사용하면 적은 양을 출력할 때는 편하지만, 많은 출력의 경우 버퍼를 사용하는게 더 효과적이다.

### 사용법

```java
// 선언
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
String str = "abcdef"; // 출력할 문자열
bw.write(str); // 출력
bw.newLine(); // 줄바꿈
bw.flush(); // 남아있는 데이터 모두 출력
bw.close();
```

- BufferWritter는 System.out.println(””);처럼 출력과 개행을 같이 해주지 않기 때문에 개행을 하려면 따로 newLine() 또는 write(”\n”)을 사용한다.
- 그리고 버퍼를 잡아 놓는 방식이기 때문에 flush()/close()를 해줘야 한다.

### 메서드

- **close()** : 스트림을 닫음. 닫기 전에 flush() (타입 : void)
- **flush() :** 스트림을 비움, 남아 있는 데이터 모두 출력. (타입 : void)
- **newLine()** : 개행 문자, 줄바꿈 (타입 : void)
- **write(char[] buf, int offset, int length) :** 버퍼 offset 위치부터 length만큼 write
- **write(int c)** : 한글자 쓰기
- **write(String s, int offset, int length)** : 문자열에서 offset에서부터 일정 길이만큼 write

---

## StringBuffer

문자열을 추가하거나 변경할 때 주로 사용하는 자료형

StringBuilder는 동기화가 안되어서 멀티쓰레드에서 사용 불가능한 반면 StringBuffer는 동기화가 가능하여 멀티쓰레드에서 활용 가능하다.

## 메서드

### append

append를 사용하면 문자열을 계속해서 추가해 나갈 수 있다.

toString() 메서드를 사용하면 StringBuffer를 String 자료형으로 변경 가능

```java
StringBuffer sb = new StringBuffer();  // StringBuffer 객체 sb 생성
sb.append("hello");
sb.append(" ");
sb.append("jump to java");
String result = sb.toString();
System.out.println(result);  // "hello jump to java" 출력

// 결과
// hello jump to java
```

### insert

원하는 인덱스 위치에 문자열을 삽입할 수 있음.

```java
StringBuffer sb = new StringBuffer();
sb.append("jump to java");
sb.insert(0, "hello ");
System.out.println(sb.toString());

// 결과
// hello jump to java
```

### substring(시작 위치, 끝 위치)

StringBuffer 객체의 시작 위치에서 끝 위치까지의 문자를 뽑아냄

```java
StringBuffer sb = new StringBuffer();
sb.append("Hello jump to java");
System.out.println(sb.substring(0, 4));

// 결과
// Hell
```

추천 문제

https://www.acmicpc.net/problem/15552

> https://rlakuku-program.tistory.com/33
>