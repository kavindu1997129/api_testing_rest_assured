package restapi;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import groovy.json.JsonException;
import io.restassured.path.json.JsonPath;

public class JsonReadWrite {
    
    public static String runtimeJsonPath = "./src/test/runtimeData/runtime.json";
    
    public static void addApiToJson(String apiId) {
        
        JSONObject jsonObject = new JSONObject();
            
        try {
          JSONObject apiIdToJson = new JSONObject();
          apiIdToJson.put("apiId", apiId);  
            
          JSONParser parser = new JSONParser();
          Object obj = parser.parse(new FileReader(runtimeJsonPath));
            jsonObject = (JSONObject) obj;
            JSONArray apisList = (JSONArray)jsonObject.get("apis");
            apisList.add(apiIdToJson);
            jsonObject.put("apis", apisList);
            try (FileWriter file = new FileWriter(runtimeJsonPath)) {
              file.write(jsonObject.toJSONString()); 
              file.flush();
   
          } catch (IOException e) {
              e.printStackTrace();
          }
        } catch (Exception e) {
          
        }
          
   }
    
   public static String readApiId(int indexOfApiInList) {
       
       JSONObject jsonObject = new JSONObject();
       JSONParser parser = new JSONParser();
       try {
           Object obj = parser.parse(new FileReader(runtimeJsonPath));
           jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
       JSONArray apis = (JSONArray)jsonObject.get("apis");
       JSONObject getApi = (JSONObject)apis.get(indexOfApiInList);
       String getApiId = (String)getApi.get("apiId");
       
       return getApiId;
   }
    
   public static void addAppToJson(String appId) {
       
       JSONObject jsonObject = new JSONObject();
       
       try {
         JSONObject apiIdToJson = new JSONObject();
         apiIdToJson.put("appId", appId);  
         JSONParser parser = new JSONParser();
         Object obj = parser.parse(new FileReader(runtimeJsonPath));
           jsonObject = (JSONObject) obj;
           JSONArray appsList = (JSONArray)jsonObject.get("apps");
           appsList.add(apiIdToJson);
           jsonObject.put("apps", appsList);
           
           try (FileWriter file = new FileWriter(runtimeJsonPath)) {
             file.write(jsonObject.toJSONString()); 
             file.flush();
  
         } catch (IOException e) {
             e.printStackTrace();
         }
       } catch (Exception e) {
         
       }
       
   }
   
   public static String readAppId(int indexOfApiInList) {
       
       JSONObject jsonObject = new JSONObject();
       JSONParser parser = new JSONParser();
       try {
           Object obj = parser.parse(new FileReader(runtimeJsonPath));
           jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
       JSONArray apps = (JSONArray)jsonObject.get("apps");
       JSONObject getApp = (JSONObject)apps.get(indexOfApiInList);
       String getAppId = (String)getApp.get("appId");
       
       return getAppId;
   }
   
   public static void addKeys(String appId, String keyType, String keyObject) {
       
       JSONObject jsonObject = new JSONObject();
       JSONParser parser = new JSONParser();
       try {
           Object obj = parser.parse(new FileReader(runtimeJsonPath));
           jsonObject = (JSONObject) obj;
           JSONArray apisList = (JSONArray)jsonObject.get("apps");
           
           int i = 0;
           JSONArray apps = (JSONArray) jsonObject.get("apps");
           JSONObject getApp = (JSONObject)apps.get(i);
           String getAppId = "";
           
           while(!getAppId.trim().equals(appId.trim())) {
               
               getApp = (JSONObject)apps.get(i);
               getAppId = (String)getApp.get("appId");
               
               if(getAppId.trim().equals(appId.trim())) {
                   JSONParser parser2 = new JSONParser();
                   JSONObject jsonKeyObject = (JSONObject) parser2.parse(keyObject);
                   getApp.put(keyType, jsonKeyObject);
                   break;
               }
               i += 1;
           }
           
           try (FileWriter file = new FileWriter(runtimeJsonPath)) {
               file.write(jsonObject.toJSONString()); 
               file.flush();

           } catch (IOException e) {
               e.printStackTrace();
           }
        } catch (Exception e) {
            // TODO: handle exception
        }
       
   }
   
   public static String getAccessTokenOfApiFromApp(String appId) {
       
       JSONObject jsonObject = new JSONObject();
       JSONParser parser = new JSONParser();
       String getAccessToken = "";
       try {
           Object obj = parser.parse(new FileReader(runtimeJsonPath));
           jsonObject = (JSONObject) obj;
           
           int i = 0;
           JSONArray apps = (JSONArray) jsonObject.get("apps");
           JSONObject getApp = (JSONObject)apps.get(i);
           String getAppId = "";
           int appsArraySize = apps.size();
           
           while(!getAppId.trim().equals(appId.trim()) && i < appsArraySize) {
               
               getApp = (JSONObject)apps.get(i);
               JSONObject getSandbox = (JSONObject)getApp.get("sandbox");
               JSONObject getToken = (JSONObject)getSandbox.get("token");
               getAccessToken = (String)getToken.get("accessToken");
               
               i += 1;
               return getAccessToken;
           }
           
        } catch (Exception e) {
            // TODO: handle exception
        }
       return getAccessToken;
   }
  
   
}
