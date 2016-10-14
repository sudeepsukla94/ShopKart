package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CatalogServ
 */

//This is to do Edit Orders !!! Try retaining whatever added in the first order page !!
@WebServlet("/CatalogServ")
public class CatalogServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(Connection con = DBConnection.getConnect()){
			
			String sql = "select * from product";
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			List<Product> list = new ArrayList<Product>();
			while (rs.next())
		      {
				Product product = new Product();
				product.setProdId(rs.getString("prodid"));
				product.setProdName(rs.getString("prodname"));
				product.setCost(rs.getInt("price"));
				product.setInventory(rs.getInt("quantity"));
				list.add(product);
		      }
			
			System.out.println(request.getHeader("Referer")+ "  Referrer !!!!");
	
			if(request.getHeader("Referer").equals("http://localhost:9090/Meera.ShopKart/AddtoCart")){
				
				ArrayList<Product> listtemp = new ArrayList<Product>();
				HttpSession session2 = request.getSession(false);
				listtemp = (ArrayList<Product>)session2.getAttribute("cart");
				
				ArrayList<Product> productlist= new ArrayList<Product>();
				HttpSession session1 = request.getSession(false);
				productlist = (ArrayList<Product>)session1.getAttribute("product");
				
				for(int i=0;i<listtemp.size();i++){
					String pid=listtemp.get(i).getProdId();
					for(int j=0;j<productlist.size();j++){
						if(pid.equals(productlist.get(j).getProdId())){
							listtemp.get(i).setInventory(productlist.get(j).getInventory());
							break;
						}
					}
				}
				
				request.getSession().setAttribute("product", listtemp);
				
			
			
			}
			else {
				
				HttpSession s = request.getSession();
				s.setAttribute("product", list);
				
			}
		        RequestDispatcher view = request.getRequestDispatcher("Catalog.jsp");
		          view.forward(request, response);		          
		      			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
