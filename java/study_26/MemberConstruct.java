package study_26;

public class MemberConstruct {
    String name;
    int age;
    int grade;

    // this() 를 사용하여 코드 중복을 방지한다. 해당 매개변수와 타입에 맞는 생성자에게 전달  
    // 단 this()는 해당 블럭의 맨 첫줄에만 작성가능
    MemberConstruct(String name, int age) {
        this(name, age, 50); // 변경 
        // System.out.println("go");
        // this(name, age, 50); 이런 방식은 인식 x
        

    }

    MemberConstruct(String name, int age, int grade) {
        System.out.println("생성자 호출 name=" + name + ",age=" + age + ",grade=" +
        grade);
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
    
}
