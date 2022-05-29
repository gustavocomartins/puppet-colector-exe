package com.mycompany.puppet.colector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private final String pathLogDir;

    public Log(String userMaquina) {
        pathLogDir = String.format("/home/%s/PuppetCollector/", userMaquina);
    }
    
    public void newLog(String message){
        DateTimeFormatter dtf      = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String            dataHoje = dtf.format(LocalDateTime.now());
        
        String stringPath = String.format(pathLogDir + "collectorLog-%s.txt", dataHoje);
        Path path = Paths.get(stringPath);
        
        File todayLog = new File(stringPath);
        
        if(todayLog.exists()){
            escreverLog(message, todayLog);
        }
        else{
            criarLogFile(path);
            escreverLog(message, todayLog);
        }                       
    }
    
    public void criarLogFile(Path path){
       try {
            Files.createFile(path);
        } 
        catch (IOException e) {
            System.out.println("Erro ao criar arquivo " + path.toString());
        }     
    }
    
    public void escreverLog(String message, File file){
        DateTimeFormatter dtf      = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String            dataHoje = dtf.format(LocalDateTime.now());
        
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file))) {
            writer.println(dataHoje + "--- " + message);
            System.out.println(String.format("Novo log registado em %s", pathLogDir + String.format("log-%s.txt", dataHoje)));
            writer.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }  
}
