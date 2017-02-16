/**
@file
    BaseRepository.java
@author
    William Chang
@version
    0.1
@date
    - Created: 2017-02-05
    - Modified: 2017-02-08
    .
@note
    References:
    - General:
        - Nothing.
        .
    .
*/

package data.sqlite.repositories;

import data.interfaces.*;

/**
 * Base Repository.
 */
public class BaseRepository implements IBaseRepository {
    /**
     * Default constructor.
     */
    public BaseRepository() {
        // TODO Auto-generated constructor stub
    }

    public static String getClassesFolderPath() {
        return new Object() {}.getClass().getResource("/").getPath().substring(1);
    }

    public static String getDefaultConnectionString() {
        String filePath = getClassesFolderPath() + "Sandbox.sqlite3";

        System.out.println();
        System.out.println("data.sqlite.repositories.BaseRepository.GetDefaultConnectionString, Classes Folder Path : " + getClassesFolderPath());
        System.out.println();

        if(isFileExist(filePath)) {
            return "jdbc:sqlite:" + filePath;
        } else {
            return null;
        }
    }

    public static boolean isFileExist(String filePath) {
        return new java.io.File(filePath).isFile();
    }
}
