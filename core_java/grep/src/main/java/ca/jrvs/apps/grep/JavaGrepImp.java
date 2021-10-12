package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
// import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;


public class JavaGrepImp implements JavaGrep{
  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length !=3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    //Use default logger config
    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error("Error: Unable to process", ex);
    }
  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();
    for (File file : listFiles(getRootPath())) {
      for (String line : readLines(file)) {
        if (containsPattern(line))
        matchedLines.add(line);
      }
    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> files = new ArrayList<>();
    File root = new File(rootDir);

    File[] fList = root.listFiles();
    if (fList != null) {
      for (File f : fList) {
        if (f.isFile()) {
          files.add(f);
        } else if (f.isDirectory()){
          files.addAll(listFiles(f.getAbsolutePath()));
        }
      }
    }
    return files;
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    List<String> lines = new ArrayList<String>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(inputFile));
      String line = br.readLine();
      while (line != null) {
        lines.add(line);
        line = br.readLine();
      }
      br.close();
    } catch (IOException e) {
      logger.error("Error: Invalid input", e);
    }
    return lines;
  }

  @Override
  public boolean containsPattern(String line) {
    boolean isPattern = Pattern.matches(getRegex(), line);
    return isPattern;
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File file = new File(outFile);
    FileOutputStream fileOutput = new FileOutputStream(file);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutput));
    for (String line : lines) {
      bw.write(line);
      bw.newLine();
    }
    bw.close();
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
