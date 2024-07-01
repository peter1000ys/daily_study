# ArrayList란?

자바의 List 인터페이스를 상속 받은 여러 클래스 중 하나이다. 

일반 배열과 동일하게 연속된 공간을 사용하며 인덱스는 0부터 시작한다.

하지만 배열과 달리 크기가 고정되어 있지 않고 가변적으로 변한다.

주로 배열의 크기가 정해져 있지 않을 때 사용하기 좋다.

사용 하기 전 먼저 ArrayList를 import 해와야한다.

```java
import java.util.ArrayList;
```

생성자 :

```java
ArrayList<Integer> integers1 = new ArrayList<Integer>();
// 타입 생략이 가능
ArrayList<Integer> integers2 = new ArrayList<>();
```

### 사용 메서드

- `.add()`  : 요소 추가, 기본적으로 리스트의 가장 끝에 값을 추가

```java
colors.add("Black");
```

- `.set(int idx, String str)` : idx의 요소를 str으로 변경

```java
 colors.set(0, "Blue");
```

- `.remove()` : 삭제 메서드, 삭제할 요소의 인덱스나 삭제할 요소를 직접 입력할 수 있다. 삭제되는 요소를 return 받을 수 있다.

```java
colors.remove("White");
```

- `.clear()` : 리스트 전체 삭제

```java
colors.clear();
```

- `.get()` : 특정 인덱스의 값을 가져오기 위한 메서드

```java
colors.get(1)
```

- `.contains`  : 값이 존재하는 경우 true, 없으면 false 반환

```java
colors.contains("Black");
```

- `.indexOf()` : 값이 존재하는 경우 해당 요소의 인덱스를 반환, 값이 존재하지 않으면 -1을 반환

```java
colors.indexOf("Blue")
```

- `Collections.reverse(arrayList)` : 배열을 거꾸로 뒤집기 위해 사용
import java.util.Collections;로 Collections를 받아와야 한다

```java
import java.util.Collections;

ArrayList<String> list = new ArrayList<>(Arrays.asList("H", "e", "l", "l", "o"));
Collections.reverse(list);
```

> https://psychoria.tistory.com/765
> 

> https://hianna.tistory.com/570
>