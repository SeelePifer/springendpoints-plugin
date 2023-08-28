package com.example.springendpoints;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class CompletionContributor extends com.intellij.codeInsight.completion.CompletionContributor {
    public CompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(PsiPlainText.class), new CompletionProvider<>() {
            @Override
            protected void addCompletions(@NotNull CompletionParameters parameters,
                                          @NotNull ProcessingContext context,
                                          @NotNull CompletionResultSet resultSet) {
                PsiElement position = parameters.getPosition();
                String textBeforeCaret = position.getText();


                if (textBeforeCaret.endsWith(".post")) {
                    resultSet.addElement(LookupElementBuilder.create(".post")
                            .withInsertHandler((context1, item) -> {

                                String generatedCode = generatePostEndpointCode();
                                PsiDocumentManager documentManager = PsiDocumentManager.getInstance(context1.getProject());
                                PsiFile psiFile = context1.getFile();
                                Document document = documentManager.getDocument(psiFile);
                                if (document != null) {
                                    int tailOffset = context1.getTailOffset();
                                    documentManager.doPostponedOperationsAndUnblockDocument(document);
                                    documentManager.commitDocument(document);

                                    document.insertString(tailOffset, generatedCode);
                                    documentManager.commitDocument(document);
                                }
                            }));
                }
                else if (textBeforeCaret.endsWith(".patch")) {
                    resultSet.addElement(LookupElementBuilder.create(".patch")
                            .withInsertHandler((context1, item) -> {

                                String generatedCode = generatePatchEndpointCode();
                                PsiDocumentManager documentManager = PsiDocumentManager.getInstance(context1.getProject());
                                PsiFile psiFile = context1.getFile();
                                Document document = documentManager.getDocument(psiFile);
                                if (document != null) {
                                    int tailOffset = context1.getTailOffset();
                                    documentManager.doPostponedOperationsAndUnblockDocument(document);
                                    documentManager.commitDocument(document);

                                    document.insertString(tailOffset, generatedCode);
                                    documentManager.commitDocument(document);
                                }
                            }));
                }
                else if(textBeforeCaret.endsWith(".delete")){
                    resultSet.addElement(LookupElementBuilder.create(".patch")
                            .withInsertHandler((context1, item) -> {

                                String generatedCode = generateDeleteEndpointCode();
                                PsiDocumentManager documentManager = PsiDocumentManager.getInstance(context1.getProject());
                                PsiFile psiFile = context1.getFile();
                                Document document = documentManager.getDocument(psiFile);
                                if (document != null) {
                                    int tailOffset = context1.getTailOffset();
                                    documentManager.doPostponedOperationsAndUnblockDocument(document);
                                    documentManager.commitDocument(document);

                                    document.insertString(tailOffset, generatedCode);
                                    documentManager.commitDocument(document);
                                }
                            }));
                }
                else if(textBeforeCaret.endsWith(".get")){
                    resultSet.addElement(LookupElementBuilder.create(".get")
                            .withInsertHandler((context1, item) -> {

                                String generatedCode = generateGetEndpointCode();
                                PsiDocumentManager documentManager = PsiDocumentManager.getInstance(context1.getProject());
                                PsiFile psiFile = context1.getFile();
                                Document document = documentManager.getDocument(psiFile);
                                if (document != null) {
                                    int tailOffset = context1.getTailOffset();
                                    documentManager.doPostponedOperationsAndUnblockDocument(document);
                                    documentManager.commitDocument(document);

                                    document.insertString(tailOffset, generatedCode);
                                    documentManager.commitDocument(document);
                                }
                            }));
                }
                else if(textBeforeCaret.endsWith(".put")){
                    resultSet.addElement(LookupElementBuilder.create(".put")
                            .withInsertHandler((context1, item) -> {

                                String generatedCode = generatePutEndpointCode();
                                PsiDocumentManager documentManager = PsiDocumentManager.getInstance(context1.getProject());
                                PsiFile psiFile = context1.getFile();
                                Document document = documentManager.getDocument(psiFile);
                                if (document != null) {
                                    int tailOffset = context1.getTailOffset();
                                    documentManager.doPostponedOperationsAndUnblockDocument(document);
                                    documentManager.commitDocument(document);

                                    document.insertString(tailOffset, generatedCode);
                                    documentManager.commitDocument(document);
                                }
                            }));
                }
            }
        });
    }

    private String generatePutEndpointCode() {
        // Generate the code for a Spring Boot PUT endpoint
        // Return the generated code as a string
        return """

                @PutMapping("/endpoint")
                public ResponseEntity<String> updateResource(@PathVariable Long id, @RequestBody String data) {
                    // Handle PUT request
                    return ResponseEntity.ok("Resource updated");
                }
                """;
    }
    private String generateGetEndpointCode() {
        // Generate the code for a Spring Boot GET endpoint
        // Return the generated code as a string
        return """

                @GetMapping("/endpoint")
                public ResponseEntity<String> getResource(@RequestParam String id) {
                    // Handle GET request
                    return ResponseEntity.ok("Resource retrieved");
                }
                """;
    }
    private String generateDeleteEndpointCode() {
        // Generate the code for a Spring Boot DELETE endpoint
        // Return the generated code as a string
        return """

                @DeleteMapping("/endpoint")
                public ResponseEntity<String> deleteResource(@PathVariable Long id) {
                    // Handle DELETE request
                    return ResponseEntity.ok("Resource deleted");
                }
                """;
    }
    private String generatePatchEndpointCode() {
        // Generate the code for a Spring Boot PATCH endpoint
        // Return the generated code as a string
        return """

                @PatchMapping("/endpoint")
                public ResponseEntity<String> updateResource(@RequestBody String data) {
                    // Handle PATCH request
                    return ResponseEntity.ok("Resource updated");
                }
                """;
    }

    private String generatePostEndpointCode() {
        // Generate the code for a Spring Boot POST endpoint
        // Return the generated code as a string
        return """

                @PostMapping("/endpoint")
                public ResponseEntity<String> createResource(@RequestBody String data) {
                    // Handle POST request
                    return ResponseEntity.ok("Resource created");
                }
                """;
    }
}
