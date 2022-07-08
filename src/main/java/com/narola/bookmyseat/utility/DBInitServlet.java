package com.narola.bookmyseat.utility;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DBInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			String strdbType = getServletContext().getInitParameter("DB-IN-USE");
			DatabaseType dbtype;

			if (strdbType.equals(DatabaseType.MYSQL.name())) {
				dbtype = DatabaseType.MYSQL;
			} else if (strdbType.equals(DatabaseType.POSTGRES.name())) {
				dbtype = DatabaseType.POSTGRES;
			} else {
				dbtype = null;
			}			DAOFactory.getInstance().init(dbtype);

			String dbURL = getServletContext().getInitParameter(strdbType + "_DBURL");
			String dbUser = getServletContext().getInitParameter(strdbType + "_DBUSER");
			String dbPassword = getServletContext().getInitParameter(strdbType + "_DBPASSWORD");
			DBConnector.getInstance(dbURL, dbUser, dbPassword);
			ServiceFactory.getInstance().init();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
