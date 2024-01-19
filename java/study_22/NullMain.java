package study_22;

public class NullMain {
    public static void main(String[] args) {
        BigData bigData = new BigData();
        System.out.println(bigData.count);
        System.out.println(bigData.data); // null 이 출력, 객체는 초기화되면 null이 포함된다.  
        System.out.println(bigData.data.value); // nullPointerException 발생
        bigData.data = new Data(); // 인스턴스 참조값 할당
        System.out.println(bigData.data.value); // 에러 발생 x  

    }

}
