package net.megaplanet.simplisticeconomy.storage.types.mysql;

class Queries {

    final static String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `economy` (\n" +
            "  `player_name` varchar(16) NOT NULL,\n" +
            "  `balance` double NOT NULL,\n" +
            "  PRIMARY KEY (`player_name`),\n" +
            "  UNIQUE KEY `player_name` (`player_name`)\n" +
            ") ENGINE=MyISAM DEFAULT CHARSET=latin1;\n";

    final static String INSERT = "INSERT INTO economy VALUES (?, ?)";
    final static String UPDATE_SET = "UPDATE economy SET balance=? WHERE player_name=?";
    final static String UPDATE_ADD = "UPDATE economy SET balance=balance+? WHERE player_name=?";
    final static String UPDATE_REMOVE = "UPDATE economy SET balance=balance-? WHERE player_name=?";
    final static String SELECT = "SELECT balance FROM economy WHERE player_name=?";

}
