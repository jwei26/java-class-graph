# Java Class Graph

This project aims to build a directed graph of Java classes, where nodes represent classes and edges represent class relationships like inheritance, composition, and aggregation.

## Table of Contents
- [Features](#Features)
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

1.  Parse Java source code files to identify class dependencies
2.  Create a graph representation of the project's class structure
3.  Generate a visual representation of the class dependency graph
4.  Provide insights and metrics for class coupling and cohesion

## Getting Started

To get started with this project, follow these steps:

1. Clone the repository:

```
git clone https://github.com/jwei26/java-class-graph.git
```

2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).

3. Ensure you have the necessary JDK installed. This project requires JDK 8 or higher.

4. Build the project using your IDE's build tools or by running the following command in the project root directory:

```
./mvnw clean install
```

5. Run the application using your IDE or by running the following command in the project root directory:

```
./mvnw spring-boot:run
```

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven
- IntelliJ IDEA (or any other Java IDE)

## Usage

Once the project is set up, you can run the main class (e.g., `JavaClassGraph.java`) to generate the directed graph of Java classes.

Please refer to the source code and comments for further details on how the graph is constructed and analyzed.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Push the branch to your fork.
5. Create a pull request.

Before submitting a pull request, please ensure that your code follows the project's coding style and conventions.
