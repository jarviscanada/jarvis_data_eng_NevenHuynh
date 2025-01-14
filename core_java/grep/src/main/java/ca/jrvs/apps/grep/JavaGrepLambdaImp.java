package ca.jrvs.apps.grep;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.io.*;
import java.util.stream.*;

public class JavaGrepLambdaImp extends JavaGrepImp{

  final Logger logger = LoggerFactory.getLogger(JavaGrepLambdaImp.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args){
    //Check if input contains 3 arguments
    if(args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    BasicConfigurator.configure();

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);
    javaGrepLambdaImp.listFiles(javaGrepLambdaImp.getRootPath());

    try{
      javaGrepLambdaImp.process();
    }catch(Exception ex){
      javaGrepLambdaImp.logger.error("Error: Unable to process", ex);
    }
  }

  @Override
  public List<String> readLines(File inputFile){
    List<String> lineList = new ArrayList<>();
    if (inputFile == null || !inputFile.isFile()){
      throw new IllegalArgumentException("Invalid file: " + inputFile);
    }
    try (Stream<String> lineStream = Files.lines(inputFile.toPath())){  //create a stream of lines from the file
      lineStream.forEach(line -> lineList.add(line));             //add lines to the list
    } catch (IOException e){
      logger.error(e.getMessage());
    }
    return lineList;
  }

  @Override
  public List<File> listFiles(String rootPath){
    List<File> fileList = new ArrayList<>();
    try(Stream<Path> paths = Files.walk(Paths.get(rootPath))) {
      fileList = paths
          .filter(file -> Files.isRegularFile(file))  //Filtering files
          .map(path -> path.toFile())                 //Converting path to file
          .collect(Collectors.toList());                   // Collect the result into a List<File>
    } catch (IOException e) {
      logger.error(e.getMessage());
    }
    return fileList;
  }
}
