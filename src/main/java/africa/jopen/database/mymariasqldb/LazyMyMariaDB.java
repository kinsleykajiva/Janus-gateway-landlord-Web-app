package africa.jopen.database.mymariasqldb;

import org.json.JSONObject;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BooleanSupplier;
import java.util.logging.Logger;

public class LazyMyMariaDB {
	
	public static String DB_NAME = "";
	public static String DB_HOST = "";
	public static String DB_USERNAME = "";
	public static String DB_PASSWORD = "";
	public static int DB_PORT = 3306;
	static Logger logger = Logger.getLogger(LazyMyMariaDB.class.getSimpleName());
	private ExecutorService executorService = Executors.newSingleThreadExecutor();
	private static LazyMyMariaDB instance;
	
	private Connection connect = null;
	private Statement statement = null;
	
	private static BooleanSupplier isConnected = () -> DB_HOST == null || DB_HOST.isEmpty() ||DB_PASSWORD == null || DB_PASSWORD.isEmpty() ||DB_USERNAME == null || DB_USERNAME.isEmpty();
	private LazyMyMariaDB() {
		if (isConnected.getAsBoolean()) {
			return;
		}
		logger.info("Initializing LazyMyMariaDB instance");
		
		connection();
	}
	
	void createTables() {
		if (connect == null) {
			connection();
		}
		SQLBatchExec(createJanusTables());
		
	}
	
	void connection() {
		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mariadb://" + DB_HOST + "/" + DB_NAME, DB_USERNAME, DB_PASSWORD);
			createTables();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static String createJanusTables() {
		logger.info("Loading Tables at run time ");
		var sqlCreateTables = """
				CREATE TABLE IF NOT EXISTS sessions (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30) NOT NULL, event VARCHAR(30) NOT NULL, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS handles (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30) NOT NULL, handle BIGINT(30) NOT NULL, event VARCHAR(30) NOT NULL, plugin VARCHAR(100) NOT NULL, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS core (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(30) NOT NULL, value VARCHAR(30) NOT NULL, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS sdps (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30) NOT NULL, handle BIGINT(30) NOT NULL, remote BOOLEAN NOT NULL, offer BOOLEAN NOT NULL, sdp VARCHAR(3000) NOT NULL, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS ice (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30) NOT NULL, handle BIGINT(30) NOT NULL, stream INT NOT NULL, component INT NOT NULL, state VARCHAR(30) NOT NULL, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS selectedpairs (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30) NOT NULL, handle BIGINT(30) NOT NULL, stream INT NOT NULL, component INT NOT NULL, selected VARCHAR(200) NOT NULL, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS dtls (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30) NOT NULL, handle BIGINT(30) NOT NULL, stream INT NOT NULL, component INT NOT NULL, state VARCHAR(30) NOT NULL, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS connections (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30) NOT NULL, handle BIGINT(30) NOT NULL, state VARCHAR(30) NOT NULL, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS media (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30) NOT NULL, handle BIGINT(30) NOT NULL, medium VARCHAR(30) NOT NULL, receiving BOOLEAN NOT NULL, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS stats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30) NOT NULL, handle BIGINT(30) NOT NULL, medium VARCHAR(30) NOT NULL, base INT, lsr INT, lostlocal INT, lostremote INT, jitterlocal INT, jitterremote INT, packetssent INT, packetsrecv INT, bytessent BIGINT, bytesrecv BIGINT, nackssent INT, nacksrecv INT, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS plugins (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30), handle BIGINT(30), plugin VARCHAR(100) NOT NULL, event VARCHAR(3000) NOT NULL, timestamp datetime NOT NULL);
				CREATE TABLE IF NOT EXISTS transports (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, session BIGINT(30), handle BIGINT(30), plugin VARCHAR(100) NOT NULL, event VARCHAR(300) NOT NULL, timestamp datetime NOT NULL);
				""";
		
		return sqlCreateTables;
	}
	
	public static LazyMyMariaDB getInstance() {
		if (isConnected.getAsBoolean()) {
			logger.info("No Access to LazyMyMariaDB Database!");
			return null;
		}
		if (instance == null) {
			instance = new LazyMyMariaDB();
			
		}
		return instance;
	}
	
	/*type - 1*/
	record SessionEvents(BigInteger sessionId, String event, long timestamp) {
		
		public String insertSessionSQL() {
			
			return "INSERT INTO sessions (session,event,timestamp) VALUES (" + sessionId + " , '" + event + "' , " + timestamp + " );";
			
		}
	}
	
	/*type - 2*/
	record HandleEvents(BigInteger sessionId, BigInteger handleId, String event, String plugin, long timestamp) {
		
		public String insertHandleSQL() {
			return "INSERT INTO handles (session,handle,event,plugin,timestamp) VALUES (" + sessionId + " ," + handleId + " , '" + event + "' ,'" + plugin + "' , " + timestamp + " );";
		}
	}
	
	/*type - 8*/
	record JSEPEvents(BigInteger sessionId, BigInteger handleId, int remote, int offer, String sdp, long timestamp) {
		public String insertJSEPSQL() {
			return "INSERT INTO sdps (session,handle,remote,offer,sdp,timestamp) VALUES (" + sessionId + "," + handleId + ",'" + remote + "','" + offer + "','" + sdp + "'," + timestamp + " );";
		}
	}
	
	/*type - 16*/
	record WebRTCStateEvents(BigInteger sessionId, BigInteger handleId, BigInteger streamId, BigInteger componentId,
	                         long timestamp) {
		public String insertIceWebRTCStateSQL(String ice) {
			return "INSERT INTO ice (session,handle,stream,component,timestamp ,state) VALUES (" + sessionId + "," + handleId + ",'" + streamId + "' , '" + componentId + "','" + timestamp + "' , '" + ice + "');";
		}
		
		public String insertPairWebRTCStateSQL(String pair) {
			return "INSERT INTO selectedpairs (session,handle,stream,component,timestamp ,selected) VALUES (" + sessionId + "," + handleId + "," + streamId + " , '" + componentId + "'," + timestamp + " , '" + pair + "');";
		}
		
		public String insertDtlsWebRTCStateSQL(String dtls) {
			return "INSERT INTO dtls (session,handle,state,timestamp) VALUES (" + sessionId + "," + handleId + ", '" + dtls + "'," + timestamp + " );";
		}
		
		public String insertConnectionWebRTCStateSQL(String connection) {
			return "INSERT INTO ice (session,handle,timestamp ,state) VALUES (" + sessionId + "," + handleId + ", " + timestamp + " , '" + connection + "');";
		}
		
	}
	
	/*type - 32*/
	record MediaEvents(BigInteger sessionId, BigInteger handleId, String medium, long timestamp) {
		public String insertReceivingMediaSQL(String receiving) {
			return "INSERT INTO media (session,handle,medium,timestamp,receiving) VALUES (" + sessionId + "," + handleId + ",'" + medium + "'," + timestamp + ", '" + receiving + "' );";
			
		}
		
		public String insertBaseMediaSQL(String base, String lsr, String lostlocal, String lostremote, String jitterlocal
				, String jitterremote, String packetssent, String packetsrecv, String bytessent, String bytesrecv, String nackssent, String nackrecv) {
			return "INSERT INTO stats (session,handle,medium ,timestamp ,   base, lsr, lostlocal, lostremote ,  jitterlocal, " +
					" jitterremote , packetssent , packetsrecv , bytessent, bytesrecv ,  nackssent , nackrecv)  VALUES (" + sessionId + "," + handleId + ",'" + medium + "'," + timestamp + "," +
					"  '" + base + "' , '" + lsr + "' , '" + lostlocal + "' , '" + lostremote + "' , '" + jitterlocal + "' , '" + jitterremote + "' , '" + packetssent + "' , '" + packetsrecv + "' , '" + bytessent + "' , '" + bytesrecv + "' , '" + nackssent + "' , '" + nackrecv + "'  );";
			
			
		}
		
	}
	
	/*type - 64*/ /*type - 128*/
	record PluginTransportEvents(boolean isTransport, BigInteger sessionId, BigInteger handleId, String plugin,
	                             String event, long timestamp) {
		
		public String insertPluginTransportSQL() {
			var table = isTransport ? "plugins" : " transports ";
			return "INSERT INTO '" + table + "' (session,handle,plugin,event,timestamp) VALUES (" + sessionId + "," + handleId + ",'" + plugin + "' , '" + event + "' , " + timestamp + " )";
		}
		
	}
	
	/*type - 256*/
	record CoreEvents(String name, String event, String signum, long timestamp) {
		public String insertCoreEventSQL() {
			return "INSERT INTO core (name,value,timestamp) VALUES ('" + name + "','" + event + "'," + timestamp + ");";
		}
		
	}
	
	
	public void saveEvent(String json) {
		if (isConnected.getAsBoolean()) {
			return;
		}
		var type = 0;
		var sql = "";
		JSONObject jsonObject = new JSONObject(json);
		
		if (jsonObject.has("type")) {
			type = jsonObject.getInt("type");
			
			switch (type) {
				case 1 -> {
					var sessionEvents = new SessionEvents(jsonObject.getBigInteger("session_id"), jsonObject.getJSONObject("event").getString("name"), jsonObject.getLong("timestamp"));
					sql = sessionEvents.insertSessionSQL();
				}
				case 2 -> {
					var handleEvents = new HandleEvents(jsonObject.getBigInteger("session_id"), jsonObject.getBigInteger("handle_id"),
							jsonObject.getJSONObject("event").getString("name"),
							jsonObject.getJSONObject("event").getString("plugin"),
							jsonObject.getLong("timestamp"));
					sql = handleEvents.insertHandleSQL();
				}
				case 16 -> {
					var webRTCStateEvents = new WebRTCStateEvents(jsonObject.getBigInteger("session_id"), jsonObject.getBigInteger("handle_id"),
							jsonObject.getJSONObject("event").getBigInteger("stream_id"),
							jsonObject.getJSONObject("event").getBigInteger("component_id"),
							jsonObject.getLong("timestamp"));
					if (jsonObject.getJSONObject("event").has("ice")) {
						var ice = jsonObject.getJSONObject("event").getString("ice");
						sql = webRTCStateEvents.insertIceWebRTCStateSQL(ice);
					} else if (jsonObject.getJSONObject("event").has("selected-pair")) {
						var selectedPair = jsonObject.getJSONObject("event").getString("selected-pair");
						sql = webRTCStateEvents.insertPairWebRTCStateSQL(selectedPair);
					} else if (jsonObject.getJSONObject("event").has("dtls")) {
						var dtls = jsonObject.getJSONObject("event").getString("dtls");
						sql = webRTCStateEvents.insertDtlsWebRTCStateSQL(dtls);
					} else if (jsonObject.getJSONObject("event").has("connection")) {
						var connection = jsonObject.getJSONObject("event").getString("connection");
						sql = webRTCStateEvents.insertConnectionWebRTCStateSQL(connection);
					} else {
						logger.severe("Unsupported WebRTC event?");
					}
				}
				case 8 -> {
					var jsepEvents = new JSEPEvents(jsonObject.getBigInteger("session_id"), jsonObject.getBigInteger("handle_id"),
							jsonObject.getJSONObject("event").getString("owner").equals("remote") ? 1 : 0,
							jsonObject.getJSONObject("event").getJSONObject("jsep").getString("type").equals("offer") ? 1 : 0,
							jsonObject.getJSONObject("event").getJSONObject("jsep").getString("sdp"),
							jsonObject.getLong("timestamp"));
					sql = jsepEvents.insertJSEPSQL();
				}
				case 32 -> {
					var mediaEvents = new MediaEvents(jsonObject.getBigInteger("session_id"), jsonObject.getBigInteger("handle_id"),
							jsonObject.getJSONObject("event").getString("media"),
							jsonObject.getLong("timestamp"));
					if (jsonObject.getJSONObject("event").has("receiving") && !jsonObject.getJSONObject("event").isNull("receiving")) {
						sql = mediaEvents.insertReceivingMediaSQL(jsonObject.getJSONObject("event").getString("receiving"));
					} else if (jsonObject.getJSONObject("event").has("base") && !jsonObject.getJSONObject("event").isNull("base")) {
						
						var event = jsonObject.getJSONObject("event");
						sql = mediaEvents.insertBaseMediaSQL(event.getString("base"), event.getString("lsr"), event.getString("lost"),
								event.getString("lost-by-remote"), event.getString("jitter-local"), event.getString("jitter-remote"),
								event.getString("packets-sent"), event.getString("packets-received"), event.getString("bytes-sent"),
								event.getString("bytes-received"), event.getString("nacks-sent"), event.getString("nacks-received"));
					} else {
						logger.severe("Unsupported media event?");
					}
				}
				case 64, 128 -> {
					var pluginTransportEvents = new PluginTransportEvents(type == 64,
							jsonObject.getBigInteger("session_id"), jsonObject.getBigInteger("handle_id"),
							jsonObject.getJSONObject("event").getString("plugin"), jsonObject.getJSONObject("event").getString("data"),
							jsonObject.getLong("timestamp"));
					sql = pluginTransportEvents.insertPluginTransportSQL();
				}
				default -> logger.severe("Unsupported  event?");
			}
			String finalSql = sql;
			executorService.execute(()->SQLExec(finalSql));
			
		}
	}
	
	public void SQLExec(String sql) {
		if (isConnected.getAsBoolean()) {
			return;
		}
		
		
		if (connect == null) {
			connection();
		}
		if (connect == null) {
			logger.severe("Could not connect to database");
			return;
		}
		try {
			logger.info("SQL-" + sql);
			statement = connect.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
		}
	}
	
	public void SQLBatchExec(String sql) {
		if (isConnected.getAsBoolean()) {
			return;
		}
		
		if (connect == null) {
			connection();
		}
		if (connect == null) {
			logger.severe("Could not connect to database");
			return;
		}
		try {
			logger.info("SQL-" + sql);
			statement = connect.createStatement();
			statement.addBatch(sql);
			statement.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.severe(e.getMessage());
		}
	}
	
	
}
