## Benchmark

How fast is faiNumber for Java?

The below are benchmark results for faiNumber (v.1.0.0.f) when parsing 
decimal integer comparing to parseInt() and parseLong() method of Java.
Each test ran 3 times and the average result from all 3 tests is used as
the final result. For each test, faiNumber's was built and run on the
targeted JDK version. The benchmark code was also built and run on the
targeted JDK version. 

Both faiNumber's toInt() and parseInt() were parsing the same random 
array. That was also the same for faiNumber's toLong() and parseLong().

Results are in milliseconds and were rounded to the nearest value without
decimal placement. For the results, the lower is the better.

###### CPU: AMD® A10-7800 radeon r7, 12 compute cores 4c+8g × 4
###### OS: Ubuntu 18.04.1 LTS
<br>

##### Parsed 10,000,000 random number strings.
| | JDK 8  | JDK 11 |
|---|---|---|
| Integer.parseInt()           | 516ms | 558ms | 
| base10Util.toInt()           | 368ms | 365ms |

##### Parsed 10,000,000 random number strings.
| | JDK 8  | JDK 11 |
|---|---|---|
| Long.parseLong()             | 930ms | 1124ms |
| base10Util.toLong()          | 543ms |  635ms |
