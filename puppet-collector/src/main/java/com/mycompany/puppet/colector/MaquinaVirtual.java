package com.mycompany.puppet.colector;
//import com.github.britooo.looca.api.group.sistema.Sistema;
//import com.github.britooo.looca.api.core.Looca;


/**
 *
 * @author deusf
 */
public class MaquinaVirtual {

    private Integer id;
    private Integer fkAdmin;
    private String  hostName;
    private String  nome;
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
                + "Nome: %s\n"
                + "IP: %s\n"
                + "Armazenamento: %d\n"
                + "Memória ram: %d\n"
                + "Processador: %s\n"
                + "keyVM: %s\n",
                id, fkAdmin,
                hostName,
                nome,
                ip,
                disco,
                ram,
                processador, 
                keyVM);
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

    public String getNome() {
        return nome;
    }

    public void setNome(String userLogin) {
        this.nome = userLogin;
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
