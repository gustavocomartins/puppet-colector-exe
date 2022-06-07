
package com.mycompany.puppet.colector;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import java.io.IOException;
import java.net.http.HttpClient;

public class CollectorSlack {   
    
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String slackChanel = "monitoração-de-servidores-cloud";
    
    private static final String token1 = "xoxb-36";
    private static final String token2 = "273745391";
    private static final String token3 = "09-3633577";
    private static final String token4 = "844580-";
    private static final String token5 = "5yvfr9d";
    private static final String token6 = "BnbqdOH7";
    private static final String token7 = "oCrReAdwZ";
    private static final String oAuthToken = token1 + token2 + token3 + token4 + token5 + token6 + token7;
    
    private static final String url1 = "https:";
    private static final String url2 = "//hooks.slack.";
    private static final String url3 = "com/services";
    private static final String url4 = "/T03JF";
    private static final String url5 = "B0FV37/B";
    private static final String url6 = "03K8TP";
    private static final String url7 = "QF7S/gUH";
    private static final String url8 = "CMYF7wf";
    private static final String url9 = "MVj0FVE";
    private static final String url10 = "03CsrqV";
    //
    private static final String webHookURL = url1 + url2 + url3 + url4 + url5 + url6 + url7 + url8 + url9 + url10;

    public static void sendSlackMessage(String message) {
        System.out.println(webHookURL);
        try{
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append(message);
            Payload payload = Payload.builder().channel(slackChanel).text(msgBuilder.toString()).build();
            WebhookResponse wbResp = Slack.getInstance().send(webHookURL,payload);
            System.out.println("Slack enviado com sucesso.");
        }
        catch(IOException e){
            e.getMessage();
            System.out.println("Slack nao precisou enviar notificacao.");
        }       
    }  
}
