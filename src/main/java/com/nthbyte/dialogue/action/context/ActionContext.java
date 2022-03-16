package com.nthbyte.dialogue.action.context;

import org.bukkit.entity.Player;

/**
 * Container that has data/information that an action needs to run.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.3.0.0
 */
public class ActionContext {

    private Player responder;

    public ActionContext(Player responder){
        this.responder = responder;
    }

    public boolean isValid(){
        return responder != null;
    }

    public Player getResponder() {
        return responder;
    }

}
