# Assignment Problem Solver using Simulated Annealing

A Java implementation of the Simulated Annealing algorithm to solve the Assignment Problem, where n workers need to be assigned to n tasks optimally.

## Algorithm Details (Can be modified in `SimulatedAnnealing.java`)

- Initial Temperature: 1000
- Cooling Rate: 0.2
- Inner Loop Iterations (N): 500
- Maximum Iterations: 1000
- Neighborhood Generation: 3-way swap between random assignments

## File Structure

- `Main.java` - Entry point, runs multiple experiments and calculates average cost
- `SimulatedAnnealing.java` - Implementation of the Simulated Annealing algorithm
- `ImportData.java` - Handles reading cost matrix from input file
- `Solution.java` - Represents and manages solution states

## Input Format

Expects a text file (e.g., "assign100.txt") with the following format:
- First line: "size n" where n is the number of workers/tasks
- Following lines: n×n cost matrix

## Usage

```java
double[][] costMatrix = ImportData.importData("assign100.txt");
SimulatedAnnealing sa = new SimulatedAnnealing(costMatrix);
Solution solution = sa.solve();
System.out.println("Cost: " + solution.getCost());
```


## Resources

- [Simulated Annealing](https://en.wikipedia.org/wiki/Simulated_annealing)
- Testing data is from [here](http://people.brunel.ac.uk/~mastjjb/jeb/orlib/assigninfo.html)