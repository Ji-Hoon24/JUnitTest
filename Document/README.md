# 중요포인트

## 테스트 코드 작성 원칙
- [F]ast - 빠른
- [I]solated - 고립된
- [R]epeatable - 반복 가능한
- [S]elf-validating - 스스로 검증 가능한
- [T]imely - 적시의

## 무엇을 테스트할 것인가?
- Right - 결과가 올바른가?
- B - 경계조건은 맞는가?
- I - 역 관계를 검사할 수 있는가?
- C - 다른 수단을 활용하여 교차 검사 할 수 있는가?
- E - 오류 조건을 강제로 일어나게 할 수 있는가?
- P - 성능 조건은 기준에 부합하는가?

### 경계 조건에서는 CORRECT를 기억하라
- [C]onformance - 값이 기대한 양식을 `준수`하고 있는가?
- [O]rdering - 값의 집합이 적절하게 `정렬`되거나 `정렬`되지 않났나?
- [R]ange - 이성적인 최솟값과 최댓값 `범위` 안에 있는가?
- [R]eference - 코드 자체에서 통제할 수 없는 어떤 외부 `참조`를 포함하고 있는가?
- [E]xistence - 값이 `존재`하는가(non-null, non-zero, 집합에 존재하는가 등)?
- [C]ardinality - 정확히 충분한 값들이 있는가?(`기수`)
- [T]ime - 모든 것이 순서대로 일어나는가? 정확한 `시간`에? 정시에?