import array 


chars = '*()_+%$'

array_g = array.array('f', (ord(s) for s in chars))
print(array_g)

array_l= array_g.tolist()
print(array_l)