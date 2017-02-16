/**
@file
    ISystemRepository.java
@author
    William Chang
@version
    0.1
@date
    - Created: 2017-02-05
    - Modified: 2017-02-07
    .
@note
    References:
    - General:
        - Nothing.
        .
    .
*/

package data.interfaces;

import java.util.*;
import data.entities.*;

/**
 * System repository interface.
 * */
public interface ISystemRepository {
    /**
     * Create (INSERT) system setting.
     */
    SystemSetting createSetting(SystemSetting s);

    /**
     * Delete system setting permanently.
     */
    boolean deleteSetting(UUID id);

    /**
     * Delete system setting permanently (duplicates are removed).
     */
    boolean deleteSetting(String name);

    /**
     * Get system setting.
     */
    SystemSetting getSetting(UUID id);

    /**
     * Get system setting.
     */
    SystemSetting getSetting(String name);

    /**
     * Get system settings.
     */
    Collection<SystemSetting> getSettings();

    /**
     * Set (UPDATE) system setting.
     */
    SystemSetting SetSetting(SystemSetting s);

    /**
     * Set (UPDATE) or create (INSERT) system setting.
     */
    SystemSetting SetSetting(String name, String value);
}
