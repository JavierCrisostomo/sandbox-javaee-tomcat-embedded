/**
@file
    SystemRepository.java
@author
    William Chang
@version
    0.1
@date
    - Created: 2017-02-05
    - Modified: 2017-02-23
    .
@note
    References:
    - General:
        - Nothing.
        .
    .
*/

package data.mssql.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import data.entities.SystemSetting;
import data.interfaces.ISystemRepository;

/**
 * System repository.
 */
public class SystemRepository extends BaseRepository implements ISystemRepository {
    protected String _sqlConnectionString;

    /**
     * Default constructor.
     */
    protected SystemRepository() {}

    /**
     * Argument constructor.
     */
    public SystemRepository(String sqlConnectionString) {
        this._sqlConnectionString = sqlConnectionString;
    }

    public SystemSetting createSetting(SystemSetting s) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean deleteSetting(String name) {
        // TODO Auto-generated method stub
        return false;

    }

    public boolean deleteSetting(UUID id) {
        // TODO Auto-generated method stub
        return false;
    }

    public SystemSetting getSetting(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    public SystemSetting getSetting(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Get system settings.
     */
    public Collection<SystemSetting> getSettings() {
        Connection sqlConnection = null;
        Statement sqlStatement = null;
        ResultSet sqlResultSet = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            sqlConnection = DriverManager.getConnection(this._sqlConnectionString);
            sqlStatement = sqlConnection.createStatement();
            sqlResultSet = sqlStatement.executeQuery("SELECT * FROM SystemSetting ORDER BY ApplicationName ASC, Name ASC;");
            Collection<SystemSetting> objs1 = new ArrayList<SystemSetting>();
            while(sqlResultSet.next()) {
                SystemSetting obj = new SystemSetting();
                obj.setId(UUID.fromString(sqlResultSet.getString("Id")));
                obj.setName(sqlResultSet.getString("Name"));
                obj.setValue(sqlResultSet.getString("Value"));
                objs1.add(obj);
            }
            return objs1;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if(sqlResultSet != null) try {sqlResultSet.close();} catch(Exception ex) {}
            if(sqlStatement != null) try {sqlStatement.close();} catch(Exception ex) {}
            if(sqlStatement != null) try {sqlStatement.close();} catch(Exception ex) {}
        }
        return new ArrayList<SystemSetting>();
    }

    public SystemSetting SetSetting(String name, String value) {
        // TODO Auto-generated method stub
        return null;
    }

    public SystemSetting SetSetting(SystemSetting s) {
        // TODO Auto-generated method stub
        return null;
    }
}
