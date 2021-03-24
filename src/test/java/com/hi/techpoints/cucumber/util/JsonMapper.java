package com.hi.techpoints.cucumber.util;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class JsonMapper {

	public static final ObjectMapper INSTANCE = new ObjectMapper();
	private static final Logger log = LoggerFactory.getLogger(JsonMapper.class);

	public static <T> List<T> jsonStrRootToList(String rootNodeContent, String key, Class<T> valueType) {
		if(rootNodeContent == null || key == null || valueType == null) return null;
		try {
			JsonNode root = INSTANCE.readTree(rootNodeContent);
			if(root == null || root.get(key) == null) {
				log.error("No valid data to parse");
				return null;
			}
			return jsonObjectToList(root.get(key), valueType);
		} catch (Exception e) {
			log.error("Failed to parse json string. =====" + rootNodeContent + "=====", e);
		}
		return null;
	}

	public static <T> List<T> jsonStrToList(String content, Class<T> valueType) {
		if(content == null || valueType == null) return null;
		try {
			JsonNode node = INSTANCE.readTree(content);
			if(node == null) {
				log.error("No valid data to parse");
				return null;
			}
			return jsonObjectToList(node, valueType);
		} catch (Exception e) {
			log.error("Failed to parse json string. =====" + content + "=====", e);
		}
		return null;
	}

	public static <T> List<T> jsonObjectToList(JsonNode node, Class<T> valueType) {
		if(node == null || valueType == null) return null;
		try {
			return INSTANCE.convertValue(node, INSTANCE.getTypeFactory().constructCollectionType(List.class, valueType));
		} catch (Exception e) {
			log.error("Failed to parse json object", e);
		}
		return null;
	}

	public static <K, V> Map<K, V> jsonStrToMap(String content, Class<K> keyType, Class<V> valueType) {
		if(content == null || keyType == null || valueType == null) return null;
		try {
			JsonNode node = INSTANCE.readTree(content);
			if(node == null) {
				log.error("No valid data to parse");
				return null;
			}
			return jsonObjectToMap(node, keyType, valueType);
		} catch (Exception e) {
			log.error("Failed to parse json string. =====" + content + "=====", e);
		}
		return null;
	}

	public static <K, V> Map<K, V> jsonObjectToMap(JsonNode node, Class<K> keyType, Class<V> valueType) {
		if(node == null || valueType == null) return null;
		try {
			return INSTANCE.convertValue(node, INSTANCE.getTypeFactory().constructMapLikeType(Map.class, keyType, valueType));
		} catch (Exception e) {
			log.error("Failed to parse json object", e);
		}
		return null;
	}

	public static <T> T jsonStrRootToBean(String rootNodeContent, String key, Class<T> valueType) {
		if(rootNodeContent == null || key == null || valueType == null) return null;
		try {
			JsonNode root = INSTANCE.readTree(rootNodeContent);
			if(root == null || root.get(key) == null) {
				log.error("No valid data to parse");
				return null;
			}
			return jsonObjectToBean(root.get(key), valueType);
		} catch (Exception e) {
			log.error("Failed to parse json string. =====" + rootNodeContent + "=====", e);
		}
		return null;
	}

	public static <T> T jsonStrToBean(String content, Class<T> valueType) {
		if(content == null || valueType == null) return null;
		try {
			JsonNode node = INSTANCE.readTree(content);
			if(node == null) {
				log.error("No valid data to parse");
				return null;
			}
			return jsonObjectToBean(node, valueType);
		} catch (Exception e) {
			log.error("Failed to parse json string. =====" + content + "=====", e);
		}
		return null;
	}

	public static <T> T jsonObjectToBean(JsonNode node, Class<T> valueType) {
		if(node == null || valueType == null) return null;
		try {
			return INSTANCE.treeToValue(node, valueType);
		} catch (Exception e) {
			log.error("Failed to parse json object", e);
		}
		return null;
	}

	public static JsonNode jsonStrRootToObject(String rootNodeContent, String key) {
		if(rootNodeContent == null || key == null) return null;
		try {
			JsonNode root = INSTANCE.readTree(rootNodeContent);
			if(root == null || root.get(key) == null) {
				log.error("No valid data to parse");
				return null;
			}
			return root.get(key);
		} catch (Exception e) {
			log.error("Failed to parse json string. =====" + rootNodeContent + "=====", e);
		}
		return null;
	}

	public static JsonNode jsonStrToObject(String content) {
		if(content == null) return null;
		try {
			return INSTANCE.readTree(content);
		} catch (Exception e) {
			log.error("Failed to parse json string. =====" + content + "=====", e);
		}
		return null;
	}

	public static ObjectNode createObjectNode() {
		return INSTANCE.createObjectNode();
	}

	public static ArrayNode createArrayNode() {
		return INSTANCE.createArrayNode();
	}

	public static String jsonObjectToStr(ObjectNode node) {
		if(node == null) return null;
		try {
			return INSTANCE.writeValueAsString(node);
		} catch (Exception e) {
			log.error("Failed to parse json object", e);
		}
		return null;
	}

	public static JsonNode objectToJsonObject(Object obj) {
		if(obj == null) return null;
		try {
			return INSTANCE.valueToTree(obj);
		} catch (Exception e) {
			log.error("Failed to parse object", e);
		}
		return null;
	}

	public static ObjectNode objectToObjectNode(Object obj) {
		if(obj == null) return null;
		try {
			return INSTANCE.valueToTree(obj);
		} catch (Exception e) {
			log.error("Failed to parse object", e);
		}
		return null;
	}

	public static String beanToStr(Object obj) {
		if(obj == null) return null;
		try {
			return INSTANCE.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("Failed to parse object", e);
		}
		return null;
	}

	/**
	 * Find Object by value
	 * @param JsonObject
	 * @param value
	 * @return JsonNode
	 */
	public static JsonNode findObjectByValue(JsonNode rootNode, String fieldName, Object value) {
		if(rootNode != null) {
			for (JsonNode jsonNode : rootNode) {
				if(jsonNode.findValuesAsText(fieldName).contains(value)) {
					return jsonNode;
				}
			}
		}
		return null;
	}
}
