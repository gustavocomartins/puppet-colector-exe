/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login.swing;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.servicos.ServicosGroup;
import com.github.britooo.looca.api.group.sistema.Sistema;
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

    private Integer id;
    private Integer fkMaquinaVirtual;
    private Double usoDisco;
    private Double usoRam;
    private Double usoProcessador;
    private Date dataHora;
    private Boolean isLogado;

    Connection config = new Connection();
    JdbcTemplate template = new JdbcTemplate(config.getDataSource());

    public static void main(String[] args) {
        Memoria memoria = new Memoria();

        Double memoriaUso = memoria.getEmUso().doubleValue();
        Double memoriaTotal = memoria.getTotal().doubleValue();

        Double usoMemoria = (memoriaUso / memoriaTotal) * 100.0;

        System.out.println("Memória uso: " + memoriaUso);
        System.out.println("Memoria Total: " + memoriaTotal);
        System.out.println("Porcentagem: " + usoMemoria);
    }

    public DadosColetados() {
        this.fkMaquinaVirtual = fkMaquinaVirtual;
    }
    
    

    public void insercaoDados() {
        // Classes do OSHI

        MaquinaVirtual mv = new MaquinaVirtual();
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        FileSystem file = operatingSystem.getFileSystem();

        Looca looca = new Looca();
        Memoria memoria = new Memoria();
        Processador processador = new Processador();
        DiscosGroup grupoDeDiscos = new DiscosGroup();
        Sistema sistema = new Sistema();
        Sistema teste = new Sistema();
        DadosColetados dados = new DadosColetados();
        ServicosGroup servicosGroup = new ServicosGroup();

        while (true) {

            try {
                for (int i = 1; i > 0; i++) {
                    
                    setFkMaquinaVirtual(mv.getId());
                    
                    // Importando Date e SimpleDateFormat para formatar a hora
                    Date dataAtual = new Date();
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    
                    // Convertendo  Long da memória para double e trazendo 
                    // porcentagem de uso
                    Double memoriaUso = memoria.getEmUso().doubleValue();
                    Double memoriaTotal = memoria.getTotal().doubleValue();
                    Double usoMemoria = (memoriaUso / memoriaTotal) * 100.0;

                    // Pegando do OSHI dados do armazenamento(disco) e fazendo
                    // o calculo de porcentagem
                    Long total = file.getFileStores().get(0).getTotalSpace();
                    Long uso = file.getFileStores().get(0).getUsableSpace();
                    Double usoDisco = (uso.doubleValue() / total.doubleValue()) * 100.0;
                    
                    // Pegando o uso de CPU em porcentagem
                    Double usoCpu = processador.getUso();
                    
                    //Printando uso de memória ram atual
                    System.out.println(String.format(""
                            + "Memoria em uso: %.2f\n"
                            + "", usoMemoria));
                    
                    
                    System.out.println(getFkMaquinaVirtual());
                    
                    System.out.println(String.format(""
                            + "%d° inserção \n"
                            + "Hora atual: %s", i, dataAtual));
                    String updateStatement = ""
                            + "INSERT INTO dadosColetados"
                            + "(fkMaquinaVirtual, usoDisco, usoRam, usoProcessador,"
                            + "dataHora) "
                            + "VALUES "
                            + "(?,?,?,?,?)";
                    template.update(updateStatement,
                            getFkMaquinaVirtual(),
                            usoDisco,
                            usoMemoria,
                            usoCpu,
                            dataAtual);
                    Thread.sleep(1000);
                }

            } catch (Exception e) {
                System.out.println(e);
            }

        }
//        Long total = file.getFileStores().get(0).getTotalSpace() / 1000;
//        Long livre = file.getFileStores().get(0).getFreeSpace() / 1000;
//        Long uso = file.getFileStores().get(0).getUsableSpace() / 1000;
//        System.out.println("Get total Inodes " + file.getFileStores().get(0).getLogicalVolume());
//        System.out.println("Espaço livroe em GB: " + (total - uso));
//        System.out.println("Espaço livre GB: " + livre);
//        System.out.println(simpleDate.format(dataAtual));
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

    public Boolean getIsLogado() {
        return isLogado;
    }

    public void setIsLogado(Boolean isLogado) {
        this.isLogado = isLogado;
    }

}
