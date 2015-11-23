import junit.framework.*;
import com.mockobjects.servlet.*;

public class TestLabConverterServlet extends TestCase {
    
    public void test_bad_parameter() throws Exception {
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
    
//    public void test_boil() throws Exception {
//        TestingLabConverterServlet s = new TestingLabConverterServlet();
//        MockHttpServletRequest request =
//        new MockHttpServletRequest();
//        MockHttpServletResponse response =
//        new MockHttpServletResponse();
    
        
        
//        request.setupAddParameter("Fahrenheit", "212");
//        response.setExpectedContentType("text/html");
//        s.doGet(request,response);
//        response.verify();
//        assertEquals("Fahrenheit: 212, Celsius: 100.0\n",
//                     response.getOutputStreamContents());
//    }
    
}
