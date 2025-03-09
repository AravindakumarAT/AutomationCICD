package framework.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader
{

	public List<HashMap<String,String>> JSONDataToMap() throws IOException
	{
		//JSON TO STRING
		String json=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\framework\\data\\PurchaseOrder.json"),StandardCharsets.UTF_8);
		//STRING TO HASHMAP
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(json,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
}
