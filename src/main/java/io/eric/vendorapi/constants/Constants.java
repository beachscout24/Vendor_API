package io.eric.vendorapi.constants;

public class Constants {
	
	public static final Object DELETE_OK = """
   {
   		"acknowledged":true,
   		"deletedCount": 1
   }
			""";
	
	public static String GET_BY_ID_ERROR(String id){
		return """
    	{
    		"message":"Unable to retrieve Vendor with ID: %S"
    	}
			""".formatted(id);
	}
	
	public static Object BAD_DELETE_REQUEST(String id) {
		return """
    	{
    		"message":"Unable to delete request for ID: %S"
    	}
				""".formatted(id);
	}
	
	public static Object GET_VENDOR_ERROR(Integer page, Integer size) {
		return """
    	{
    		"message":"Unable to handle your request for page %S and size %S"
    	}
			""".formatted(page, size);
	}
}
