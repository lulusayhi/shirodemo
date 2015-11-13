package com.peter.demo.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SimpleDemo {
	/* the default framework is embedded */
	private String framework = "embedded";
	private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private String protocol = "jdbc:derby:";

	public static void main(String[] args) {
		new SimpleDemo().go(args);
		System.out.println("SimpleDemo finished");
	}

	void go(String[] args) {

		System.out.println("SimpleDemo starting in " + framework + " mode");

		/* load the desired JDBC driver */
		loadDriver();

		/*
		 * We will be using Statement and PreparedStatement objects for
		 * executing SQL. These objects, as well as Connections and ResultSets,
		 * are resources that should be released explicitly after use, hence the
		 * try-catch-finally pattern used below. We are storing the Statement
		 * and Prepared statement object references in an array list for
		 * convenience.
		 */
		Connection conn = null;
		/*
		 * This ArrayList usage may cause a warning when compiling this class
		 * with a compiler for J2SE 5.0 or newer. We are not using generics
		 * because we want the source to support J2SE 1.4.2 environments.
		 */
		Statement s = null;
		ResultSet rs = null;
		try {
			Properties props = new Properties(); // connection properties
			// providing a user name and password is optional in the embedded
			// and derbyclient frameworks
			props.put("user", "root");
			props.put("password", "123456");

			String dbName = "derbyDB"; // the name of the database

			conn = DriverManager.getConnection(protocol + dbName, props);

			System.out.println("Connected to database " + dbName);

			// We want to control transactions manually. Autocommit is on by
			// default in JDBC.
			conn.setAutoCommit(false);

			/*
			 * Creating a statement object that we can use for running various
			 * SQL statements commands against the database.
			 */
			s = conn.createStatement();

			/*
			 * We select the rows and verify the results.
			 */
			rs = s.executeQuery("SELECT id, name,passwd FROM users ORDER BY id");

			/*
			 * we expect the first returned column to be an integer (num), and
			 * second to be a String (addr). Rows are sorted by street number
			 * (num).
			 * 
			 * Normally, it is best to use a pattern of while(rs.next()) { // do
			 * something with the result set } to process all returned rows, but
			 * we are only expecting two rows this time, and want the
			 * verification code to be easy to comprehend, so we use a different
			 * pattern.
			 */

			int id; // street number retrieved from the database
			String name;
			String passwd;

			while (rs != null && rs.next()) {
				id = rs.getInt(1);
				name = rs.getString(2);
				passwd = rs.getString(3);
				System.out.println(id + ";" + name + ";" + passwd);
			}

			/*
			 * We commit the transaction. Any changes will be persisted to the
			 * database now.
			 */
			conn.commit();
			System.out.println("Committed the transaction");

			/*
			 * In embedded mode, an application should shut down the database.
			 * If the application fails to shut down the database, Derby will
			 * not perform a checkpoint when the JVM shuts down. This means that
			 * it will take longer to boot (connect to) the database the next
			 * time, because Derby needs to perform a recovery operation.
			 * 
			 * It is also possible to shut down the Derby system/engine, which
			 * automatically shuts down all booted databases.
			 * 
			 * Explicitly shutting down the database or the Derby engine with
			 * the connection URL is preferred. This style of shutdown will
			 * always throw an SQLException.
			 * 
			 * Not shutting down when in a client environment, see method
			 * Javadoc.
			 */

			if (framework.equals("embedded")) {
				try {
					// the shutdown=true attribute shuts down Derby
					DriverManager.getConnection("jdbc:derby:;shutdown=true");

					// To shut down a specific database only, but keep the
					// engine running (for example for connecting to other
					// databases), specify a database in the connection URL:
					// DriverManager.getConnection("jdbc:derby:" + dbName +
					// ";shutdown=true");
				} catch (SQLException se) {
					if (((se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState())))) {
						// we got the expected exception
						System.out.println("Derby shut down normally");
						// Note that for single database shutdown, the expected
						// SQL state is "08006", and the error code is 45000.
					} else {
						// if the error code or SQLState is different, we have
						// an unexpected exception (shutdown failed)
						System.err.println("Derby did not shut down normally");
						printSQLException(se);
					}
				}
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		} finally {
			// release all open resources to avoid unnecessary memory usage

			// ResultSet
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}

			// Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
	}

	private void loadDriver() {

		try {
			Class.forName(driver).newInstance();
			System.out.println("Loaded the appropriate driver");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("\nUnable to load the JDBC driver " + driver);
			System.err.println("Please check your CLASSPATH.");
			cnfe.printStackTrace(System.err);
		} catch (InstantiationException ie) {
			System.err.println("\nUnable to instantiate the JDBC driver " + driver);
			ie.printStackTrace(System.err);
		} catch (IllegalAccessException iae) {
			System.err.println("\nNot allowed to access the JDBC driver " + driver);
			iae.printStackTrace(System.err);
		}
	}

	public static void printSQLException(SQLException e) {
		// Unwraps the entire exception chain to unveil the real cause of the
		// Exception.
		while (e != null) {
			System.err.println("\n----- SQLException -----");
			System.err.println("  SQL State:  " + e.getSQLState());
			System.err.println("  Error Code: " + e.getErrorCode());
			System.err.println("  Message:    " + e.getMessage());
			// for stack traces, refer to derby.log or uncomment this:
			// e.printStackTrace(System.err);
			e = e.getNextException();
		}
	}
}
