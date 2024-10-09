package study_29;

public class ConstructInit {
    static final int CONST_VALUE = 10;
    final int value; // final로 인스턴스 변수 선언

    public ConstructInit(int value) {
        this.value = value; // final 변수 초기화
    }
}


// 인스턴스마다 다른 값으로 초기화된다면 위와 같은 방식은 괜찮지만, 인스턴스마다 동일한 값으로 선언된다면 정적 변수로 사용하는 게 메모리 사용 관점에서 효율적이다.  