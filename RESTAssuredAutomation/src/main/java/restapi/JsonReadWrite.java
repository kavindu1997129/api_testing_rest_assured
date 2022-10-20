package restapi;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReadWrite {
    
    public static void addApiToJson(String apiId) {
        
        JSONObject jsonObject = new JSONObject();
            
        try {
          JSONObject apiIdToJson = new JSONObject();
          apiIdToJson.put("apiId", apiId);  
            
          JSONParser parser = new JSONParser();
          Object obj = parser.parse(new FileReader("./src/test/runtimeData/runtime.json"));
            jsonObject = (JSONObject) obj;
            JSONArray apisList = (JSONArray)jsonObject.get("apis");
            apisList.add(apiIdToJson);
            jsonObject.put("apis", apisList);
            try (FileWriter file = new FileWriter("./src/test/runtimeData/runtime.json")) {
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
           Object obj = parser.parse(new FileReader("./src/test/runtimeData/runtime.json"));
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
         Object obj = parser.parse(new FileReader("./src/test/runtimeData/runtime.json"));
           jsonObject = (JSONObject) obj;
           JSONArray apisList = (JSONArray)jsonObject.get("apps");
           apisList.add(apiIdToJson);
           jsonObject.put("apps", apisList);
           try (FileWriter file = new FileWriter("./src/test/runtimeData/runtime.json")) {
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
           Object obj = parser.parse(new FileReader("./src/test/runtimeData/runtime.json"));
           jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
       JSONArray apps = (JSONArray)jsonObject.get("apps");
       JSONObject getApp = (JSONObject)apps.get(indexOfApiInList);
       String getAppId = (String)getApp.get("appId");
       
       return getAppId;
   }
  
   
}
