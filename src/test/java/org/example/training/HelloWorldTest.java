package org.example.training;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

    @Test
    @StdIo
    public void testMain(StdOut out) {
        // look up the location of `data.txt`
        Path dataFile = Paths.get("data/hello.txt");

        // call main
        HelloWorld.main(new String[]{ dataFile.toFile().getAbsolutePath() });

        // expect System output
        assertEquals("Hello Brent Spiner!", out.capturedLines()[0]);
    }

    @Test
    public void writeToResourcesDir() throws IOException {
        // Simple timer
        long startTime = System.nanoTime();

        // resolve a file in the data dir (data/timing.txt)
        Path timingFile = Paths.get("data/timing.txt");

        // Record the time
        long timeTaken = System.nanoTime() - startTime;
        Files.writeString(timingFile, "Time taken: " + timeTaken + "ns");
    }
}
