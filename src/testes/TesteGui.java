/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.io.IOException;
import principal.AplicacaoPrincipal;
import principal.MainServer;

/**
 *
 * @author Geeo
 */
public class TesteGui {

    public static void main(String[] args) throws IOException {
        AplicacaoPrincipal clientApp = new AplicacaoPrincipal();
        //AplicacaoPrincipal clientApp2 = new AplicacaoPrincipal();
        //AplicacaoPrincipal clientApp3 = new AplicacaoPrincipal();
        MainServer serverApp = new MainServer();

        clientApp.run();
      //  clientApp2.run();
        //clientApp3.run();

        serverApp.run();
    }
}
