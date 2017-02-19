/**
@file
    SystemRepository.java
@author
    William Chang
@version
    0.1
@date
    - Created: 2017-02-05
    - Modified: 2017-02-11
    .
@note
    References:
    - General:
        - Nothing.
        .
    .
*/

package data.sqlite.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        Connection sqlConnection = null;
        PreparedStatement sqlStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            sqlConnection = DriverManager.getConnection(this._sqlConnectionString);
            sqlStatement = sqlConnection.prepareStatement("INSERT INTO SystemSetting (Id, ApplicationName, Name, Value, DateModified) VALUES (?, ?, ?, ?, ?);");
            sqlStatement.setString(1, s.getId().toString());
            sqlStatement.setString(2, s.getApplicationName());
            sqlStatement.setString(3, s.getName());
            sqlStatement.setString(4, s.getValue());
            sqlStatement.setDate(5, (java.sql.Date)s.getDateModified());
            int numRowsAffected = sqlStatement.executeUpdate();
            if(numRowsAffected <= 0) {
                return null;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlConnection != null) try {sqlConnection.close();} catch(Exception e) {}
            if(sqlStatement != null) try {sqlStatement.close();} catch(Exception e) {}
        }
        return s;
    }

    public boolean deleteSetting(String name) {
        Connection sqlConnection = null;
        PreparedStatement sqlStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            sqlConnection = DriverManager.getConnection(this._sqlConnectionString);
            sqlStatement = sqlConnection.prepareStatement("DELETE FROM SystemSetting WHERE Name = ?;");
            sqlStatement.setString(1, name);
            int numRowsAffected = sqlStatement.executeUpdate();
            if(numRowsAffected <= 0) {
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlConnection != null) try {sqlConnection.close();} catch(Exception e) {}
            if(sqlStatement != null) try {sqlStatement.close();} catch(Exception e) {}
        }
        return true;
    }

    public boolean deleteSetting(UUID id) {
        Connection sqlConnection = null;
        PreparedStatement sqlStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            sqlConnection = DriverManager.getConnection(this._sqlConnectionString);
            sqlStatement = sqlConnection.prepareStatement("DELETE FROM SystemSetting WHERE Id = ?;");
            sqlStatement.setString(1, id.toString());
            int numRowsAffected = sqlStatement.executeUpdate();
            if(numRowsAffected <= 0) {
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlConnection != null) try {sqlConnection.close();} catch(Exception e) {}
            if(sqlStatement != null) try {sqlStatement.close();} catch(Exception e) {}
        }
        return true;
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
            Class.forName("org.sqlite.JDBC");
            sqlConnection = DriverManager.getConnection(this._sqlConnectionString);
            sqlStatement = sqlConnection.createStatement();
            sqlResultSet = sqlStatement.executeQuery("SELECT * FROM SystemSetting ORDER BY ApplicationName ASC, Name ASC;");
            Collection<SystemSetting> objs1 = new ArrayList<SystemSetting>();
            while(sqlResultSet.next()) {
                SystemSetting obj = new SystemSetting();
                obj.setName(sqlResultSet.getString("Name"));
                obj.setValue(sqlResultSet.getString("Value"));
                objs1.add(obj);
            }
            return objs1;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlConnection != null) try {sqlConnection.close();} catch(Exception e) {}
            if(sqlStatement != null) try {sqlStatement.close();} catch(Exception e) {}
            if(sqlResultSet != null) try {sqlResultSet.close();} catch(Exception e) {}
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
