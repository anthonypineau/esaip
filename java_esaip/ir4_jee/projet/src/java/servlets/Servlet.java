/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Data;
import database.JDBC;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anthony
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameterNames().hasMoreElements()){
            if(request.getParameterNames().nextElement().equals("search")){
                if(request.getParameterValues("search")[0].equals("all")){
                    request.setAttribute( "data", JDBC.getUsersByClauseAll(request.getParameterValues("clause")[0]));
                    request.setAttribute( "count", JDBC.countUserByClauseAll(request.getParameterValues("clause")[0]));
                }else{
                    request.setAttribute( "data", JDBC.getUsersByClause(request.getParameterValues("search")[0],request.getParameterValues("clause")[0]));
                    request.setAttribute( "count", JDBC.countUserByClause(request.getParameterValues("search")[0],request.getParameterValues("clause")[0]));
                }
                request.setAttribute("search", true);
            }else{
                Map<String, String[]> incommingData = request.getParameterMap();
                process(incommingData);
                request.setAttribute( "data", JDBC.getUsers());
                request.setAttribute( "count", JDBC.countUser());
                request.setAttribute("search", false);
            }
        }else{
            request.setAttribute( "data", JDBC.getUsers());
            request.setAttribute( "count", JDBC.countUser());
            request.setAttribute("search", false);
        }
        
        request.getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
    }

    private void process(Map<String, String[]> incommingData) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        ArrayList<Data> data = new ArrayList();
        BeanInfo infoData = Introspector.getBeanInfo(Data.class);
        String[] temp =(String[])(incommingData.values().toArray()[0]);
        for(int i=0;i<temp.length;i++){
            data.add(new Data());
        }
        for(Map.Entry<String, String[]> entry : incommingData.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            for(int j=0;j<values.length;j++){
                for(PropertyDescriptor property : infoData.getPropertyDescriptors()){
                    if(key.equals(property.getName())){
                        property.getWriteMethod().invoke(data.get(j), values[j]);
                    }
                }
            }
        }
        for(Data bean : data){
            JDBC.insertUser(bean);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IntrospectionException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IntrospectionException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
