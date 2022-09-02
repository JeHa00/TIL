# n: 거스름돈
# money_list: 동전 종류 

def money_change(n, money_list):
    dp = [1] + [0]*n
    for money in money_list:
        for price in range(money, n+1):
            if price >= money:
                dp[price] += dp[price - money]
    return dp[n]




money_change(5,[1,2,5]) 