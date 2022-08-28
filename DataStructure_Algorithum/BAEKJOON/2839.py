"""
[problem: 2839]

- 문제
    - 최대 5kg을 들 수 있는 봉지와 최대 3kg을 들 수 있는 봉지를 사용하여, 입력값으로 주어진 설탕의 무게를 만족하는 최소 봉지 수 구하기 

- 입력값: 무게

- 출력값: 이 무게를 이룰 수 있는 최소 봉지 수  

"""
def minPlasticBag(kg: int) -> int:
    """
    최대한 적은 봉지로 입력한 무게 만족하기
    """
    
    five_kilo_bags = divmod(kg, 5)
    three_kilo_bags = divmod(kg, 3)

    # 5로만 나누어 떨어지는 경우 (3으로 나누어떨어지든, 아니든)
    if five_kilo_bags[1] == 0 and three_kilo_bags[1] != 0:
        print(five_kilo_bags[0])

    # 5와 3의 조합으로 나누어 떨어지는 경우 
    # 15보다 작은 3의 배수가 여기에 걸린다. 
    elif kg // 15 >= 1 and (kg % 15) % 3 == 0 :
        print(five_kilo_bags[0] + (kg - 5 * five_kilo_bags[0]) // 3)

    # 5로는 나누어떨어지지 않는데, 3으로만 나누어 떨어지는 경우
    elif five_kilo_bags[1] != 0 and three_kilo_bags[1] == 0:
        print(three_kilo_bags[0])

    # 5와 3으로 다 나누어 떨어지지 않는 경우
    else:
        print(-1)


if __name__ == "__main__":
    
    delivery_kg = int(input())
    while delivery_kg > 5000 or delivery_kg < 3: 
        delivery_kg = int(input())
    minPlasticBag(delivery_kg)