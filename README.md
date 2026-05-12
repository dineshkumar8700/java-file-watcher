# java-file-watcher

Observe resources/data/incoming folder, on every new score_*.txt process the file, calculate sum of all the numbers in
that file, update the total score of the app and write the current score in resources/data/scores.txt file. Process only
score_*.txt file ignore any other file in that folder.

score_*.txt file format: Plain numbers separated by whitespace(space, tab, newline etc)
text

scores.txt
```
12 14

45
```

result.txt
```
Update Time: datetime in any format
21
Update Time: datetime in any format
24
Update Time: datetime in any format
36
```
