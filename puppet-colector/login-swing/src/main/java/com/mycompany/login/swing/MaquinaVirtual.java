package com.mycompany.login.swing;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author deusf
 */
public class MaquinaVirtual {

    private Integer id;
    private Integer fkAdmin;
    private String hostName;
    private String userLogin;
    private String senha;
    private String ip;
    private Integer disco;
    private Integer ram;
    private String processador;

    public MaquinaVirtual() {
        this.processador = "";
    }

    @Override
    public String toString() {
        return String.format(""
                + "ID: %d\n"
                + "fkAdmin: %d\n"
                + "Host name: %s\n"
                + "User login: %s\n"
                + "Senha: %s\n"
                + "IP: %s\n"
                + "Armazenamento: %d\n"
                + "Memória ram: %d\n"
                + "Processador: %s\n",
                id, fkAdmin,
                hostName,
                userLogin,
                senha, ip,
                disco,
                ram,
                processador);
    }

    Connection config = new Connection();
    JdbcTemplate template = new JdbcTemplate(config.getDataSource());

    public static void main(String[] args) {
        MaquinaVirtual mv = new MaquinaVirtual();
        mv.updateMaquina();
        System.out.println(mv);
    }

    public void updateMaquina() {
        Looca looca = new Looca();
        Processador processadorMethod = new Processador();
        Sistema sistema = new Sistema();
        DiscosGroup grupoDeDiscos = new DiscosGroup();
        Memoria memoria = new Memoria();

        // Memória total e armazenamento total convertida para Byte
        Integer memoriaTotal = (int) (memoria.getTotal() / 1024);
        Integer discoTotal = (int) (grupoDeDiscos.getTamanhoTotal() / 1024);

        // Formatando nome do processador
        String processadorNome = String.format("%s %s %d",
                processadorMethod.getFabricante(),
                processadorMethod.getNome(),
                processadorMethod.getNumeroCpusLogicas()
        );

        // Setando os dados dentro das variaveis da classe MaquinaVirtual
        setProcessador(processadorNome);
        setRam(memoriaTotal);
        setDisco(discoTotal);

        // Para capturar o IP e HostName, precisei passar a condição
        // try e catch
        try {
            // Importando o InetAddress
            InetAddress iÁddress;
            iÁddress = InetAddress.getLocalHost();

            // Capturando dado do HostName e do endereço IP
            String hostname = iÁddress.getHostName();
            String ipMachine = iÁddress.getHostAddress();

            // Setando 
            setHostName(hostname);
            setIp(ipMachine);

        } catch (UnknownHostException e) {
            System.out.println(e);
        }

    }

    public void updateTabela() {

        // Setando fkMaquinaVirtual da classe DadosColetados
        DadosColetados dados = new DadosColetados();

        System.out.println("O id está aqui: " + id);
        dados.setFkMaquinaVirtual(id);
        
        String updateStatement = "UPDATE maquinaVirtual SET"
                + " hostName= ?, ip=?, disco=?, ram=?, processador=?"
                + " WHERE id=?;";
        template.update(updateStatement,
                hostName,
                ip,
                disco,
                ram,
                processador,
                id);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkAdmin() {
        return fkAdmin;
    }

    public void setFkAdmin(Integer fkAdmin) {
        this.fkAdmin = fkAdmin;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getDisco() {
        return disco;
    }

    public void setDisco(Integer disco) {
        this.disco = disco;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }
}
