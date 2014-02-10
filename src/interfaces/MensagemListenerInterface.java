/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;
import eventos.MensagemEvent;
import java.util.EventListener;
/**
 *
 * @author geeo
 */
public interface MensagemListenerInterface extends EventListener {
    public void novaMensagem(MensagemEvent msg);
}
