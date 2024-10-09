import urllib.request

req = urllib.request.Request('http://python.org/')

with urllib.request.urlopen(req) as response:

    html = response.read().decode('utf-8')    
    the_page = response.read()
    print(html)
