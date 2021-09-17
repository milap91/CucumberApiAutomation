package resources;

import io.cucumber.messages.internal.com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class utils {
	public static RequestSpecification rs;
	
	public String getJsonParserKeyValue(Response response,String key)
	{
		
		JsonPath js= new JsonPath(response.asString());
		String actualvalue=js.get(key).toString();
		return actualvalue;
		
	}
	
	public static String getpropertyvalue(String key) throws IOException
	{
		Properties p= new Properties();
		FileInputStream fis= new FileInputStream("E:\\milap_workspace\\CucumberApiProject\\src\\test\\java\\resources\\Config.properties");
		p.load(fis);
		String value=p.getProperty(key);
		return value;
		
	}
	
	public RequestSpecification reqspecification_subset() throws IOException
	{
		if(rs==null)
		{
			PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
			rs= new RequestSpecBuilder().setBaseUri(getpropertyvalue("baseurl")).addQueryParam("key", "qaclick123")
					 .addFilter(RequestLoggingFilter.logRequestTo(log))
					 .addFilter(ResponseLoggingFilter.logResponseTo(log))
			.setContentType(ContentType.JSON).build();
			return rs;
		}
		return rs;
		
	}
}
