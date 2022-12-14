package africa.jopen.database.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.function.UnaryOperator;
import java.util.logging.Logger;

import static africa.jopen.utils.XUtils.renameObjectKeyName;
import static com.mongodb.client.model.Filters.eq;

public class LazyMongoDB {

	public static  String        DB_NAME     = "";
	public static  String        DB_HOST     = "";
	public static  String        DB_USERNAME = "";
	public static  String        DB_PASSWORD = "";
	public static  int           DB_PORT     = 27017;
	static         Logger        logger      = Logger.getLogger(LazyMongoDB.class.getSimpleName());
	private static LazyMongoDB   instance;
	private        MongoDatabase database;
	private MongoClient mongoClient1;


	private LazyMongoDB () {
		if (
				DB_HOST == null || DB_HOST.isEmpty() ||
				DB_PASSWORD == null || DB_PASSWORD.isEmpty() ||
				DB_USERNAME == null || DB_USERNAME.isEmpty()
		) {
			return;
		}
		logger.info("Initializing LazyMongoDB instance");
		final String uri = "mongodb://" + DB_USERNAME + ":" + DB_PASSWORD + "@" + DB_HOST + ":" + DB_PORT;
		mongoClient1 = MongoClients.create(uri);
		database = mongoClient1.getDatabase(DB_NAME);
	}

	public static LazyMongoDB getInstance () {
		if (
				DB_HOST == null || DB_HOST.isEmpty() ||
				DB_PASSWORD == null || DB_PASSWORD.isEmpty() ||
				DB_USERNAME == null || DB_USERNAME.isEmpty()
		) {
			logger.info("No Access to Mongo Database!");
			return null;
		}
		if (instance == null) {
			instance = new LazyMongoDB();
		}
		return instance;
	}


	private Document processJ (Document document, JSONObject jsobject) {
		var timeStamp = new Timestamp(System.currentTimeMillis());
		for (String key : jsobject.keySet()) {
			var value = jsobject.get(key);
			if (value instanceof String) {
				document.put(renameObjectKeyName.apply(key), jsobject.getString(key));
			} else if (value instanceof BigInteger) {
				document.put(renameObjectKeyName.apply(key), jsobject.getBigInteger(key));
			} else if (value instanceof Long) {
				document.put(renameObjectKeyName.apply(key), jsobject.getLong(key));
			} else if (value instanceof Integer) {
				document.put(renameObjectKeyName.apply(key), jsobject.getInt(key));
			} else if (value instanceof Boolean) {
				document.put(renameObjectKeyName.apply(key), jsobject.getBoolean(key));
			}
		}
		document.append("_id", new ObjectId());
		document.append("ts", timeStamp);
		document.append("ts_", timeStamp.toString());
		return document;
	}




	public JSONArray getEvents (String tableEvent) {
		if (
				DB_HOST == null || DB_HOST.isEmpty() ||
				DB_PASSWORD == null || DB_PASSWORD.isEmpty() ||
				DB_USERNAME == null || DB_USERNAME.isEmpty()
		) {
			return null;
		}
		JSONArray                 jsonArray           = new JSONArray();
		MongoCollection<Document> sipEventsCollection = database.getCollection(tableEvent);
		for (Document document : sipEventsCollection.find()) {
			JSONObject jsonObject = new JSONObject();
			for (String key : document.keySet()) {
				jsonObject.put(key, document.get(key));
			}
			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}


	public JSONArray getEventsFilter (String tableEvent, String filterColum, String value) {
		if (
				DB_HOST == null || DB_HOST.isEmpty() ||
				DB_PASSWORD == null || DB_PASSWORD.isEmpty() ||
				DB_USERNAME == null || DB_USERNAME.isEmpty()
		) {
			return null;
		}
		boolean                   isOnlyNumbers       = value.chars().allMatch(Character::isDigit);
		JSONArray                 jsonArray           = new JSONArray();
		Bson                      equalComparison     = eq(filterColum, isOnlyNumbers ? Integer.valueOf(value) : value);
		MongoCollection<Document> sipEventsCollection = database.getCollection(tableEvent);
		var                       collection          = sipEventsCollection.find(equalComparison);
		for (Document document : collection) {
			JSONObject jsonObject = new JSONObject();
			for (String key : document.keySet()) {
				jsonObject.put(key, document.get(key));
			}
			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}



	public void saveEvent (String tableEvent, String jsonStr,String jsonBody) {
		if (
				DB_HOST == null || DB_HOST.isEmpty() ||
				DB_PASSWORD == null || DB_PASSWORD.isEmpty() ||
				DB_USERNAME == null || DB_USERNAME.isEmpty()
		) {
			return;
		}
		MongoCollection<Document> sipEventsCollection = database.getCollection(tableEvent);
		try {
			var             newDoc = processJ(new Document(), new JSONObject(jsonStr));
			newDoc.put("original",jsonBody);
			InsertOneResult result = sipEventsCollection.insertOne(newDoc);
			//logger.info("Added new document: " + result);
		} catch (MongoException me) {
			//	logger.severe("Failed to insert document " + me.getMessage());
			me.printStackTrace();
		}
	}


}
