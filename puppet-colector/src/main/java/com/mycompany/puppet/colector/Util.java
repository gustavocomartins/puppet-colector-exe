package com.mycompany.puppet.colector;

import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class Util {

    private final Connection config;
    private final JdbcTemplate template;
    private final String userMaquina;
    private final String montagemLinux;
    private final String diretorioPuppet;
    private final String filePuppetKey;

    public Util() {
        config          = new Connection();
        template        = new JdbcTemplate(config.getDataSource());
        userMaquina     = System.getProperty("user.name");       
        montagemLinux   = "/home/";
        diretorioPuppet = "PuppetCollector";
        filePuppetKey   = "puppetKey.txt"; 
    }
    // ---------------------------------------------------------------------------------------
    // --Método para login de usuário---------------------------------------------------------
    // ---------------------------------------------------------------------------------------
    
    public List<Usuario> retornarUsuario(String username, String senha) {
        String queryUser = String.format("select id, username, senha from [dbo].[usuario] where username = '%s';", username);
        System.out.println(queryUser);
        List<Usuario> user = template.query(queryUser, new BeanPropertyRowMapper<>(Usuario.class));

        return user;
    }
    
    // ---------------------------------------------------------------------------------------
    // --Métodos para configuração local da VM (Diretórios e arquivo da chave)----------------
    // ---------------------------------------------------------------------------------------
    
    // Verifica se a VM está localmente configurada.
    public Boolean isVmConfigured(){
        Boolean dirCollector  = isCollectorDirExists();
        Boolean dirLog        = isLogDirExists();
        Boolean fileKey       = isKeyFileExists();
        
        return dirCollector && dirLog && fileKey;
    }
    
    // Realiza a configuração local necessária.
    public void setUpVm(){
        createCollectorDir();
        createLogDir();
        createKeyFile();
    }
    
    // ---------------------------------------------------------------------------------------
    // --Métodos para o diretório PuppetCollector---------------------------------------------
    // ---------------------------------------------------------------------------------------
    
    // Verifica se o diretório Collector existe.
    public Boolean isCollectorDirExists() {
        File puppetDir = new File(montagemLinux + userMaquina + "/" + diretorioPuppet);              
        return puppetDir.exists();
    }

    // Cria o diretório Collector
    public void createCollectorDir(){
        Path path = Paths.get(montagemLinux + userMaquina + "/" + diretorioPuppet);
        try {
            Files.createDirectory(path);               
        }       
        catch (IOException e) {
            System.out.println(String.format("Erro ao criar diretório - %s", path));
        }           
    }
    
    // ---------------------------------------------------------------------------------------
    // --Métodos para o diretório de logs-----------------------------------------------------
    // ---------------------------------------------------------------------------------------
    
    public Boolean isLogDirExists(){
        File puppetDir = new File(montagemLinux + userMaquina + "/" + diretorioPuppet + "/" + "collectorLogs");              
        return puppetDir.exists();
    }
    
    public void createLogDir(){
        Path path = Paths.get(montagemLinux + userMaquina + "/" + diretorioPuppet + "/" + "collectorLogs");
        try {
            Files.createDirectory(path);               
        }       
        catch (IOException e) {
            System.out.println(String.format("Erro ao criar diretório - %s", path));
        }  
    }
    
    // ---------------------------------------------------------------------------------------
    // --Métodos para o arquivo puppetKey.txt-------------------------------------------------
    // ---------------------------------------------------------------------------------------
    
    // Verificar se o arquivo existe.
    public Boolean isKeyFileExists(){
        String pathPuppetKey = montagemLinux + userMaquina + "/" +  diretorioPuppet + "/" + filePuppetKey;
        File puppetKey = new File(pathPuppetKey); 
        return puppetKey.exists();
    }
    
    public void createKeyFile(){
        String pathKeyFile = montagemLinux + userMaquina + "/" +  diretorioPuppet + "/" + filePuppetKey;
        Path path = Paths.get(pathKeyFile);  
        
        try {
            Files.createFile(path);  
        }       
        catch (IOException e) {
            System.out.println("Erro ao criar arquivo " + path.toString());
        }
    }
    
    // Verificar de o arquivo possui chave registrada localmente.
    public Boolean isKeyLocalRegistered(){
        String pathKeyFile = montagemLinux + userMaquina + "/" +  diretorioPuppet + "/" + filePuppetKey;
        Boolean isRegistered = false;
        
        try(BufferedReader bfrReader = new BufferedReader(new FileReader(pathKeyFile))){
            String keyVM = bfrReader.readLine();
            if(keyVM != null){
                System.out.println("keyVM: " + keyVM);
                isRegistered = true;
            }
            else{
                System.out.println("Sua chave não foi informada ou não está registrada na primeira linha do arquivo puppetLey.txt.");
            }
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return isRegistered;
    }
    
    // Registrar chave no arquivo puppetKey.txt.
    public void registerKeyVMLocal(String keyVM){
        String pathPuppetKey = montagemLinux + userMaquina + "/" +  diretorioPuppet + "/" + filePuppetKey;
        File puppetKey = new File(pathPuppetKey); 
        
        try(PrintWriter writer = new PrintWriter(new FileOutputStream(puppetKey))) {
            writer.println(keyVM.toUpperCase());
            System.out.println("Novo registro local de chave identificadora realizado com sucesso.");
            writer.close(); 
        } 
        catch(FileNotFoundException e){ 
            System.out.println("Error: " + e.getMessage());
        }

    }   
        
    // Buscar a chave no arquivo puppetKey.txt.
    public String getVmKey(){
        String pathKeyFile = montagemLinux + userMaquina + "/" +  diretorioPuppet + "/" + filePuppetKey;
        List<String> keyVmList = new ArrayList<>();
        String key = "Não encontrada localmente";
        
        try(BufferedReader bfrReader = new BufferedReader(new FileReader(pathKeyFile))){
            String keyRead = bfrReader.readLine();
            if(keyRead != null){
                keyVmList.add(keyRead);
                System.out.println("keyVM: " + keyVmList.get(0));              
                key = keyVmList.get(0);
            }
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return key;
    }
    
    // ---------------------------------------------------------------------------------------
    // --Métodos da Máquina Virtual-----------------------------------------------------------
    // ---------------------------------------------------------------------------------------
   
    // Buscar no banco a máquina através da chave registrada localmente.
    public List<MaquinaVirtual> searchVmByKey(){
       
        String key = getVmKey(); 
        
        List<MaquinaVirtual>vmList;        
       
        String query = String.format("select * from [dbo].[maquinaVirtual] where keyVm = '%s';", key);
        System.out.println(query);
        vmList = template.query(query, new BeanPropertyRowMapper<>(MaquinaVirtual.class));
        
        
        return vmList;
    }
    
    // Verificar se uma vm está com o registro completo.
    public Boolean isVmComplete(MaquinaVirtual vm){
        Integer ram         = vm.getRam();
        Integer disco       = vm.getDisco();
        String  processador = vm.getProcessador();
        String  ip          = vm.getIp();
        String  hostname    = vm.getHostName();
        
        return ram != null && disco != null && processador != null && ip != null && hostname != null;
    }    
    
    // Atualiza o registro da vm no banco de dados Azure da Puppet.
    public void updateVmRegistrationOnAzure(MaquinaVirtual vm){
        updateObjectVm(vm);
        
        String query = "UPDATE maquinaVirtual SET " +
                       "hostName= ?, ip=?, disco=?, ram=?, processador= ? " +
                       "WHERE keyVm = ?";
        
        template.update(query,
                vm.getHostName(),
                vm.getIp(),
                vm.getDisco(),
                vm.getRam(),
                vm.getProcessador(),
                vm.getKeyVM());          
    }
    
    // Atualiza o objeto VM instanciado ao buscar na Azure através da chave.
    public void updateObjectVm( MaquinaVirtual vm){  
        Processador processadorVm   = new Processador();
        DiscosGroup grupoDeDiscosVm = new DiscosGroup();
        Memoria     memoriaVm       = new Memoria();  
        
        Integer ramTotal = (int) (memoriaVm.getTotal() / 1024);
        Integer discoTotal = (int) (grupoDeDiscosVm.getTamanhoTotal() / 1024);
        String processadorNome = String.format("%s %s %d",
                processadorVm.getFabricante(),
                processadorVm.getNome(),
                processadorVm.getNumeroCpusLogicas()
        );      
        vm.setRam(ramTotal);
        vm.setDisco(discoTotal);
        vm.setProcessador(processadorNome);
        
        String key = getVmKey();       
        if(key != null){
            vm.setKeyVm(key);
        }
        
        try {
            // Importando o InetAddress
            InetAddress iAddress;
            iAddress = InetAddress.getLocalHost();

            // Capturando dado do HostName e do endereço IP
            String hostname = iAddress.getHostName();
            String ipMachine = iAddress.getHostAddress();

            // Setando 
            vm.setHostName(hostname);
            vm.setIp(ipMachine);

        } catch (UnknownHostException e) {
            System.out.println(e);
        }
        
        System.out.println("Objeto de classe MáquinaVirtual foi atualizado:");
        System.out.println(vm);      
    }
    
    
}
