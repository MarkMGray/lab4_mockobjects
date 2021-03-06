import junit.framework.TestCase;
import com.mockobjects.servlet.*;

public class TestLabConverterServlet extends TestCase {
    
    // Create new test
    public TestLabConverterServlet (String name) {
        super(name);
    }
    
    public void test_no_parameter() throws Exception {
        System.out.println("Testing No Parameters");
        TestingLabConverterServlet s = new TestingLabConverterServlet();
        MockHttpServletRequest request =
        new MockHttpServletRequest();
        MockHttpServletResponse response =
        new MockHttpServletResponse();
        
        response.setExpectedContentType("text/html");
        
        s.doGet(request, response);
        response.verify();
        assertEquals("<html><head><title>No Temperature</title></head><body><h2>Need to enter a temperature!</h2></body></html>\n", response.getOutputStreamContents());
    }
    
    public void test_wrong_parameter() throws Exception {
        System.out.println("Testing Wrong Parameter");
        TestingLabConverterServlet s = new TestingLabConverterServlet();
        MockHttpServletRequest request =
        new MockHttpServletRequest();
        MockHttpServletResponse response =
        new MockHttpServletResponse();
        
        request.setupAddParameter("notTheRightParam", "80");
        response.setExpectedContentType("text/html");
        
        s.doGet(request, response);
        response.verify();
        assertEquals("<html><head><title>No Temperature</title></head><body><h2>Need to enter a temperature!</h2></body></html>\n", response.getOutputStreamContents());
    }
    
    public void test_non_double() throws Exception {
        System.out.println("Testing Non-Double");
        TestingLabConverterServlet s = new TestingLabConverterServlet();
        MockHttpServletRequest request =
        new MockHttpServletRequest();
        MockHttpServletResponse response =
        new MockHttpServletResponse();
        String farTemp = "Not-a-double!!";
        request.setupAddParameter("farenheitTemperature", farTemp);
        response.setExpectedContentType("text/html");
        
        s.doGet(request, response);
        response.verify();
        assertEquals("<html><head><title>Bad Temperature</title>"
                     + "</head><body><h2>Need to enter a valid temperature!"
                     + "Got a NumberFormatException on "
                     + farTemp
                     + "</h2></body></html>\n", response.getOutputStreamContents());
    }
    
    public void test_convert_value() throws Exception {
        System.out.println("Testing Conversion Value");
        TestingLabConverterServlet s = new TestingLabConverterServlet();
        MockHttpServletRequest request =
        new MockHttpServletRequest();
        MockHttpServletResponse response =
        new MockHttpServletResponse();
        String farTemp = "86";
        String celTemp = "30";
        String austinTemperature = "451";
        request.setupAddParameter("farenheitTemperature", farTemp);
        response.setExpectedContentType("text/html");
        
        s.doGet(request, response);
        response.verify();
        
        String expectedOutput = "<html><head><title>Temperature Converter Result</title>"
        + "</head><body><h2>" + farTemp + " Farenheit = " + celTemp + " Celsius "
        + "</h2>\n"
        + "<p><h3>The temperature in Austin is " + austinTemperature + " degrees Farenheit</h3>\n"
        + "</body></html>\n";
        
        assertEquals(expectedOutput, response.getOutputStreamContents());
        
    }
    
    public static void main(String args[]) {
        String[] testCaseName =
        { TestLabConverterServlet.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
    
    
}
