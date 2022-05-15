package com.mycompany.puppet.colector;
//import com.github.britooo.looca.api.group.sistema.Sistema;
//import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
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
    private String  hostName;
    private String  userLogin;
    private String  ip;
    private Integer disco;
    private Integer ram;
    private String  processador;
    private String  keyVM;

    public MaquinaVirtual() {
        
    }

    @Override
    public String toString() {
        return String.format(""
                + "ID: %d\n"
                + "fkAdmin: %d\n"
                + "Host name: %s\n"
                + "User login: %s\n"
                + "IP: %s\n"
                + "Armazenamento: %d\n"
                + "Memória ram: %d\n"
                + "Processador: %s\n"
                + "keyVM: %s\n",
                id, fkAdmin,
                hostName,
                userLogin,
                ip,
                disco,
                ram,
                processador, 
                keyVM);
    }

    Connection config = new Connection();
    JdbcTemplate template = new JdbcTemplate(config.getDataSource());

    // public void main3(String[] args) {
    //     MaquinaVirtual mv = new MaquinaVirtual();
    //     mv.updateVmRegistration();
    //     System.out.println(mv);
    // }

    public void updateMaquina() {
        //Looca looca = new Looca();
        //Sistema sistema = new Sistema();
        
        Processador processadorVm   = new Processador();
        DiscosGroup grupoDeDiscosVm = new DiscosGroup();
        Memoria     memoriaVm       = new Memoria();

        // Memória Ram e Disco total convertida para Byte.
        Integer ramTotal = (int) (memoriaVm.getTotal() / 1024);
        Integer discoTotal = (int) (grupoDeDiscosVm.getTamanhoTotal() / 1024);

        // Formatando nome do processador.
        String processadorNome = String.format("%s %s %d",
                processadorVm.getFabricante(),
                processadorVm.getNome(),
                processadorVm.getNumeroCpusLogicas()
        );

        // Setando os dados dentro das variaveis da classe MaquinaVirtual
        setProcessador(processadorNome);
        setRam(ramTotal);
        setDisco(discoTotal);

        // Para capturar o IP e HostName, precisei passar a condição try e catch
        try {
            // Importando o InetAddress
            InetAddress iAddress;
            iAddress = InetAddress.getLocalHost();

            // Capturando dado do HostName e do endereço IP
            String hostname = iAddress.getHostName();
            String ipMachine = iAddress.getHostAddress();

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
        
        // Variavel com o código de update
        String updateStatement = "UPDATE maquinaVirtual SET "
                               + "hostName= ?, ip=?, disco=?, ram=?, processador=? "
                               + "WHERE id=?;";
        
        // Método que executa a query de update
        template.update(updateStatement,
                hostName,
                ip,
                disco,
                ram,
                processador,
                id);
        dados.insercaoDados();

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
    
    public String getKeyVM() {
        return keyVM;
    }

    public void setKeyVm(String keyVM) {
        this.keyVM = keyVM;
    }
}
