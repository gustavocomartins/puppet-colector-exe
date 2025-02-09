package com.mycompany.puppet.colector;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class TelaLogin extends javax.swing.JFrame {

    Connection config = new Connection("Azure");
    JdbcTemplate template = new JdbcTemplate(config.getDataSource());
    Util util = new Util();

    /**
     * Creates new form TelaLoginPUPPET
     */
    public TelaLogin() {
        initComponents();
    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JLabel();
        header = new javax.swing.JLabel();
        headerLogin = new javax.swing.JLabel();
        footer = new javax.swing.JLabel();
        painelLoginArea = new javax.swing.JPanel();
        textFieldUsername = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        labelUsername = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        labelTesteLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/background-server.jpg"))); // NOI18N

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/headerPuppet.jpg"))); // NOI18N

        headerLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loginHeaderPuppet.jpg"))); // NOI18N

        footer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/footerPuppet.jpg"))); // NOI18N

        painelLoginArea.setBackground(new java.awt.Color(48, 0, 70));
        painelLoginArea.setForeground(new java.awt.Color(53, 0, 70));

        textFieldUsername.setBackground(new java.awt.Color(255, 255, 255));
        textFieldUsername.setForeground(new java.awt.Color(0, 0, 0));
        textFieldUsername.setBorder(null);
        textFieldUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUsernameActionPerformed(evt);
            }
        });

        passwordField.setBackground(new java.awt.Color(255, 255, 255));
        passwordField.setForeground(new java.awt.Color(0, 0, 0));
        passwordField.setBorder(null);
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        labelUsername.setForeground(new java.awt.Color(255, 255, 255));
        labelUsername.setText("Username");

        labelSenha.setForeground(new java.awt.Color(255, 255, 255));
        labelSenha.setText("Senha");

        btnLogin.setBackground(new java.awt.Color(0, 0, 0));
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Entrar");
        btnLogin.setBorder(null);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setFocusable(false);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        labelTesteLogin.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout painelLoginAreaLayout = new javax.swing.GroupLayout(painelLoginArea);
        painelLoginArea.setLayout(painelLoginAreaLayout);
        painelLoginAreaLayout.setHorizontalGroup(
            painelLoginAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLoginAreaLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(painelLoginAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLoginAreaLayout.createSequentialGroup()
                        .addGroup(painelLoginAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSenha)
                            .addComponent(labelUsername)
                            .addGroup(painelLoginAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textFieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                .addComponent(passwordField)
                                .addComponent(labelTesteLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelLoginAreaLayout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107))))
        );
        painelLoginAreaLayout.setVerticalGroup(
            painelLoginAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelLoginAreaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(labelSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelTesteLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(headerLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(footer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(painelLoginArea, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(headerLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(painelLoginArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(footer))
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldUsernameActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        Boolean isLoginValido = false;
        
        String username = textFieldUsername.getText();
        String senha = String.valueOf(passwordField.getPassword());

        List<Usuario> user = util.retornarUsuario(username, senha);

        if (user.isEmpty()) {
            labelTesteLogin.setText("Usuário não encontrado.");
        } else if (user.get(0).username.equals(username)) {
            if (user.get(0).senha.equals(senha)) {
                labelTesteLogin.setText("email e senha corretos");
                isLoginValido = true;              
            } else {
                labelTesteLogin.setText("senha incorreta.");
            }
        }
        
        if(isLoginValido == true){
            if(util.isVmConfigured()){
                if(util.isKeyLocalRegistered()){
                    String key = util.getVmKey();
                    System.out.println("A chave é: " + key);
                    if(key.equals("Não encontrada localmente")== false){
                       List<MaquinaVirtual> vmAzureList = util.searchVmByKey();
                        if(vmAzureList.isEmpty() == false){
                            if(util.isVmComplete(vmAzureList.get(0))){
                                TelaDoUsuario telaUsuario = new TelaDoUsuario(user.get(0), vmAzureList.get(0));
                                this.dispose();
                                telaUsuario.setVisible(true);
                            }
                            else{
                                System.out.println("Completando o registro da máquina em nosso sistema...");
                                util.updateVmRegistrationOnAzure(vmAzureList.get(0));
                                TelaDoUsuario telaUsuario = new TelaDoUsuario(user.get(0), vmAzureList.get(0));
                                this.dispose();
                                telaUsuario.setVisible(true);
                            } 
                        }
                        else{
                            System.out.println("Sua maquina nao foi encontrada, registre novamente a chave.");
                            VMFinder telaVmFinder = new VMFinder(user.get(0));
                            this.dispose();
                            telaVmFinder.setVisible(true);
                        }
                    }
                    else{
                    VMFinder telaVmFinder = new VMFinder(user.get(0));
                    this.dispose();
                    telaVmFinder.setVisible(true);
                    }        
                }
                else{
                    System.out.println("Chave ainda não foi registrada localmente.");
                    VMFinder telaVmFinder = new VMFinder(user.get(0));
                    this.dispose();
                    telaVmFinder.setVisible(true);
                }
            }
            else{
                util.setUpVm();
                System.out.println("Sua máquina não foi configurada ainda.");
                VMFinder telaVmFinder = new VMFinder(user.get(0));
                this.dispose();
                telaVmFinder.setVisible(true);
            }
        }       
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel footer;
    private javax.swing.JLabel header;
    private javax.swing.JLabel headerLogin;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelTesteLogin;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JPanel painelLoginArea;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField textFieldUsername;
    // End of variables declaration//GEN-END:variables

}
