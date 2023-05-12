package com.jwei26.javaclass_graph.controller;

import com.jwei26.javaclass_graph.analyzer.JavaClassAnalyzer;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class JavaClassAnalyzerController {

    @PostMapping("/analyze")
    public String analyze(@RequestParam("repositoryUrl") String repositoryUrl) {
        try {
            Git git = cloneRepository(repositoryUrl);
            JavaClassAnalyzer.analyze(git);
            return "Analysis completed. Please check the output.dot file.";
        } catch (IOException | GitAPIException e) {
            return "Error: " + e.getMessage();
        }
    }
    @PostMapping("/analyze-by-class-name")
    public String analyzeByClassName(
            @RequestParam("repositoryUrl") String repositoryUrl,
            @RequestParam("className") String className) {
        try {
            Git git = cloneRepository(repositoryUrl);
            JavaClassAnalyzer.analyzeByClassName(git, className);
            return "Analysis completed. Please check the output.dot file.";
        } catch (IOException | GitAPIException e) {
            return "Error: " + e.getMessage();
        }
    }

    private Git cloneRepository(String repositoryUrl) throws IOException, GitAPIException {
        // Create a temporary directory for cloning remote warehouses
        File tempDir = File.createTempFile("java_class_analyzer_", "");
        tempDir.delete();
        tempDir.mkdir();

        // Clone remote warehouse to temporary directory
        return Git.cloneRepository()
                .setURI(repositoryUrl)
                .setDirectory(tempDir)
                .call();
    }
}


