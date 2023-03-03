class WordSplitter:
    def __init__(self, text):
        self._text = text.split(' ')
    
    def __next__(self):
        for word in self._text:
            yield word
        return 
    
    def __repr__(self):
        return 'WordSplit(%s)' % (self._text)


wi = WordSplitter('Do today what you could do tomorrow')

wt = iter(wi)


print(next(wt))
print(next(wt))
print(next(wt))
print(next(wt))
print(next(wt))
print(next(wt))
print(next(wt))