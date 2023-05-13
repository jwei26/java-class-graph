# Java Class Graph Analyzer

The Java Class Graph Analyzer is a tool that analyzes Java classes in a Git repository and generates a graph in DOT format, visualizing the relationships between classes. It supports two modes of operation:

1. Analyzing all classes in the repository.
2. Analyzing a specific class and its related classes.

The tool is implemented as a Spring Boot application and exposes a RESTful API for user interaction.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Usage](#usage)
- [Docker Deployment](#docker-deployment)
- [Contributing](#contributing)
- [License](#license)

## Features

- Parse Java source code files to identify class dependencies
- Create a graph representation of the project's class structure
- Generate a visual representation of the class dependency graph
- Provide insights and metrics for class coupling and cohesion

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven
- IntelliJ IDEA (or any other Java IDE)
- Docker (optional, for deployment)

## Getting Started

1. Clone the repository:
```
git clone https://github.com/jwei26/java-class-graph.git
```
2. Import the project into your Java IDE (e.g., IntelliJ IDEA).
3. Build the project using Maven:
```
mvn clean install
```
## Installation
1. Run the JavaClassGraphApplication class as a Spring Boot application.
2. Open a web browser and navigate to http://localhost:8080/swagger-ui/.
3. Use the Swagger UI to interact with the provided API endpoints. Enter a Git repository URL and, if needed, a class name as input.

## Usage
After successful execution, the application will generate a DOT file (output.dot for the /analyze endpoint, and output.dot for the /analyze-by-class-name endpoint), which can be used to visualize the relationships between classes. You can use a tool like Graphviz or an online DOT viewer to render the graph.https://viz-js.com/

## Docker Deployment
1. Build the Docker image:
```
docker build -t java-class-graph .
```
2. Run the Docker container:
```
docker run -p 8080:8080 java-class-graph
```
3. Access the Swagger UI at http://localhost:8080/swagger-ui/.

## Contributing
Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Push the branch to your fork.
5. Create a pull request.
   Before submitting a pull request, please ensure that your code follows the project's coding style and conventions.
