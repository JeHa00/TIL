package study_23;

public class MemberInit {
    String name;
    int age;
    int grade;

    // this는 인스턴스 자신을 가리키는 참조값  
    void initMember(String name, int age, int grade) {
        this.name = name; // 멤버변수와 매개변수의 이름이 동일할 때, 이를 구분하기 위해서 사용
        this.age = age;
        this.grade = grade;
    }
}

