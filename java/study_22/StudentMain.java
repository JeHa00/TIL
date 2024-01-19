package study_22;

public class StudentMain {

    public static void main(String[] args) {
        Student student1 = createStudent("학생1", 15, 90);
        Student student2 = createStudent("학생2", 16, 80);

        printStudent(student1);
        printStudent(student2);
    }

    static Student createStudent(String name, int age, int grade) {
        Student student = new Student(); // 인스턴스의 참조값이 student 변수에 할당
        student.name = name; // String은 참조형이지만 기본형처럼 실제 값이 할당
        student.age = age; //  int 형은 기본형이므로 실제 값이 할당
        student.grade = grade; // int형이므로 실제 값이 할당  
        return student;

    }

    static void printStudent(Student student) {
        System.out.println("이름: " + student.name + " 나이: " + student.age + " 성적: " + student.grade);

    }
}
