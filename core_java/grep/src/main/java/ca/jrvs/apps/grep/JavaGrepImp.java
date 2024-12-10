package ca.jrvs.apps.grep;

import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.io.*;

public class JavaGrepImp implements JavaGrep{

  final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args){
    //Check if input contains 3 arguments
    if(args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);
    javaGrepImp.listFiles(javaGrepImp.getRootPath());

    try{
      javaGrepImp.process();
    }catch(Exception ex){
      javaGrepImp.logger.error("Error: Unable to process", ex);
    }
  }

  /**
   * Top level search workflow
   *
   * @throws IOException
   */
  @Override
  public void process() throws IOException {
    List<File> fileList = listFiles(rootPath);
    List<String> lines = new ArrayList<>();

    for(File file: fileList){
      for(String line : readLines(file)){
        if(containsPattern(line))
          lines.add(line);
      }
    }
    writeToFile(lines);
  }

  /**
   * Traverse a given directory and return all files
   *
   * @param rootPath input directory
   * @return files under the rootDir
   */
  @Override
  public List<File> listFiles(String rootPath) {
    List<File> list = new ArrayList<>();
    File root = new File(rootPath);
    traverseDirectory(root, list);

    return list;
  }

  /**
   * Helper function to recursively traverse a directory and return all files
   * @param root file at the input directory
   * @param list arraylist of file names
   */
  private void traverseDirectory(File root, List<File> list){
    File[] files = root.listFiles();
    if(files != null) {
      for( File file: files){
        if(file.isFile()){
          list.add(file);
        }else if (file.isDirectory()){
          traverseDirectory(file, list);
        }
      }
    }
  }

  /**
   * Read a file and return all the lines
   * <p>
   * BufferedReader creates a buffer and uses FileReader to read large amounts of data, loading it into an input buffer in the memory.
   * Character encoding defines how text is represented as bytes in the file. FileReader reads characters from a file using the system's default encoding.
   *
   * @param inputFile file to be read
   * @return lines
   * @throws IllegalArgumentException if a given inputFile is not a file
   */
  @Override
  public List<String> readLines(File inputFile){
    List<String> list = new ArrayList<>();
    if (inputFile == null || !inputFile.isFile()){
      throw new IllegalArgumentException("Invalid file: " + inputFile);
    }
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
      String line;
      while((line = bufferedReader.readLine()) != null){
        list.add(line);
      }
    } catch (IOException e){
      logger.error(e.getMessage());
    }
    return list;
  }

  /**
   * check if a line contains the regex pattern (passed by user)
   *
   * @param line input string
   * @return true if there is a match
   */
  @Override
  public boolean containsPattern(String line) {
    Pattern pattern = Pattern.compile(getRegex());
    return pattern.matcher(line).matches();
  }

  /**
   * Write lines to a file
   * <p>
   * BufferedWriter writes text to a file by buffering the data before writing it to the outputstream.
   * FileOutputStream writes raw bytes to a file.
   * OutputStreamWriter converts characters into bytes using a specific encoding before writing them to a file.
   *
   * @param lines matched line
   * @throws IOException if write failed
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File file = new File(outFile);
    try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
      for (String line : lines){
        bufferedWriter.write(line);
        bufferedWriter.newLine();
      }
    }
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
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
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
