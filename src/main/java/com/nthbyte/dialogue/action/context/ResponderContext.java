package com.nthbyte.dialogue.action.context;

import org.bukkit.entity.Player;

public class ResponderContext {

    private Player responder;

    public ResponderContext(Player responder){
        this.responder = responder;
    }

    public boolean isValid(){
        return responder != null;
    }

    public Player getResponder() {
        return responder;
    }

}
