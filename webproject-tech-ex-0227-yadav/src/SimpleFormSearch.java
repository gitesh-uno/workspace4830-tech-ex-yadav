/**
 * @file SimpleFormSearch.java
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormSearch")
public class SimpleFormSearch extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormSearch() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String keyword = request.getParameter("keyword");
      search(keyword, response);
   }

   void search(String keyword, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnectionYadav.getDBConnection(getServletContext());
         connection = DBConnectionYadav.connection;

         if (keyword.isEmpty()) {
            String selectSQL = "SELECT * FROM MyTableYadavTech0227";
            preparedStatement = connection.prepareStatement(selectSQL);
         } else {
            String selectSQL = "SELECT * FROM MyTableYadavTech0227";
      //      String theUserName = keyword + "%";
            preparedStatement = connection.prepareStatement(selectSQL);
      //      preparedStatement.setString(1, theUserName);
         }
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
            int id = rs.getInt("id");
            String productName = rs.getString("PRODUCT_NAME").trim();
            String productDescription = rs.getString("product_description").trim();
            String price = rs.getString("price").trim();
            String availability = rs.getString("availability").trim();
            String storeName = rs.getString("store_name").trim();
            String purchaseDate = rs.getString("purchase_Date").trim();            
            
            if (keyword.isEmpty() || productName.contains(keyword)) {
               out.println("ID: " + id + ", ");
               out.println("ProductName: " + productName + ", ");
               out.println("ProductDescription: " + productDescription + ", ");
               out.println("Price: " + price + ", ");
               out.println("Availability: " + availability + ", ");
               out.println("StoreName: " + storeName + ", ");
               out.println("PurchaseDate: " + purchaseDate + "<br>");
            }
            
            else if (keyword.isEmpty() || storeName.contains(keyword)) {
                out.println("ID: " + id + ", ");
                out.println("ProductName: " + productName + ", ");
                out.println("ProductDescription: " + productDescription + ", ");
                out.println("Price: " + price + ", ");
                out.println("Availability: " + availability + ", ");
                out.println("StoreName: " + storeName + ", ");
                out.println("PurchaseDate: " + purchaseDate + "<br>");
             }
            
            else if (keyword.isEmpty() || availability.contains(keyword)) {
                out.println("ID: " + id + ", ");
                out.println("ProductName: " + productName + ", ");
                out.println("ProductDescription: " + productDescription + ", ");
                out.println("Price: " + price + ", ");
                out.println("Availability: " + availability + ", ");
                out.println("StoreName: " + storeName + ", ");
                out.println("PurchaseDate: " + purchaseDate + "<br>");
             }
            else if (keyword.isEmpty() || purchaseDate.contains(keyword)) {
                out.println("ID: " + id + ", ");
                out.println("ProductName: " + productName + ", ");
                out.println("ProductDescription: " + productDescription + ", ");
                out.println("Price: " + price + ", ");
                out.println("Availability: " + availability + ", ");
                out.println("StoreName: " + storeName + ", ");
                out.println("PurchaseDate: " + purchaseDate + "<br>");
             }
         }
         out.println("<a href=/webproject-tech-ex-0227-yadav/simpleFormSearch.html>Search Data</a> <br>");
         out.println("</body></html>");
         rs.close();
         preparedStatement.close();
         connection.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         } catch (SQLException se2) {
         }
         try {
            if (connection != null)
               connection.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
