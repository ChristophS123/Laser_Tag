package de.christoph.lasertag.state;

import de.christoph.lasertag.state.lobby.LobbyState;

public class StateManager {

    private State currentState;
    private LobbyState lobbyState;

    public void setLobbyState() {
        lobbyState = new LobbyState();
        currentState = State.LOBBY;
    }

    public State getCurrentState() {
        return currentState;
    }

    public LobbyState getLobbyState() {
        return lobbyState;
    }
}
