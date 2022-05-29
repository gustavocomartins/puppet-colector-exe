package com.mycompany.puppet.colector;

import java.util.List;
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
import java.util.Date;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OperatingSystem;

public class Util {

    private Boolean isColetaAtiva;
    private final Connection config;
    private final JdbcTemplate template;
    private final String userMaquina;
    private final String montagemLinux;
    private final String diretorioPuppet;
    private final String filePuppetKey;
    private final Log log;

    public Boolean getIsColetaAtiva() {
        return isColetaAtiva;      
    }

    public void setIsColetaAtiva(Boolean isColetaAtiva) {
        this.isColetaAtiva = isColetaAtiva;
    }

    public Util() {
        isColetaAtiva = false;
        config = new Connection("Azure");
        template = new JdbcTemplate(config.getDataSource());
        userMaquina = System.getProperty("user.name");
        montagemLinux = "/home/";
        diretorioPuppet = "PuppetCollector";
        filePuppetKey = "puppetKey.txt";
        log = new Log(System.getProperty("user.name"));
    }
    // ---------------------------------------------------------------------------------------
    // --Método para login de usuário---------------------------------------------------------
    // ---------------------------------------------------------------------------------------

    public List<Usuario> retornarUsuario(String username, String senha) {
        String queryUser = String.format("select id, username, senha from usuario where username = '%s' and senha = '%s';", username, senha);
        System.out.println(queryUser);
        List<Usuario> user = template.query(queryUser, new BeanPropertyRowMapper<>(Usuario.class));

        return user;
    }

    // ---------------------------------------------------------------------------------------
    // --Métodos para configuração local da VM (Diretórios e arquivo da chave)----------------
    // ---------------------------------------------------------------------------------------
    // Verifica se a VM está localmente configurada.
    public Boolean isVmConfigured() {
        Boolean dirCollector = isCollectorDirExists();
        Boolean dirLog = isLogDirExists();
        Boolean fileKey = isKeyFileExists();

        return dirCollector && dirLog && fileKey;
    }

    // Realiza a configuração local necessária.
    public void setUpVm() {
        createCollectorDir();
        createLogDir();
        createKeyFile();
        log.newLog("Maquina configurada.");
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
    public void createCollectorDir() {
        Path path = Paths.get(montagemLinux + userMaquina + "/" + diretorioPuppet);
        try {
            Files.createDirectory(path);
            log.newLog("Diretorio PuppetCollector criado com sucesso.");
        } catch (IOException e) {
            log.newLog("Erro ao tentar criar o diretorio PuppetCollector");
            System.out.println(String.format("Erro ao criar diretório - %s", path));
        }
    }

    // ---------------------------------------------------------------------------------------
    // --Métodos para o diretório de logs-----------------------------------------------------
    // ---------------------------------------------------------------------------------------
    public Boolean isLogDirExists() {
        File puppetDir = new File(montagemLinux + userMaquina + "/" + diretorioPuppet + "/" + "collectorLogs");
        return puppetDir.exists();
    }

    public void createLogDir() {
        Path path = Paths.get(montagemLinux + userMaquina + "/" + diretorioPuppet + "/" + "collectorLogs");
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            System.out.println(String.format("Erro ao criar diretório - %s", path));
        }
    }

    // ---------------------------------------------------------------------------------------
    // --Métodos para o arquivo puppetKey.txt-------------------------------------------------
    // ---------------------------------------------------------------------------------------
    // Verificar se o arquivo existe.
    public Boolean isKeyFileExists() {
        String pathPuppetKey = montagemLinux + userMaquina + "/" + diretorioPuppet + "/" + filePuppetKey;
        File puppetKey = new File(pathPuppetKey);
        return puppetKey.exists();
    }

    public void createKeyFile() {
        String pathKeyFile = montagemLinux + userMaquina + "/" + diretorioPuppet + "/" + filePuppetKey;
        Path path = Paths.get(pathKeyFile);

        try {
            Files.createFile(path);
            log.newLog("Arquivo ketVM.txt criado com sucesso.");
        } catch (IOException e) {
            log.newLog("Erro ao criar arquivo kryVM.txt");
            System.out.println("Erro ao criar arquivo " + path.toString());
        }
    }

    // Verificar de o arquivo possui chave registrada localmente.
    public Boolean isKeyLocalRegistered() {
        String pathKeyFile = montagemLinux + userMaquina + "/" + diretorioPuppet + "/" + filePuppetKey;
        Boolean isRegistered = false;

        try ( BufferedReader bfrReader = new BufferedReader(new FileReader(pathKeyFile))) {
            String keyVM = bfrReader.readLine();
            if (keyVM != null) {
                System.out.println("keyVM: " + keyVM);
                isRegistered = true;
            } else {
                System.out.println("Sua chave não foi informada ou não está registrada na primeira linha do arquivo puppetLey.txt.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return isRegistered;
    }

    // Registrar chave no arquivo puppetKey.txt.
    public void registerKeyVMLocal(String keyVM) {
        String pathPuppetKey = montagemLinux + userMaquina + "/" + diretorioPuppet + "/" + filePuppetKey;
        File puppetKey = new File(pathPuppetKey);

        try ( PrintWriter writer = new PrintWriter(new FileOutputStream(puppetKey))) {
            writer.println(keyVM.toUpperCase());
            System.out.println("Novo registro local de chave identificadora realizado com sucesso.");
            writer.close();
            log.newLog("Nova chave de identificacao de maquina cadastrada localmente.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            log.newLog("Erro ao gravar chave de identificacao");
        }

    }

    // Buscar a chave no arquivo puppetKey.txt.
    public String getVmKey() {
        String pathKeyFile = montagemLinux + userMaquina + "/" + diretorioPuppet + "/" + filePuppetKey;
        List<String> keyVmList = new ArrayList<>();
        String key = "Não encontrada localmente";

        try ( BufferedReader bfrReader = new BufferedReader(new FileReader(pathKeyFile))) {
            String keyRead = bfrReader.readLine();
            if (keyRead != null) {
                keyVmList.add(keyRead);
                System.out.println("keyVM: " + keyVmList.get(0));
                key = keyVmList.get(0);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return key;
    }

    // ---------------------------------------------------------------------------------------
    // --Métodos da Máquina Virtual-----------------------------------------------------------
    // ---------------------------------------------------------------------------------------
    // Buscar no banco a máquina através da chave registrada localmente.
    public List<MaquinaVirtual> searchVmByKey() {

        String key = getVmKey();
        List<MaquinaVirtual> vmList;

        String query = String.format("select * from [dbo].[maquinaVirtual] where keyVm = '%s';", key);
        System.out.println(query);
        vmList = template.query(query, new BeanPropertyRowMapper<>(MaquinaVirtual.class));
        
        return vmList;
    }

    // Verificar se uma vm está com o registro completo.
    public Boolean isVmComplete(MaquinaVirtual vm) {
        Integer ram = vm.getRam();
        Integer disco = vm.getDisco();
        String processador = vm.getProcessador();
        String ip = vm.getIp();
        String hostname = vm.getHostName();

        return ram != null && disco != null && processador != null && ip != null && hostname != null;
    }

    // Atualiza o registro da vm no banco de dados Azure da Puppet.
    public void updateVmRegistrationOnAzure(MaquinaVirtual vm) {
        updateObjectVm(vm);

        String query = "UPDATE maquinaVirtual SET "
                + "hostName= ?, ip=?, disco=?, ram=?, processador= ? "
                + "WHERE keyVm = ?";

        template.update(query,
                vm.getHostName(),
                vm.getIp(),
                vm.getDisco(),
                vm.getRam(),
                vm.getProcessador(),
                vm.getKeyVM());
        
        log.newLog("Cadastro de maquina na base Puppet Azure atualizado com sucesso.");
    }

    // Atualiza o objeto VM instanciado ao buscar na Azure através da chave.
    public void updateObjectVm(MaquinaVirtual vm) {
        Processador processadorVm = new Processador();
        DiscosGroup grupoDeDiscosVm = new DiscosGroup();
        Memoria memoriaVm = new Memoria();

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
        if (key != null) {
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

    public DadosColetados coletarDados(MaquinaVirtual vm) {
        System.out.println("Chamando util.coletarDados()...");

        DadosColetados dados = new DadosColetados(vm);
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        FileSystem file = operatingSystem.getFileSystem();
        Memoria memoria = new Memoria();
        Processador processador = new Processador();

        try {
            Date dataAtual = new Date(); // dataHora

            Double memoriaUso = memoria.getEmUso().doubleValue();
            Double memoriaTotal = memoria.getTotal().doubleValue();
            Double usoMemoria = (memoriaUso / memoriaTotal) * 100.0;
            Double usoMemoriaConvertido = Math.round(usoMemoria * 100.0) / 100.0; //usoRam

            Long discoTotal = file.getFileStores().get(0).getTotalSpace();
            Long discoDisponivel = file.getFileStores().get(0).getFreeSpace();
            Double usoDisco = (discoTotal.doubleValue() - discoDisponivel.doubleValue()) * 100.0 / 1000.0 / 1000.0 / 1000.0 / 1000.0;
            Double usoDiscoConvertido = Math.round(usoDisco * 100.0) / 100.0; //usoDisco

            Double usoCpu = processador.getUso();
            Double usoCpuConvertido = Math.round(usoCpu * 100.0) / 100.0; //usoCPU

            dados.setFkMaquinaVirtual(vm.getId());
            dados.setUsoDisco(usoDiscoConvertido);
            dados.setUsoRam(usoMemoriaConvertido);
            dados.setUsoProcessador(usoCpuConvertido);
            dados.setDataHora(dataAtual);

            System.out.println("\nUso de memoria Ram...: " + usoMemoriaConvertido);
            System.out.println("Uso de Disco.........: " + usoDiscoConvertido);
            System.out.println("Uso de CPU...........: " + usoCpuConvertido);
            System.out.println("DataHora da coleta...: " + dataAtual);
            
            log.newLog("Coleta de dados realizada com sucesso.");

        } catch (Exception e) {
            log.newLog("Erro ao tentar coletar de dados de maquina.");
            System.out.println(e);
        }

        return dados;
    }

    public void inserirDados(DadosColetados dados) {
        System.out.println("Chamando util.inserirDados()...");
        String updateStatement = ""
                + "INSERT INTO dadosColetados"
                + "(fkMaquinaVirtual, usoDisco, usoRam, usoProcessador,"
                + "dataHora) "
                + "VALUES "
                + "(?,?,?,?,?)";

        template.update(updateStatement,
                dados.getFkMaquinaVirtual(),
                dados.getUsoDisco(),
                dados.getUsoRam(),
                dados.getUsoProcessador(),
                dados.getDataHora());

        log.newLog("Registro de coleta cadastrado na base oficial Puppet na Azure.");

    }

    public void inserirDadosBackup(DadosColetados dados) {
        Connection configMysql = new Connection("Mysql");
        JdbcTemplate template1 = new JdbcTemplate(configMysql.getDataSource());
        String updateStatement = ""
                + "INSERT INTO dadosColetados"
                + "(fkMaquinaVirtual, usoDisco, usoRam, usoProcessador,"
                + "dataHora) "
                + "VALUES "
                + "(?,?,?,?,?)";

        template.update(updateStatement,
                dados.getFkMaquinaVirtual(),
                dados.getUsoDisco(),
                dados.getUsoRam(),
                dados.getUsoProcessador(),
                dados.getDataHora());
        
        log.newLog("Registro de coleta cadastrado na base de backup local MySQL.");
    }
}
