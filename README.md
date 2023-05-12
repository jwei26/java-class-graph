Java Class Graph
This project is a Java application designed to analyze class dependencies in Java projects. It parses Java source code files, creates a graph of dependencies between classes, and provides insights into the relationships and structure of your Java project.

Features
Parse Java source code files to identify class dependencies
Create a graph representation of the project's class structure
Generate a visual representation of the class dependency graph
Provide insights and metrics for class coupling and cohesion
Getting Started
To get started with this project, follow these steps:

Clone the repository:
bash
Copy code
git clone https://github.com/jwei26/java-class-graph.git
Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).

Ensure you have the necessary JDK installed. This project requires JDK 8 or higher.

Build the project using your IDE's build tools or by running the following command in the project root directory:

bash
Copy code
./mvnw clean install
Run the application using your IDE or by running the following command in the project root directory:
arduino
Copy code
./mvnw spring-boot:run
Usage
To analyze a Java project, provide the path to the directory containing the source code files as a command line argument:

bash
Copy code
java -jar target/java-class-graph-1.0-SNAPSHOT.jar /path/to/your/java/project
This will generate a report detailing the class dependencies and other relevant metrics for the provided project.

Contributing
We welcome contributions to this project! To contribute, please:

Fork the repository.
Create a new branch with a descriptive name.
Make your changes and commit them to your branch.
Open a pull request against the main branch of the original repository.
Please ensure your code is well-documented and adheres to the project's coding style.
