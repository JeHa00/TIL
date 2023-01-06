"""
[11650: 좌표 정렬하기]

x좌표를 기준으로 정렬한 후, x좌표 정렬을 유지하면서 y좌표를 정렬하는 문제
"""
import sys

X, Y = 0, 1

def main(number_of_points: int):
    total_points = []

    # 정해진 갯수만큼 좌표 입력받기
    while number_of_points != 0:
        print('100,000 이하 -100,000 이상의 x, y 값을 입력하세요.')
        point = list(map(int, sys.stdin.readline().split()))
        total_points.append(point)
        number_of_points -= 1 
    
    # 정렬하기 - x 좌표 기준
    points_sorted_on_x = quick_sort_on_x(total_points)
    total_points = quick_sort_on_y(points_sorted_on_x)
    result = total_points
    return result


def quick_sort_on_x(points:list) -> list:
    """
    x 좌표를 기준으로 좌표들을 정렬하는 함수
    """
    if len(points) <= 1:
        return points
    
    pivot = points[0]
    point_less_pivot = []
    point_than_pivot = []

    for i in range(1, len(points)):
        if points[i][X] <= pivot[X]: 
            point_less_pivot.append(points[i])
        else:
            point_than_pivot.append(points[i])
            
    return quick_sort_on_x(point_less_pivot) + [pivot] + quick_sort_on_x(point_than_pivot)


def quick_sort_on_y(points:list) -> list: 
    """
    x 좌표 정렬 후, x 좌표 정렬을 유지하면서 y 좌표에 대해서 정렬하는 함수
    """
    pivot = points[0]
    for i in range(1, len(points)):
        if pivot[X] == points[i][X]:
            if pivot[Y] > points[i][Y]:
                pivot, points[i] = points[i], pivot
        else: 
            pivot = points[i]

    return points

if __name__ == "__main__":
    number_of_points = int(sys.stdin.readline())
    result = main(number_of_points)
    for i in range(number_of_points):
        point = result[i]
        print(point[X], point[Y])