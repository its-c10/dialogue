package com.nthbyte.dialogue.action.context;

import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Container that provides a given action with specific information/data it needs to run successfully.
 *
 * @author <a href="linktr.ee/c10_">Caleb Owens</a>
 * @version 1.3.0.0
 */
public class ActionContext<T> {

    /**
     * All inputs within this dialogue that have been stored via Action.STORE_INPUT
     */
    protected Map<String, String> inputStorage;
    protected Player responder;
    protected T data = null;

    public ActionContext(T data){
        this.data = data;
    }

    public ActionContext(){ }

    public Player getResponder() {
        return responder;
    }

    public void setResponder(Player responder) {
        this.responder = responder;
    }

    public Map<String, String> getInputStorage() {
        return inputStorage;
    }

    public void setInputStorage(Map<String, String> inputStorage) {
        this.inputStorage = inputStorage;
    }

    public boolean hasStoredInputs(){
        return inputStorage != null && !inputStorage.isEmpty();
    }

    /**
     * Will attempt to initialize data via it's constructor.
     */
    public void initData(){}

    public T getData() {
        return data;
    }

}
