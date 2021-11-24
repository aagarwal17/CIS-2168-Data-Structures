#Arun Agarwal/More Regex


#1. .* is used to stand for anything. It uses greedy mode, so it will
# always try to match as much text as possible. .*? matches any and all
# text in a nongreedy fashion (it will match the shortest string possible).

#2. Regex that matches the full name of someone whose last name is Nakamoto

compilation = re.compile(r'[A-Z][a-zA-Z]*\sNakamoto')

#3. There should be a less repetitive way...

r'((twenty)|(thirty)|(forty)|(fifty)|(sixty)|(seventy)|(eighty)|(ninety))-((one)|(two)|(three)|(four)|(five)|(six)|(seven)|(eight)|(nine))|((twenty)|(thirty)|(forty)|(fifty)|(sixty)|(seventy)|(eighty)|(ninety))'

