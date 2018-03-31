package net.megaplanet.simplisticeconomy.storage.types.mysql;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLCallback {

    void call(Connection t) throws SQLException;

}