/**
@file
    AdminServlet.java
@author
    William Chang
@version
    0.1
@date
    - Created: 2017-02-11
    - Modified: 2017-02-11
    .
@note
    References:
    - General:
        - Nothing.
        .
    .
*/

package web.servlets;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import data.entities.*;
import data.interfaces.*;
import data.sqlite.repositories.*;

/**
 * Servlet implementation class Admin.
 */
@SuppressWarnings("serial")
@WebServlet("/api/v1/admin")
public class AdminServlet extends HttpServlet {
    protected static final String mssqlConnectionString = "jdbc:sqlserver://127.0.0.1;instance=SQLEXPRESS;databaseName=Sandbox;user=sa;password=sa";

    /**
     * Default constructor.
     */
    public AdminServlet() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        StringBuilder sb1 = new StringBuilder();

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if(action == null) {
            res.getWriter().write("Missing action querystring (eg web.com/api/v1/servlet?action=yourname).");
            return;
        }

        if(action.equals("ViewSystemSetting")) {
            sb1.append(actionViewSystemSetting(req, res));
        } else if(action.equals("CreateSystemSetting")) {
            sb1.append(actionCreateSystemSetting(req, res));
        } else {
            sb1.append("Missing action name.");
        }

        /*
        if(sqliteConnectionString != null) {
            ISystemRepository repoSystem = new SystemRepository(sqliteConnectionString);

            sb1.append(System.getProperty("line.separator"));
            sb1.append("Database Table : SystemSetting").append(System.getProperty("line.separator"));
            sb1.append(System.getProperty("line.separator"));
            for(SystemSetting item: repoSystem.getSettings()) {
                sb1.append("Name : ").append(item.getName()).append(System.getProperty("line.separator"));
                sb1.append("Value : ").append(item.getValue()).append(System.getProperty("line.separator"));
                sb1.append(System.getProperty("line.separator"));
            }
        } else {
            sb1.append(System.getProperty("line.separator"));
            sb1.append("The database file is missing or cannot be found.").append(System.getProperty("line.separator"));
            sb1.append(System.getProperty("line.separator"));
        }
        */

        res.getWriter().write(sb1.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * CreateSystemSetting.
     */
    protected String actionCreateSystemSetting(HttpServletRequest req, HttpServletResponse res) {
        Gson json = new Gson();
        String sqliteConnectionString = BaseRepository.getDefaultConnectionString();

        if(sqliteConnectionString != null) {
            ISystemRepository repoSystem = new SystemRepository(sqliteConnectionString);
            SystemSetting obj1 = new SystemSetting();
            obj1.setId(UUID.fromString(req.getParameter("Id")));
            obj1.setApplicationName(req.getParameter("ApplicationName"));
            obj1.setName(req.getParameter("Name"));
            obj1.setValue(req.getParameter("Value"));
            obj1.setDateModified(new java.util.Date());
            return json.toJson(repoSystem.createSetting(obj1));
        } else {
            return "";
        }
    }

    /**
     * ViewSystemSetting.
     */
    protected String actionViewSystemSetting(HttpServletRequest req, HttpServletResponse res) {
        Gson json = new Gson();
        String sqliteConnectionString = BaseRepository.getDefaultConnectionString();

        if(sqliteConnectionString != null) {
            ISystemRepository repoSystem = new SystemRepository(sqliteConnectionString);
            return json.toJson(repoSystem.getSettings());
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "{\"Error\":\"The database file is missing or cannot be found.\"}";
        }
    }
}
