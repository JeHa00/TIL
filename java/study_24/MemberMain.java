package study_24;

public class MemberMain {

    public static void main(String[] args) {
        // 호출 연산자를 클래스에 사용하는 이유는 인스턴스 생성 후, 바로 생성자를 호출하기 때문
        Member member1 = new Member("user1", 15, 90);
        Member member2 = new Member("user2", 16, 80);

        Member[] members = {member1, member2};

        for (Member member : members) {
            System.out.println(member.name + " " + member.age + " " + member.grade);
        }

    }
}

/*
 [생성자의 장점]
  - 중복 호출 제거: 초기화를 위한 메서드를 반복적으로 호출하지 않아도 된다.
  - 생성자를 반드시 호출하는 제약을 필수로 둠: 개발자가 싫수로 초기화하지 않았을 경우를 대비 
    - 사용자 정의 사용자가 없으면 기본 사용자를 사용  
  - 생성자를 사용하여 필수값 입력을 보장  
*/