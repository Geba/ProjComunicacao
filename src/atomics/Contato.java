/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package atomics;

/**
 *
 * @author geeo
 */
public class Contato {
    private long id;
    private String nome;
    private String photo;
    private String apelido;

    public Contato(long id, String nome, String photo, String apelido) {
        this.id = id;
        this.nome = nome;
        this.photo = photo;
        this.apelido = apelido;
    }
    
}
