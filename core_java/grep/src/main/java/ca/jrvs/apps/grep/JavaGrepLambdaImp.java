package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }
    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex){
      javaGrepLambdaImp.logger.error("Error. Process Failed", ex);
    }
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    Path path = Paths.get(inputFile.getAbsolutePath());
    try {
      return Files.lines(path).collect(Collectors.toList());
    } catch (IOException e) {
      logger.error("Not readable", e);
    }
    return null;
  }


 @Override
  public List<File> listFiles(String rootDir) {
   Path path = Paths.get(rootDir);
    List<File> fList = new ArrayList<File>();
      try {
        fList = Files.walk(path)
            .filter(Files::isRegularFile)
            .map(Path::toFile)
            .collect(Collectors.toList());
        return fList;
      }
      catch(IOException e){
        logger.error("Invalid input", e);
      }
      return null;
    }
  }

