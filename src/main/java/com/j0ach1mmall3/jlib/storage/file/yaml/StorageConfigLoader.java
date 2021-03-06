package com.j0ach1mmall3.jlib.storage.file.yaml;

import com.j0ach1mmall3.jlib.storage.DataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/11/15
 */
public final class StorageConfigLoader<P extends JavaPlugin> extends ConfigLoader<P> {
    private DataType type;
    private final String database_Host;
    private final int database_Port;
    private final String database_Database;
    private final String database_User;
    private final String database_Password;
    private final String database_Prefix;

    /**
     * Constructs a new Storage ConfigLoader
     * @param plugin The JavaPlugin associated with this Storage Config
     */
    public StorageConfigLoader(P plugin) {
        super("storage.yml", plugin);
        try {
            this.type = DataType.valueOf(this.config.getString("Type"));
        } catch(IllegalArgumentException e) {
            this.type = DataType.FILE;
        }
        this.database_Host = this.config.getString("Database.Host");
        this.database_Port = this.config.getInt("Database.Port");
        this.database_Database = this.config.getString("Database.Database");
        this.database_User = this.config.getString("Database.User");
        this.database_Password = this.config.getString("Database.Password");
        this.database_Prefix = this.config.getString("Database.Prefix");
    }

    /**
     * Returns the selected DataType
     * @return The DataType
     */
    public DataType getType() {
        return this.type;
    }

    /**
     * Returns the Database Host Address
     * @return The Database Host Address
     */
    public String getDatabaseHost() {
        return this.database_Host;
    }

    /**
     * Returns the Database Port
     * @return The Database Port
     */
    public int getDatabasePort() {
        return this.database_Port;
    }

    /**
     * Returns the Database Database Name
     * @return The Database Database Name
     */
    public String getDatabaseDatabase() {
        return this.database_Database;
    }

    /**
     * Returns the Database User
     * @return The Database User
     */
    public String getDatabaseUser() {
        return this.database_User;
    }

    /**
     * Returns the Database Password
     * @return The Database Password
     */
    public String getDatabasePassword() {
        return this.database_Password;
    }

    /**
     * Returns the Database Table prefix
     * @return The Database Table prefix
     */
    public String getDatabasePrefix() {
        return this.database_Prefix;
    }
}
