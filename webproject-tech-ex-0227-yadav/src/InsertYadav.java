
/**
 * @file InsertYadav.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertYadav")
public class InsertYadav extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertYadav() {
      super();
   }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      String productName = request.getParameter("productName");
      String productDescription = request.getParameter("productDescription");
      String price = request.getParameter("price");
      String availability = request.getParameter("availability");
      String storeName = request.getParameter("storeName");
      String purchaseDate = request.getParameter("purchaseDate");

      Connection connection = null;

      String insertSql = " INSERT INTO MyTableYadavTech0227 (id, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRICE, AVAILABILITY, STORE_NAME, PURCHASE_DATE) VALUES (default, ?, ?, ?, ?, ?, ?)";
      try {
         DBConnectionYadav.getDBConnection(getServletContext());
         connection = DBConnectionYadav.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, productName);
         preparedStmt.setString(2, productDescription);
         preparedStmt.setString(3, price);
         preparedStmt.setString(4, availability);
         preparedStmt.setString(5, storeName);
         preparedStmt.setString(6, purchaseDate);

         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();      
      String title = "Insert Data to DB table";      
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";     
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //
            "  <li><b>Product Name</b>: " + productName + "\n" + //
            "  <li><b>Product Description</b>: " + productDescription + "\n" + //
            "  <li><b>Price</b>: " + price + "\n" + //
            "  <li><b>Availability</b>: " + availability + "\n" + //
            "  <li><b>Store Name</b>: " + storeName + "\n" + //
            "  <li><b>Purchase Date</b>: " + purchaseDate + "\n" + //

            "</ul>\n");

      out.println("<a href=/webproject-tech-ex-0227-yadav/simpleFormSearch.html>Search Data</a> <br>");
      out.println("</body></html>"); 
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
