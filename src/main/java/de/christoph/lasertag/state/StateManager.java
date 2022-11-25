package de.christoph.lasertag.state;

import de.christoph.lasertag.state.game.GameState;
import de.christoph.lasertag.state.lobby.LobbyState;

public class StateManager {

    private State currentState;
    private LobbyState lobbyState;
    private GameState gameState;

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

    public void setGameState(GameState gameState) {
        currentState = State.GAME;
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
