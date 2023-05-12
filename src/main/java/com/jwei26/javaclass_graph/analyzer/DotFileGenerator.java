package com.jwei26.javaclass_graph.analyzer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class DotFileGenerator {
    private final String filePath;
    private final StringBuilder stringBuilder;
    private final Map<String, StringBuilder> subgraphs;

    public DotFileGenerator(String filePath) {
        this.filePath = filePath;
        stringBuilder = new StringBuilder();
        stringBuilder.append("digraph G {\n");
        subgraphs = new HashMap<>();
    }

    public void addNode(String packageName, String nodeName) {
        StringBuilder subgraphBuilder = subgraphs.computeIfAbsent(packageName, key -> {
            StringBuilder newSubgraph = new StringBuilder();
            newSubgraph.append("subgraph \"cluster_").append(packageName).append("\" {\n");
            newSubgraph.append("label=\"").append(packageName).append("\";\n");
            return newSubgraph;
        });

        subgraphBuilder.append("\"").append(nodeName).append("\";\n");
    }

    public void addEdge(String fromNode, String toNode, String label) {
        stringBuilder.append("\"").append(fromNode).append("\" -> \"").append(toNode)
                .append("\" [label=\"").append(label).append("\"];\n");
    }

    public void generate() {
        subgraphs.values().forEach(subgraph -> {
            subgraph.append("}\n");
            stringBuilder.append(subgraph.toString());
        });

        stringBuilder.append("}\n");

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

