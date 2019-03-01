# iBox

This project is a DropBox prototype. It watches for changes (CREATE, MODIFY, DELETE) in a designated folder (in this case iBox_Local/)and reflect all the changes in Google Drive. Below are the steps to clone the repo and to run test cases.

1. Build
    ```
    git clone https://github.com/nambvp03/iBox.git
    cd iBox
    mvn clean install
    ```

2. Running the Tests
    * Unit Test: `mvn verify`
    * Integration-Test: `mvn integration-test`
    * Cobertura: `mvn cobertura:cobertura`
    * CheckStyle: `mvn checkstyle:checkstyle`
    * FindBugs: `mvn site` (this also can be used to generate all three reports.)

3. Reports will be found in target/site
