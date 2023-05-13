package com.jwei26.javaclass_graph.analyzer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathSuffixFilter;

import java.io.IOException;
import java.io.InputStream;

public class JavaClassAnalyzer {
    public static String analyze(Git git) throws IOException {
        DotFileGenerator dotFileGenerator = new DotFileGenerator("output.dot");

        Repository repository = git.getRepository();
        ObjectId lastCommitId = repository.resolve(Constants.HEAD);
        try (RevWalk revWalk = new RevWalk(repository)) {
            RevCommit commit = revWalk.parseCommit(lastCommitId);
            try (TreeWalk treeWalk = new TreeWalk(repository)) {
                treeWalk.addTree(commit.getTree());
                treeWalk.setRecursive(true);
                treeWalk.setFilter(PathSuffixFilter.create(".java"));
                while (treeWalk.next()) {
                    String path = treeWalk.getPathString();
                    ObjectId objectId = treeWalk.getObjectId(0);
                    ObjectLoader loader = repository.open(objectId);

                    try (InputStream in = loader.openStream()) {
                        analyzeJavaFile(path, in, dotFileGenerator);
                    }
                }
            }
        }

        return dotFileGenerator.generate();
    }

    private static void analyzeJavaFile(String path, InputStream inputStream, DotFileGenerator dotFileGenerator) throws IOException {
        CompilationUnit compilationUnit = StaticJavaParser.parse(inputStream);
        String packageName = compilationUnit.getPackageDeclaration().map(pd -> pd.getName().asString()).orElse("default");

        compilationUnit.accept(new VoidVisitorAdapter<Object>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration clazz, Object arg) {
                String className = clazz.getNameAsString();

                dotFileGenerator.addNode(packageName, className);

                clazz.getExtendedTypes().forEach(parent -> {
                    dotFileGenerator.addEdge(parent.getNameAsString(), className, "extends");
                });

                clazz.getImplementedTypes().forEach(interfaceImpl -> {
                    dotFileGenerator.addEdge(className, interfaceImpl.getNameAsString(), "implements");
                });

                super.visit(clazz, arg);
            }
        }, null);
    }

    public static String analyzeByClassName(Git git, String targetClassName) throws IOException {
        DotFileGenerator dotFileGenerator = new DotFileGenerator("output.dot");

        Repository repository = git.getRepository();
        ObjectId lastCommitId = repository.resolve(Constants.HEAD);
        try (RevWalk revWalk = new RevWalk(repository)) {
            RevCommit commit = revWalk.parseCommit(lastCommitId);
            try (TreeWalk treeWalk = new TreeWalk(repository)) {
                treeWalk.addTree(commit.getTree());
                treeWalk.setRecursive(true);
                treeWalk.setFilter(PathSuffixFilter.create(".java"));
                while (treeWalk.next()) {
                    String path = treeWalk.getPathString();
                    ObjectId objectId = treeWalk.getObjectId(0);
                    ObjectLoader loader = repository.open(objectId);

                    try (InputStream in = loader.openStream()) {
                        analyzeRelatedJavaClasses(path, in, dotFileGenerator, targetClassName);
                    }
                }
            }
        }

        return dotFileGenerator.generate();
    }

    private static void analyzeRelatedJavaClasses(String path, InputStream inputStream, DotFileGenerator dotFileGenerator, String targetClassName) throws IOException {
        CompilationUnit compilationUnit = StaticJavaParser.parse(inputStream);
        String packageName = compilationUnit.getPackageDeclaration().map(pd -> pd.getName().asString()).orElse("default");

        compilationUnit.accept(new VoidVisitorAdapter<Object>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration clazz, Object arg) {
                String className = clazz.getNameAsString();

                boolean isTargetClass = className.equals(targetClassName);
                boolean isRelatedClass = clazz.getExtendedTypes().stream().anyMatch(parent -> parent.getNameAsString().equals(targetClassName))
                        || clazz.getImplementedTypes().stream().anyMatch(interfaceImpl -> interfaceImpl.getNameAsString().equals(targetClassName));

                if (isTargetClass || isRelatedClass) {
                    dotFileGenerator.addNode(packageName, className);

                    clazz.getExtendedTypes().forEach(parent -> {
                        dotFileGenerator.addEdge(parent.getNameAsString(), className, "extends");
                    });

                    clazz.getImplementedTypes().forEach(interfaceImpl -> {
                        dotFileGenerator.addEdge(className, interfaceImpl.getNameAsString(), "implements");
                    });
                }

                super.visit(clazz, arg);
            }
        }, null);
    }

}



