# StringTokenizer 클래스란?

**StringTokenizer**는 문자열을 구분자를 이용하여 분리할 때 사용하는 클래스

토큰은 분리된 문자열 조각으로 하나의 문자열을 여러 개의 토큰으로 분리하는 클래스다.

주로 **BufferReader**와 함께 사용

⇒ BufferReader 클래스 메서드로 입력을 받아들이면 라인 단위로 받기 때문에

문자열을 스페이스, 컴마 혹은 공백을 기준으로 분리하거나 특정 문자에 따라 문자열을 나누고 싶을 때 사용한다.

### 생성자

```java
// default : 띄어 쓰기를 기준으로 문자열을 분리
StringTokenizer st = new StringTTokenizer(문자열);

// 구분자를 기준으로 문자열 분리
StringTokenizer st = new StringTokenizer(문자열, 구분자);

// true : 구분자를 기준으로 문자열을 분리하는데 구분자를 토큰으로 추가
// false : 구분자를 분리된 문자열 토큰에 포함시키지 않는다.
// default : false
StringTokenizer st = new StringTokenizer(문자열, 구분자, true/false);

```

### 예시 코드

```java
import java.util.StringTokenizer; 
public class Main {    
    public static void main(String[] args)  {
        String str = "안녕하세요 개발자 지망생입니다.";
        StringTokenizer st = new StringTokenizer(str);
        
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}

/* 결과값 : 
안녕하세요
개발자
지망생입니다.
*/
```

### 구분자가 하나가 아닌 여러 문자라면?

분리하고 싶은 구분자가 여러 개 있을 경우, 단순하게 둘 다 적으면 된다.

⇒ 여러 개의 구분자를 토큰으로 만들 수 있다.

```jsx
import java.util.StringTokenizer; 
public class Main {    
    public static void main(String[] args)  {
        String str = "안녕하세요 !개발자 @지망생입니다.";
        StringTokenizer st = new StringTokenizer(str,"!@");
        
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}

/* 결과값 : 
안녕하세요
개발자
지망생입니다.
*/
```

## 메서드 종류

- **`hasMoreTokens()`** : 남아 있는 토큰이 존재하면 true 리턴, 더 이상 없으면 false 리턴
- **`nextToken()`** : 객체에서 다음 토큰을 반환
- **`hasMoreElements()`** : hasMoreTokens와 동일한데 엘리먼트보다 토큰으로 된 메서드를 주로 사용
- **`nextElement()`** : nextToken 메서드와 동일하지만 문자열이 아닌 객체를 리턴
- **`countTokens()`** : 총 토큰의 개수를 리턴

### split과의 차이점

split과 StringTTokenizer 모두 문자열 파싱에 사용되는데, StringTokenizer는 문자 또는 문자열로 문자열을 구분하지만 split은 정규표현식으로 구분한다.

그리고 StringTokenizer는 빈 문자열을 토큰으로 인식하지 않지만 split은 토큰으로 인식한다.

StringTokenizer는 결과값이 문자열이라면 split은 문자열 배열이다.

그래서 StringTokenizer를 이용할 경우 전체 토큰을 보고 싶으면 반복문으로 하나하나 뽑아 보는 수 밖에 없다.

이런 차이점으로는 StringTokenizer를 왜 써야 하는지 의문이 들 수 있다. 

여기에 대표적인 이유로는 성능 향상이다.

StringTokenizer를 사용하면 split을 사용하는 것보다 속도가 훨씬 빨리지는 점을 볼 수 있다.

> https://dev-coco.tistory.com/94
>