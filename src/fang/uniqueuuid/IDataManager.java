package fang.uniqueuuid;

import javax.annotation.Nonnull;

public interface IDataManager {
    void saveAll();

    void loadAll();

    @Nonnull
    String getPlayerUUID(String id);

    void addPlayerUUID(String id, String playerUUID);
}