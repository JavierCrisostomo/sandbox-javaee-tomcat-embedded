/**
@file
    AdminServlet.java
@author
    William Chang
@version
    0.1
@date
    - Created: 2017-02-11
    - Modified: 2017-02-15
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
@WebServlet("/api/v1/admin/*")
public class AdminServlet extends BaseServlet {
    /**
     * Default constructor.
     */
    public AdminServlet() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        StringBuilder sb1 = new StringBuilder();

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        String action = getActionName(req);

        if(isActionEquals(action, "ViewSystemSetting")) {
            sb1.append(actionViewSystemSetting(req, res));
        } else if(isActionEquals(action, "CreateSystemSetting")) {
            sb1.append(actionCreateSystemSetting(req, res));
        } else {
            sb1.append("Missing action name.");
        }

        res.getWriter().write(sb1.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * Action CreateSystemSetting.
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
