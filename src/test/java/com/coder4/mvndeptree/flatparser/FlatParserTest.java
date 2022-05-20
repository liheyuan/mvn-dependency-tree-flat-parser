package com.coder4.mvndeptree.flatparser;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FlatParserTest {

    private static enum TestFile {
        FILE1("1.txt"),
        FILE2("2.txt"),
        FILE3("3.txt"),
        FILE4("4.txt");

        private String file;

        TestFile(String file) {
            this.file = file;
        }

        public String getFile() {
            return file;
        }
    }

    private File getTestFile(TestFile testFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(testFile.getFile()).getFile());
    }

    private String loadFile(TestFile testFile) throws FileNotFoundException {
        File file = getTestFile(testFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    // Test mvn dependency single project
    @Test
    public void testSingle() throws FileNotFoundException {
        FlatParseResult res = FlatParser.parse(loadFile(TestFile.FILE1));
        Assert.assertEquals(46, res.getItems().size());

        FlatParseItem item = res.getItems().get(0);
        Assert.assertEquals("org.springframework.boot", item.getGroup());
        Assert.assertEquals("spring-boot-starter", item.getArtifactId());
        Assert.assertEquals("compile", item.getScope());
        Assert.assertEquals("jar", item.getType());
        Assert.assertEquals("2.6.8", item.getVersion());

    }

    // Test mvn dependency empty project
    @Test
    public void testEmpty() throws FileNotFoundException {
        FlatParseResult res = FlatParser.parse(loadFile(TestFile.FILE2));
        Assert.assertEquals(0, res.getItems().size());
    }

    // Test mvn dependency mutiple project
    @Test
    public void testMutiple() throws FileNotFoundException {
        FlatParseResult res = FlatParser.parse(loadFile(TestFile.FILE3));
        Assert.assertEquals(12, res.getItems().size());

        FlatParseItem item = res.getItems().get(0);
        Assert.assertEquals("junit", item.getGroup());
        Assert.assertEquals("junit", item.getArtifactId());
        Assert.assertEquals("test", item.getScope());
        Assert.assertEquals("jar", item.getType());
        Assert.assertEquals("3.8.1", item.getVersion());

    }

    // Test invalid empty file
    @Test
    public void testInvalidEmpty() throws FileNotFoundException {
        FlatParseResult res = FlatParser.parse(loadFile(TestFile.FILE4));
        Assert.assertEquals(0, res.getItems().size());
    }

    // Test mvn dependency single project in File Mode
    @Test
    public void testFile() throws FileNotFoundException {
        FlatParseResult res = FlatParser.parse(getTestFile(TestFile.FILE1));
        Assert.assertEquals(46, res.getItems().size());

        FlatParseItem item = res.getItems().get(0);
        Assert.assertEquals("org.springframework.boot", item.getGroup());
        Assert.assertEquals("spring-boot-starter", item.getArtifactId());
        Assert.assertEquals("compile", item.getScope());
        Assert.assertEquals("jar", item.getType());
        Assert.assertEquals("2.6.8", item.getVersion());

        for (FlatParseItem itemx : res.getItems()) {
            System.out.println(item.getGroup());
            System.out.println(item.getArtifactId());
            System.out.println(item.getVersion());
            System.out.println(item.getScope());
            System.out.println(item.getType());

        }
    }

}
