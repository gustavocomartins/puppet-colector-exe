/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login.swing;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
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

    Connection config = new Connection();
    JdbcTemplate template = new JdbcTemplate(config.getDataSource());

    public static void main(String[] args) {
        DadosColetados dados = new DadosColetados();
        dados.insercaoDados();

    }

    public void insercaoDados() {
        MaquinaVirtual mv = new MaquinaVirtual();
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        FileSystem file = operatingSystem.getFileSystem();

        Date x = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Looca looca = new Looca();
        Memoria memoria = new Memoria();
        Processador processador = new Processador();

        DiscosGroup grupoDeDiscos = new DiscosGroup();
        looca.getProcessador();
        memoria.getEmUso();
        looca.getProcessador().getUso();
        System.out.println();
        Long total = file.getFileStores().get(0).getTotalSpace() / 1000 / 1000;
        Long livre = file.getFileStores().get(0).getFreeSpace()/ 1000 /1000;
        Long uso = file.getFileStores().get(0).getUsableSpace()/ 1000 /1000;
        System.out.println(total - uso);
        System.out.println("Espaço livre GB: " + livre);
        System.out.println(simpleDate.format(x));
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
}
