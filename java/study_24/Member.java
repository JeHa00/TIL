package study_24;

public class Member {
    String name;
    int age;
    int grade; 
    
    // 생성자를 생성
    // 생성자의 이름은 class 이름과 동일해야 한다. 
    Member(String name, int age, int grade) {
        System.out.println("생성자 호출 name = " + name + ", age = " + age + ", grade = " + grade);
        this.name = name;
        this.age = age; 
        this.grade = grade;
    }
}
