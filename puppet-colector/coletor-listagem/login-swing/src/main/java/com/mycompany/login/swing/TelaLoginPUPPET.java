package com.mycompany.login.swing;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class TelaLoginPUPPET extends javax.swing.JFrame {
    Connection config = new Connection();        
    JdbcTemplate template = new JdbcTemplate(config.getDataSource());
    /**
     * Creates new form TelaLoginPUPPET
     */
    public TelaLoginPUPPET() {
        initComponents();
    }

    /**
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
        textFieldEmail = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        labelUsername = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        labelTesteLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/background-server.jpg"))); // NOI18N

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/headerPuppet.jpg"))); // NOI18N

        headerLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loginHeaderPuppet.jpg"))); // NOI18N

        footer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/footerPuppet.jpg"))); // NOI18N

        painelLoginArea.setBackground(new java.awt.Color(48, 0, 70));
        painelLoginArea.setForeground(new java.awt.Color(53, 0, 70));

        textFieldEmail.setBackground(new java.awt.Color(255, 255, 255));
        textFieldEmail.setForeground(new java.awt.Color(0, 0, 0));
        textFieldEmail.setBorder(null);
        textFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldEmailActionPerformed(evt);
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
                                .addComponent(textFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
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
                .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void textFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldEmailActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        MaquinaVirtual mv = new MaquinaVirtual();
        
        String username = textFieldEmail.getText();
        String senha = String.valueOf(passwordField.getPassword());
        System.out.println(username + ' ' + senha); 
        
        String queryUser = String.format(""
                + "select id, fkAdmin, hostName, userLogin, senha, ip, disco, ram, processador from maquinaVirtual where userLogin = '%s';", username);
        System.out.println(queryUser);       
        List<MaquinaVirtual> user = template.query(queryUser, new BeanPropertyRowMapper<>(MaquinaVirtual.class));               
        System.out.println(user);
        
        if(user.isEmpty()){  
            labelTesteLogin.setText("Usuário não encontrado.");
        }
        else if (user.get(0).getUserLogin().equals(username)){
            if(user.get(0).getSenha().equals(senha)){
                labelTesteLogin.setText("email e senha corretos" + user.get(0).getFkAdmin());
                mv.setFkAdmin(user.get(0).getFkAdmin());
                mv.setId(user.get(0).getId());
                mv.setUserLogin(user.get(0).getUserLogin());
                mv.setSenha(user.get(0).getSenha());
                
                
                mv.updateMaquina();
                System.out.println(mv);
                mv.updateTabela();
            }
            else{
                labelTesteLogin.setText("senha incorreta.");
            }
        };       
        
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
            java.util.logging.Logger.getLogger(TelaLoginPUPPET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLoginPUPPET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLoginPUPPET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLoginPUPPET.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLoginPUPPET().setVisible(true);
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
    private javax.swing.JTextField textFieldEmail;
    // End of variables declaration//GEN-END:variables

}
