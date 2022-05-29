

# 6. Allocation of File data in disk

> disk에 균일하지 않은 크기의 file을 저장할 때는 disk를 균일한 크기로 쪼갠 sector 단위로 저장한다.

- **disk에 file을 저장하는 방식 3가지 (paging 기법과 유사)**

  - Contiguous Allocation (연속 할당)
  - Linked Allocation (연결 할당)
  - Indexed Allocation (인덱스를 이용한 할당)

<br>

## 6.1 Contiguous Allocation

> **_하나의 file이 disk 상에 연속해서 sector 단위로 저장하는 것으로, 나누어진 각 블록들이 연속된 번호를 부여받아 저장된다._**

![image](https://user-images.githubusercontent.com/78094972/170855629-77feddc0-8a48-4373-b8f3-5d64f26b96b5.PNG)

- **directory를 설명하자면**

  - 'count' file은 0부터 시작해서 총 2개의 sector를 차지한다.
  - 'tr' file은 14부터 시작해서 총 3개의 sector를 차지한다.

- **장점**
  - Fast I/O (시간 효율성 증가)
    - file이 모두 연속해서 붙어 있으므로 한 번의 seek / rotation으로 많은 양의 데이터를 받을 수 있다.  
        - 대부분의 접근 시간은 header가 움직이면서 읽어들이는 시간이기 때문에, 시간 효율성이 증가한다.     
    - Realtime file 용 또는 이미 run 중이던 process의 swapping 용도로 사용된다.
  - Direct access (=random access) 가능
    - 몇 번 sector부터 시작하는지 directory가 알기 때문에 가능하다.

- **단점**

  - External fragmentation (hole 발생)
  - File grow가 어렵다. file grow를 해도 낭비 발생 (internal fragmentation)
    - file이 커져서 sector를 늘려 저장하려할 때, 다른 file이 옆 sector를 차지하고 있다면 늘릴 수 없다. 그래서 file이 커질 것을 대비하여 미리 할당했을지라도, 할당된 만큼만 커질 수 있다는 단점이 있다.
    - 그리고 미리 할당된거면서 사용하지 않는 조각이기 때문에, 내부 조각이 발생된다.

<br>

## 6.2 Linked Allocation

> **_sector들이 각각 node되어 linked list 구조를 취하면서 파일을 저장한다._**

- 장점
    - 외부 단편화가 발생하지 않는다.  

- 단점
    - 첫 요소부터 차례대로 읽어야하므로, 임의 접근이 불가능하다.  
    - 신뢰성 문제
        - 한 sector가 고장나 pointer가 유실되면 그 이후 모든 sector로 접근할 수 없다.  
    - 포인터를 위한 공간이 필요하므로 공간 효율성을 떨어뜨린다.  

- 변형
    - File-Allocation Table (FAT) file system  
        - pointer를 별도의 위치에 보관하여 신뢰성 무제와 공간 효율성 문제를 해결한다.  


<br>

## 6.3 Indexed Allocation

- file이 어디에 나눠져 있는지 index를 적어 두는 block 하나를 활용하고, 이를 index block이라 부른다.  

- 장점
    - External fragmentation(외부 조각) 발생 X
    - 임의 접근이 가능하다.  
- 단점  
    - 작은 파일의 경우, 공간 낭비가 심하며 실제로 많은 파일들이 사이즈가 작다.  
    - 매우 큰 파일의 경우, 하나의 index block으로 커버할 수 없다.  
        - 해결 방안: linked scheme, multi-level index  
        - 전자는 index block을 여러 개 두는 것이고, 후자는 block의 마지막에 다음 index 블록을 가

