package com.ats.qosmpp.domain;

public enum State {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted"),
    LOCKED("Locked");

    private String state;

    private State(final String state){
        this.state = state;
    }

    //Getter and Setter
}