package com.mycompany.puppet.colector;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Log {
    private final String pathLogDir;

    public Log(String userMaquina) {
        pathLogDir = String.format("/home/%s/PuppetCollector/collectorLogs/", userMaquina);
    }
    
    public void newLog(String message){
        DateTimeFormatter formatDate      = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String            dataHoje        = formatDate.format(LocalDateTime.now());
        
        DateTimeFormatter formatDateTime  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");     
        String            dataHoraAgora   = formatDateTime.format(LocalDateTime.now());
        
        String stringPath = String.format(pathLogDir + "collectorLog-%s.txt", dataHoje);
        
        File logFile = new File(stringPath);
        
        try {
            if (!logFile.exists()) {
                System.out.println("Criando um arquivo para armazenar o log");
                logFile.createNewFile();
            }
            FileWriter arq = new FileWriter(logFile, true);

            try (BufferedWriter bfwriter = new BufferedWriter(arq)) {
                bfwriter.write(dataHoraAgora + ".........." + message);
                bfwriter.newLine();             
            }

        } catch (IOException e) {
            System.out.println("Na hora de fazer o log deu erro!!!");
        }
    } 
}