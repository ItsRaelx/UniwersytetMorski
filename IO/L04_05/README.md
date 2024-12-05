# Traveling Salesman Problem (TSP) Local Search Algorithms

This project implements various local search algorithms to solve the Traveling Salesman Problem (TSP). The algorithms include different strategies for finding optimal or near-optimal solutions for the classic TSP.

## Algorithms Implemented

1. **LocalSearch1**
    - Uses a simple local search with random neighbor selection
    - Preserves the first city (index 0) in the permutation
    - Continues until a local optimum is found

2. **LocalSearch2**
    - Implements two variants:
        - First Improvement: Accepts the first better neighbor found
        - Best Improvement: Evaluates all neighbors and selects the best one
    - Uses random initial solutions and multiple iterations
    - Maximum iterations: 100

3. **Additional Implementations**
    - SwapAllPairs: Systematically evaluates all possible pair swaps
    - LocalSearchBestNeighbor: Uses best neighbor strategy with random initial solution

## Data Format

The program reads TSP data files in TSPLIB95 format. The input file should contain:
- NODE_COORD_SECTION marker
- City coordinates in format: `<city_id> <x_coord> <y_coord>`
- EOF marker at the end

Example:
```
NAME: berlin52
TYPE: TSP
COMMENT: 52 locations in Berlin (Groetschel)
DIMENSION: 52
EDGE_WEIGHT_TYPE: EUC_2D
NODE_COORD_SECTION
1 565.0 575.0
```


## Test Data

Test data is sourced from TSPLIB95, a library of sample instances for the TSP:
http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/index.html

The project includes testing with berlin52.tsp (52 locations in Berlin) as an example instance.

## Usage

1. Place your TSP data file in the project directory
2. Run either Main.java or LocalSearch2.java
3. The program will output:
    - Initial tour length
    - Optimization progress
    - Final tour length
    - Final permutation of cities

## Implementation Details

- Written in Java
- Uses Euclidean distance calculations
- Implements multiple neighborhood search strategies
- Includes progress monitoring and result reporting