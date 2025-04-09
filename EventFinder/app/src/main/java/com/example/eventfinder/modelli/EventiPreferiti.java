package com.example.eventfinder.modelli;

public class EventiPreferiti {
    private int utente_id;
    private int evento_id;

    public EventiPreferiti(int utente_id, int evento_id) {
        this.utente_id = utente_id;
        this.evento_id = evento_id;
    }

    public int getUtente_id() {
        return utente_id;
    }

    public void setUtente_id(int utente_id) {
        this.utente_id = utente_id;
    }

    public int getEvento_id() {
        return evento_id;
    }

    public void setEvento_id(int evento_id) {
        this.evento_id = evento_id;
    }
}
