/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import core.Core;
import gui.GuiPrincipal;
/**
 *
 * @author Geeo
 */
public class AplicacaoPrincipal {
    public static void main(String[] args){
        Core core  = new Core();
        GuiPrincipal gui = new GuiPrincipal(core);
        gui.setVisible(true);
    }
}
