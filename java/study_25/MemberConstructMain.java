package study_25;

public class MemberConstructMain {
    public static void main(String[] args) {
        
        // 생성자 오버로딩을 통해 다양한 매개변수 수와 타입으로 인스턴스를 원하는대로 생성할 수 있다.  
        MemberConstruct member1 = new MemberConstruct("user1", 15, 90);
        MemberConstruct member2 = new MemberConstruct("user2", 16);
        MemberConstruct[] members = {member1, member2};
        
        for (MemberConstruct member : members) {
                System.out.println("이름:" + member.name + " 나이:" + member.age + " 성적:" + member.grade);}
        }
    }