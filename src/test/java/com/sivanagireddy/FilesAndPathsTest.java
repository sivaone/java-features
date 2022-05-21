package com.sivanagireddy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FilesAndPathsTest {

    @Test
    @DisplayName("Files and Paths API test")
    void testFilesAndPaths() throws IOException {
        Path input = Paths.get("input.txt");
        Path output = Paths.get("output.txt");
//        Files.deleteIfExists(output);

        Files.writeString(input, "Hello world!\n", StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING);
        System.out.printf("input.txt file size : %d bytes \n", Files.size(input));

        FileTime lastModifiedTime = Files.getLastModifiedTime(input);
        System.out.printf("last modified time %s\n", lastModifiedTime);
        System.out.println("content type " + Files.probeContentType(input));

        Map<String, Object> attributes = Files.readAttributes(input, "*");
        attributes.forEach((k,v) -> System.out.printf("%s = %s\n", k, v));
        Files.copy(input, output, StandardCopyOption.REPLACE_EXISTING);

        List<String> allLines = Files.readAllLines(output);
        byte[] allBytes = Files.readAllBytes(output);
        try (Stream<String> stream = Files.lines(output)) {
            stream.forEach(System.out::println);
        }
    }
}
