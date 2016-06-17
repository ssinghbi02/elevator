
## Synopsis

The Elevator reads in raw instructions from file and to create a list of Command objects. Elevator has a controller which interpret raw command and provides sequence of instruction which elevator can understand. Elevator takes controller when instantiated. 
There are two type of controller which embed algorithm to compute path logic and elevator then displays path and distance.

## Code Example



## Installation

I am using maven 3 for depedency and build configuration. build artifact (jar) published in target folder. you can run the application from jar file (java -jar elevator.jar). you can also run from any IDE (eclipse or intellij) by running App.java. This takes instruction file from classpath: current location is src\main\resources\input.txt .

Once you run App.java output will be printed on console:

Output:

```Elevator in mode A
10 8 1 (9)<br/>
9 1 5 1 6 1 5 (30)<br/>
2 4 1 4 2 6 8 (16)<br/>
3 7 9 3 7 5 8 7 11 1 (36)<br/>
7 11 6 10 5 6 8 7 4 12 7 8 9 (40)<br/>
6 1 8 6 8 (16)<br/>
Elevator in mode B<br/>
10 8 1 (9)<br/>
9 1 5 6 (13)<br/>
2 4 2 1 6 8 (12)<br/>
3 5 7 8 9 11 1 (18)<br/>
7 11 10 6 5 6 8 12 7 4 8 9 (30)<br/>
6 1 6 8 (12)```



## API Reference


## Tests

There are unit test for Controller , command and elevator. For mocking i am using Mockito.
