# Test Driven Development *(TDD)*

## Overview

You are to solve this assignment using TDD principles. Meaning, all *tests* are to be written before the actual implementation.

Your solution should be structured with a `src` directory containing the implemented code and a `test` directory with the various test cases:

```text
.
├── src
│   └── *
└── test
    └── *
```

## Rules

- You must use *object-oriented programing (OOP)* in your solution.
- Choose an appropriate development language.
- You must follow coding styles and conventions for the language you choose to implement.
- Your test-cases must be descriptive.
- Your commit messages, PR titles, and PR descriptions must be explanatory.
- You must choose a programming language that you have yet to use during one of the previous assignments.
- You can use the internet as a means of learning about OOP or TDD, but the solution must be yours and **yours alone**. **Do not use chat of any kind at all!!**.
- You are going to be graded on the OOP design and principles and less on the actual functionality.

## Task

You are to create a `Calculator` class that will receive a `string` as input and output the results of the calculation.
For example *(the below code is in `python`, but can be in any language)*:

```python
>>> calc = Calculator()
>>> calc('1+2')
3
```

The `Calculator` must be able to accomplish the following computations:

- Addition *(`+`)*.
- Subtraction *(`-`)*.
- Multiplication *(`*`)*.
- Division *(`/`)*.
- Ignore whitespace *(`calc(' 1 + 2 +1')`)*.
- Raise exceptions *(`calc('8/0')`)*.
- Handle computation precedence *(`calc('1 + 2 * 6')` is 13)*.
