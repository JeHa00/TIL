package study_23;

public class MemberInitMain{
    public static void main(String[] args) {
        MemberInit member1 = new MemberInit();
        member1.initMember("user1", 15, 90); // this를 사용하여 초기화  

        MemberInit member2 = new MemberInit();
        member2.initMember("user2", 16, 80); // this를 사용하여 초기화  
        MemberInit[] members = {member1, member2};

        for (MemberInit member : members) {
            System.out.println("이름: " + member.name + " 나이: " + member.age + " 성적: " + member.grade) ;
        }

    }
}
