/**
@file
    HomeServlet.java
@author
    William Chang
@version
    0.1
@date
    - Created: 2017-02-05
    - Modified: 2017-02-12
    .
@note
    References:
    - General:
        - http://users.polytech.unice.fr/~buffa/cours/internet/POLYS/servlets/Servlet-Tutorial-CGI-Variables.html
        .
    .
*/

package web.servlets;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.sqlite.repositories.*;

/**
 * Servlet implementation class Home.
 */
@WebServlet({"/api/v1" , "/api/v1/home/*"})
public class HomeServlet extends HttpServlet {
    protected static final long serialVersionUID = 1L;
    protected static final String mssqlConnectionString = "jdbc:sqlserver://127.0.0.1;instance=SQLEXPRESS;databaseName=Sandbox;user=sa;password=sa";

    /**
     * Default constructor.
     */
    public HomeServlet() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        StringBuilder sb1 = new StringBuilder();

        res.setContentType("text/plain");
        res.setCharacterEncoding("UTF-8");

        res.getWriter().println();
        res.getWriter().println("Hello World");
        res.getWriter().println();

        sb1.append(debugInformation(req, res));

        res.getWriter().write(sb1.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.doPost(req, resp);
    }

    /**
     * Debug Information.
     */
    protected String debugInformation(HttpServletRequest req, HttpServletResponse res) {
        StringBuilder sb1 = new StringBuilder();

        sb1.append(System.getProperty("line.separator"));
        sb1.append("Server Variable SCRIPT_NAME : ").append(req.getServletPath()).append(System.getProperty("line.separator"));
        sb1.append("Server Variable DOCUMENT_ROOT : ").append(getServletContext().getRealPath("/")).append(System.getProperty("line.separator"));
        sb1.append("Server Variable REQUEST_METHOD : ").append(req.getMethod()).append(System.getProperty("line.separator"));
        sb1.append(System.getProperty("line.separator"));

        // Get request headers.
        Enumeration<String> reqHeaderNames = req.getHeaderNames();
        sb1.append(System.getProperty("line.separator"));
        while(reqHeaderNames.hasMoreElements()) {
            String name = reqHeaderNames.nextElement();
            String value = req.getHeader(name);

            sb1.append("Request Header Name : ").append(name).append(System.getProperty("line.separator"));
            sb1.append("Request Header Value : ").append(value).append(System.getProperty("line.separator"));
            sb1.append(System.getProperty("line.separator"));
        }

        // Get request path.
        String pathInfo = req.getPathInfo();
        sb1.append(System.getProperty("line.separator"));
        sb1.append("Server Variable PATH_INFO : ").append(pathInfo).append(System.getProperty("line.separator"));
        if(pathInfo != null) {
            String[] pathSegments = pathInfo.split("/");
            for(int index = 0;index < pathSegments.length; index += 1) {
                sb1.append("Server Variable PATH_INFO Segment Index : ").append(index).append(", Value : ").append(pathSegments[index]).append(System.getProperty("line.separator"));
            }
        }
        sb1.append(System.getProperty("line.separator"));

        // Get request querystring.
        sb1.append(System.getProperty("line.separator"));
        sb1.append("Server Variable QUERY_STRING : ").append(req.getQueryString()).append(System.getProperty("line.separator"));
        sb1.append(System.getProperty("line.separator"));

        // Get request parameters.
        Enumeration<String> reqParameterNames = req.getParameterNames();
        sb1.append(System.getProperty("line.separator"));
        while(reqParameterNames.hasMoreElements()) {
            String name = reqParameterNames.nextElement();
            String[] values = req.getParameterValues(name);

            sb1.append("Request Parameter Name : ").append(name).append(System.getProperty("line.separator"));
            for(int index = 0;index < values.length; index += 1) {
                sb1.append("Request Parameter Value Index : ").append(index).append(", Value : ").append(values[index]).append(System.getProperty("line.separator"));
            }
            sb1.append(System.getProperty("line.separator"));
        }

        // Get debug information.
        sb1.append(System.getProperty("line.separator"));
        sb1.append("Request Context Path : ").append(req.getContextPath()).append(System.getProperty("line.separator"));
        sb1.append("Classes Folder Path : ").append(BaseRepository.getClassesFolderPath()).append(System.getProperty("line.separator"));

        return sb1.toString();
    }
}
