package com.mycompany.puppet.colector;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class IntegracaoSlack {

    Connection config = new Connection("Azure");
    JdbcTemplate con = new JdbcTemplate(config.getDataSource());
    Util reqMaq = new Util();

    public Integer consultaEmpresa() {
        List IdMaqBanco = con.queryForList("select hostname from maquinaVirtual where hostName = ? ORDER BY ID DESC",
                reqMaq.getVmKey());
        String fkVar = String.valueOf(IdMaqBanco.get(0)).replace("{Fk_EstMaq=", "").replace("}", "");
        System.out.println(fkVar);
        Integer fkVarInt = Integer.parseInt(fkVar);
        return fkVarInt;
    }

    public void enviaAlerta(Integer fkVarInt) {
        if (fkVarInt == 1) {
            System.out.println("caiu no 1");

        } else if (fkVarInt == 2) {
            System.out.println("caiu no 2");
        }

    }

    public void getEnviaAlertasCmMikeys(String tipoAlerta, String corAlerta, String hostName, String tipoMedicao, Double ramEmUso) throws Exception {
        String slcT1 = "xoxb-";
        String slcT12 = "3467541436532-";
        String slcT13 = "3524285250806-";
        String slcT14 = "kKCbdB97mrikoln6TSPuv5Dd";

        String msgAlerta = String.format("%s %s\n"
                + "Máquina: %s\n"
                + "%s Disponivel: %.2f GB",
                tipoAlerta, corAlerta, hostName, tipoMedicao, ramEmUso);

        Slack slack = Slack.getInstance();
        MethodsClient methods = slack.methods(slcT1 + slcT12 + slcT13 + slcT14);

        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("#monitoramento") // Use a channel ID `C1234567` is preferrable
                .text(msgAlerta)
                .build();
        ChatPostMessageResponse response = methods.chatPostMessage(request);

        System.out.println(response);
        System.out.println(response.getError());

    }

    public void getEnviaAlertasBurgerqueen(String tipoAlerta, String corAlerta, String hostName, String tipoMedicao, Double ramEmUso) throws Exception {
        String slcT1 = "xoxb-";
        String slcT12 = "3467541436532-";
        String slcT13 = "3524285250806-";
        String slcT14 = "kKCbdB97mrikoln6TSPuv5Dd";

        String msgAlerta = String.format("%s %s\n"
                + "Máquina: %s\n"
                + "%s Disponivel: %.2f GB",
                tipoAlerta, corAlerta, hostName, tipoMedicao, ramEmUso);

        Slack slack = Slack.getInstance();
        MethodsClient methods = slack.methods(slcT1 + slcT12 + slcT13 + slcT14);

        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("#monitoramento-empresa-2") // Use a channel ID `C1234567` is preferrable
                .text(msgAlerta)
                .build();
        ChatPostMessageResponse response = methods.chatPostMessage(request);

        System.out.println(response);
        System.out.println(response.getError());

    }
}
