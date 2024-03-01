# DPE University Training

[<img width="10%" height="10%" src="https://user-images.githubusercontent.com/120980/174325546-8558160b-7f16-42cb-af0f-511849f22ebc.png">](https://dpeuniversity.gradle.com/)
Checkout all the **free** Maven, Gradle, and DPE courses at the [DPE University][dpe-university]!

## Maven Build Cache Deep Dive - Lab 05: Outputs Overwrite Inputs

This is a hands-on exercise to go along with the [Maven Build Cache Deep Dive][course-url] training module. In this exercise you will go over the following:

- How to deal with cache misses caused by outputs that overwrite inputs of a previously run goal.

## Prerequisites

- Finished going through the _Troubleshooting Build Cache Misses_ section in Maven Build Cache Deep Dive.
- Java 11+

Steps
-----

1. If you have not completed the previous labs, authenticate Maven with the Develocity server.

> [!NOTE]
> As part of taking this **free** course, you have access to a training instance of Develocity located at:
> ```
>  https://dpeuniversity-develocity.gradle.com/
>  ```
> [Sign in][develocity-url] to this Develocity server using the same account you use for the DPE University.
>
> This server is configured so users can only access the Build Scan® and Build Cache entries they publish.

Run the following command and follow the instructions in your terminal:

 ```shell
 ./mvnw com.gradle:gradle-enterprise-maven-extension:provision-access-key
 ```
> [!NOTE]
> For more ways to authenticate, see the [authentication guide](https://docs.gradle.com/enterprise/maven-extension/#authenticating_with_gradle_enterprise) to see how to provide credentials.

2. In this example the `data` directory represents content used by the build that _may_ be in another repository, a external application, or other resources needed by the build.

    This directory has already been added as an input, see the `pom.xml`.

3. Run the build **multiple times**:

    ```shell
    ./mvnw clean install
    ```

4. Notice how the test goal was not cached (the tests run on every build). Open the Build Scan to see more details.
   
    Open `HelloWorldTest.java` and look at the `writeToResourcesDir` test method, this test writes to a directory that is used as an input. This changed input will cause a cache miss on the next build.

5. Fix the test.

    While it is possible to configure Develocity to [ignore a whole file](https://docs.gradle.com/enterprise/maven-extension/#ignoring_arbitrary_files), when possible it's best to fix the cause of the problem. In this case, update the test to write to a different location (a temp directory or the `target/` dir).

    ```diff
    - // resolve a file in the data dir (data/timing.txt)
    - Path timingFile = Paths.get("data/timing.txt");
    + // resolve a file relative to the `target` directory
    + Path timingFile = Paths.get("./target/test-output/timing.txt");
    + Files.createDirectories(timingFile.getParent());
    ```
6. Run the build **multiple times**:

    ```shell
    ./mvnw clean install
    ```

   After the first run the test goal will have a cache hit!

## Solution Reference

To see the solution to the lab, check out the [`solution`](https://github.com/gradle/outputs-overwrite-inputs-maven-build-cache-lab/commit/solution) branch of this project.

## More Free Labs & Courses

Be sure to check out our other **free** [courses][dpe-university] and [labs](https://github.com/gradle?q=dpe-university)!

**Related courses:**
- [Maven - Build Cache Deep Dive][course-url]
- [Maven - Maintaining an Optimized Build Cache](https://dpeuniversity.gradle.com/c/42cf9d626302011526c4a0536b26af929b5bef58)
- [Develocity - How to Use Build Scans](https://dpeuniversity.gradle.com/c/0b0b3e4a8d21709ff39074e9962eee6ca4276dc1)

**Related labs:**
- [Lab 01 - Using the local build cache](https://github.com/gradle/getting-started-maven-build-cache-lab)
- [Lab 02 - Missing Inputs With Build Caching](https://github.com/gradle/missing-inputs-maven-build-cache-lab)
- [Lab 03 - Add Build Cache Support to any Maven Plugin](https://github.com/gradle/caching-any-plugin-maven-build-cache-lab)
- [Lab 04 - Handling Cache Misses with Normalization](https://github.com/gradle/cache-misses-maven-build-cache-lab)
- [Lab 05 - Outputs Overwrite Inputs](https://github.com/gradle/outputs-overwrite-inputs-maven-build-cache-lab)
- [Lab 06 - Maintaining an Optimized Build Cache](https://github.com/gradle/maintaining-optimized-cache-maven-build-cache-lab)

[course-url]: https://dpeuniversity.gradle.com/c/47262fea1e74b719afb590d8cb3f8280bf2af732
[dpe-university]: https://dpeuniversity.gradle.com/
[develocity-url]: https://dpeuniversity-develocity.gradle.com/