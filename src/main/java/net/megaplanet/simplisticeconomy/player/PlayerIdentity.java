package net.megaplanet.simplisticeconomy.player;

import java.util.UUID;

public class PlayerIdentity {

    private final String playerName;
    private final UUID playerUUID;

    public PlayerIdentity(String playerName, UUID playerUUID) {
        this.playerName = playerName;
        this.playerUUID = playerUUID;
    }

    /**
     * Gets the player's name
     *
     * @return the player's name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the player's UUID
     *
     * @return the player's UUID
     */
    public UUID getPlayerUUID() {
        return playerUUID;
    }
}
