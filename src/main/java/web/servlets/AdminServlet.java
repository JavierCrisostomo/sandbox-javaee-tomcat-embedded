/**
@file
    AdminServlet.java
@author
    William Chang
@version
    0.1
@date
    - Created: 2017-02-11
    - Modified: 2017-02-23
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
    protected ISystemRepository repoSystem;

    /**
     * Default constructor.
     */
    public AdminServlet() {
        String sqliteConnectionString = BaseRepository.getDefaultConnectionString();
        if(sqliteConnectionString != null) {
            repoSystem = new SystemRepository(sqliteConnectionString);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        StringBuilder sb1 = new StringBuilder();
        String action = getActionName(req);

        // Set view.
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        // Validate repositories.
        if(repoSystem == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res.getWriter().write("{\"Error\":\"The database file is missing or cannot be found.\"}");
            return;
        }

        // Validate action from URL.
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

        SystemSetting obj1 = new SystemSetting();
        obj1.setId(UUID.fromString(req.getParameter("Id")));
        obj1.setApplicationName(req.getParameter("ApplicationName"));
        obj1.setName(req.getParameter("Name"));
        obj1.setValue(req.getParameter("Value"));
        obj1.setDateModified(new java.util.Date());
        return json.toJson(repoSystem.createSetting(obj1));
    }

    /**
     * ViewSystemSetting.
     */
    protected String actionViewSystemSetting(HttpServletRequest req, HttpServletResponse res) {
        Gson json = new Gson();

        return json.toJson(repoSystem.getSettings());
    }
}
