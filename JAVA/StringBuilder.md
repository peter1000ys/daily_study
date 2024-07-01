# StringBuilder

자바에서 String 객체끼리 더하는 방식을 이용하여 문장을 만들 수 있다. 하지만 덧셈 연산이 많아진다면 이는 코드 가독성이 떨어지고 성능적으로도 메모리 할당과 해제를 일으켜 좋지 않다.

많은 문자열을 연결하면 많은 중간 문자열 객체가 생성되어 비효율적인 코드가 생성된다. 

이는 자바에서 String 객체가 변경 불가이기 때문에 하나의 문자열을 다른 문자열과 연결하면 새 문자열이 생성되고 이전 문자열은 garbage collector에 들어간다. 

이를 수많은 문져열을 연결하는 작업에서 생각하면 메모리 낭비가 매우 심하다.

이를 방지하기 위해 사용하는 것이 바로 StringBuilder다.

StringBuilder는 String과는 달리 변경 가능한 문자열을 만들어 주기 때문에 위 문제를 해결해준다.

## 사용 방법

1. `StringBuilder stringBuilder = new StringBuilder();` StringBuilder 객체 생성
2. `.append(”추가하고 싶은 문자열”)`를 통해 원하는 문자열을 추가
3. String 변수에 넣을 때나 출력 시에는 toString()을 붙여야 한다

```java
public class Main {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("내가 나를 믿자").append(" 할 수 있다 화이팅");
        String str = stringBuilder.toString();
        System.out.println(str);
        System.out.println(stringBuilder);
    }
}

// 결과 값 :
// 내가 나를 믿자 할 수 있다 화이팅
// 내가 나를 믿자 할 수 있다 화이팅
```

## 주요 메서드

- **`.append()`**  문자열을 추가
- **`.insert(int offset, String string)`** offset 위치에 string을 추가한다.
- **`.replace(int idx1, int idx2, String str)`** idx1과 idx2 사이 인덱스 위치에 있는 문자열을 str으로 대체한다.
- **`.reverse()`**  해당 문자열을 뒤집는다.
- **`.substring(int start, (int end))`** start만 존재하면 해당 인덱스부터 끝까지, end도 존재하면 start부터 end-1까지 슬라이싱 해준다.
- **`.deleteCharAt(int index)`**: 인덱스에 위치한 문자 하나를 삭제한다.
- `.**delete(int start, int end)**`: start 부터 end-1 까지의 문자를 삭제
- **`.toString()`**: String으로 변환한다.
- **`.setCharAt(int index, String s)`**: index 위치의 문자를 s로 변경
- **`.setLength(int len)`**: 문자열 길이 조정, 현재 문자열보다 길게 조정하면 공백으로 채워짐, 현재 문자열보다 짧게 조정하면 나머지 문자는 삭제

> https://onlyfor-me-blog.tistory.com/317
> 

> https://www.codejava.net/java-core/the-java-language/why-use-stringbuffer-and-stringbuilder-in-java
> 

> https://da2uns2.tistory.com/entry/Java-StringBuilder-사용법과-주요-메소드
>