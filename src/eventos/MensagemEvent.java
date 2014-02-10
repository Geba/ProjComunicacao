/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eventos;

import atomics.Mensagem;
import java.util.EventObject;
/**
 *
 * @author geeo
 */
public class MensagemEvent extends EventObject{

    /**
     *
     * @param mensagem
     */
    public MensagemEvent(Mensagem mensagem){
        super(mensagem);
    }
}
