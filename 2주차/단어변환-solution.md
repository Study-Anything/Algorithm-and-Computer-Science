# 단어변환 Solution
작성일: 2023. 04. 25. 이재현

(https://school.programmers.co.kr/learn/courses/30/lessons/43163)

```
문제 설명
두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.

1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
2. words에 있는 단어로만 변환할 수 있습니다.
예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면 "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.

두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.

제한사항
각 단어는 알파벳 소문자로만 이루어져 있습니다.
각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
begin과 target은 같지 않습니다.
변환할 수 없는 경우에는 0를 return 합니다.
입출력 예
begin	target	words	return
"hit"	"cog"	["hot", "dot", "dog", "lot", "log", "cog"]	4
"hit"	"cog"	["hot", "dot", "dog", "lot", "log"]	0
```

문제를 간단하게 설명하면,

문자열 begin 을 target 으로 바꿀 수 있는 최소 변환 횟수를 구해야 한다.

한 번에 AAA -> BBB 으로 변환할 수는 없고,

1회 변환할 수 있는 조건은

'하나의 알파벳만 다를 때' 이다.

그러면, 이렇게 생각하면 어떨까..?

words 에 존재하는 각각의 문자열을 노드로 생각하고,

root 노드는 begin 으로 하자.

노드 연결은, 예를 들어 str1, str2 가 서로 단 하나의 알파벳만 다를 경우에만 해준다면

아래 그림과 같이 문제 상황을 정리할 수 있다.

![image](https://user-images.githubusercontent.com/96612168/234156011-417f4ee4-4f9c-4ef7-8f5c-d6c8e504985e.png)


이제 문제는 begin 노드에서 target 노드로 도달할 수 있는 최단 경로를 출력하는 것으로 치환되었다.

위 그림을 보고 dfs 를 이용하여 구현할 수 있을 것이다.

(주의) 도달할 수 없는 경우에는 0 을 출력한다.







