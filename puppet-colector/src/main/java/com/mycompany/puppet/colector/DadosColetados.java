/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.puppet.colector;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.servicos.ServicosGroup;
import com.github.britooo.looca.api.group.sistema.Sistema;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OperatingSystem;

/**
 *
 * @author deusf
 */
public class DadosColetados {

    private Boolean isColetaAtiva;
    private Integer id;
    private Integer fkMaquinaVirtual;
    private Double usoDisco;
    private Double usoRam;
    private Double usoProcessador;
    private Date dataHora;

    Connection config = new Connection();
    JdbcTemplate template = new JdbcTemplate(config.getDataSource());

//    Esse metodo main está sendo usado apenas para teste de desenvolvimento
    public static void main(String[] args) {
//        API Looca
        Memoria memoria = new Memoria();

//        API OSHI
        MaquinaVirtual mv = new MaquinaVirtual();
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        FileSystem file = operatingSystem.getFileSystem();

//        Calculo de disco
        Long total = file.getFileStores().get(0).getTotalSpace();
        Long disponivel = file.getFileStores().get(0).getFreeSpace();
        Double usoDisco = (total.doubleValue() - disponivel.doubleValue()) * 100.0 / 1000.0 / 1000.0 / 1000.0 / 1000.0;

        Double usoDiscoConvertido = Math.round(usoDisco * 100.000) / 100.00;
        System.out.println(usoDiscoConvertido);

        Double memoriaUso = memoria.getEmUso().doubleValue();
        Double memoriaTotal = memoria.getTotal().doubleValue();

        Double usoMemoria = (memoriaUso / memoriaTotal) * 100.0;

        System.out.println("disco disponivel: " + disponivel);
        System.out.println("disco Total: " + total);
        System.out.println("Porcentagem: " + usoDisco);
    }

    public DadosColetados() {
        this.fkMaquinaVirtual = fkMaquinaVirtual;
        isColetaAtiva = true;
    }

    public void insercaoDados() {
        // Classes do OSHI

        MaquinaVirtual mv = new MaquinaVirtual();
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        FileSystem file = operatingSystem.getFileSystem();

        Memoria memoria = new Memoria();
        Processador processador = new Processador();

        System.out.println("Estou aqui id: " + mv.getId());
        Integer i = 0;
//        while (isColetaAtiva == true) {
            i++;
            try {

                // Importando Date e SimpleDateFormat para formatar a hora
                Date dataAtual = new Date();
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // Convertendo  Long da memória para double e trazendo 
                // porcentagem de uso
                Double memoriaUso = memoria.getEmUso().doubleValue();
                Double memoriaTotal = memoria.getTotal().doubleValue();
                Double usoMemoria = (memoriaUso / memoriaTotal) * 100.0;
                Double usoMemoriaConvertido = Math.round(usoMemoria * 100.0) / 100.0;

                // Pegando do OSHI dados do armazenamento(disco) e fazendo
                // o calculo de porcentagem
                Long total = file.getFileStores().get(0).getTotalSpace();
                Long disponivel = file.getFileStores().get(0).getFreeSpace();
                Double usoDisco = (total.doubleValue() - disponivel.doubleValue()) * 100.0 / 1000.0 / 1000.0 / 1000.0 / 1000.0;

                Double usoDiscoConvertido = Math.round(usoDisco * 100.0) / 100.0;

                // Pegando o uso de CPU em porcentagem
                Double usoCpu = processador.getUso();

                Double usoCpuConvertido = Math.round(usoCpu * 100.0) / 100.0;

                // Printando métricas ( CPU, DISCO, RAM )
                System.out.println("Uso de CPU %: " + usoCpuConvertido);
                System.out.println("Uso de Disco %: " + usoDiscoConvertido);
                System.out.println("Uso de Memória ram %: " + usoMemoriaConvertido);
                System.out.println(String.format(""
                        + "%d° inserção \n"
                        + "Hora atual: %s", i, dataAtual));
                
                // Variavel que guarda o comando de inserção
                String updateStatement = ""
                        + "INSERT INTO dadosColetados"
                        + "(fkMaquinaVirtual, usoDisco, usoRam, usoProcessador,"
                        + "dataHora) "
                        + "VALUES "
                        + "(?,?,?,?,?)";
                
                // Metodo que faz a inserção de dados no banco de dados
                template.update(updateStatement,
                        getFkMaquinaVirtual(),
                        usoDiscoConvertido,
                        usoMemoriaConvertido,
                        usoCpu,
                        dataAtual);
                
                // Delay de execução da função
                Thread.sleep(1000);

            } catch (Exception e) {
                System.out.println(e);
            }

//        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkMaquinaVirtual() {
        return fkMaquinaVirtual;
    }

    public void setFkMaquinaVirtual(Integer fkMaquinaVirtual) {
        this.fkMaquinaVirtual = fkMaquinaVirtual;
    }

    public Double getUsoDisco() {
        return usoDisco;
    }

    public void setUsoDisco(Double usoDisco) {
        this.usoDisco = usoDisco;
    }

    public Double getUsoRam() {
        return usoRam;
    }

    public void setUsoRam(Double usoRam) {
        this.usoRam = usoRam;
    }

    public Double getUsoProcessador() {
        return usoProcessador;
    }

    public void setUsoProcessador(Double usoProcessador) {
        this.usoProcessador = usoProcessador;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean getIsColetaAtiva() {
        return isColetaAtiva;
    }

    public void setIsColetaAtiva(Boolean isColetaAtiva) {
        this.isColetaAtiva = isColetaAtiva;
    }

}
