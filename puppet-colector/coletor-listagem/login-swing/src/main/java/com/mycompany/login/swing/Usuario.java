package com.mycompany.login.swing;

public class Usuario {
    Integer id;
    String username;
    String senha;

    public Usuario(Integer id, String username, String senha) {
        this.id = id;
        this.username = username;
        this.senha = senha;
    }

    public Usuario() {
    }
    

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    

    @Override
    public String toString() {
        return "\nUsuario "+ id + ", username = " + username + ",senha = " + senha;
    }
    
    
}
