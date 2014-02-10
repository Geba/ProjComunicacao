package atomics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author geeo
 */
public class Mensagem {

    private String mensagem;
    private String usuario;
    private String hora;

    public Mensagem(String mensagem, String usuario, String hora) {
        this.mensagem = mensagem;
        this.usuario = usuario;
        this.hora = hora;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getHora() {
        return hora;
    }
    
}
