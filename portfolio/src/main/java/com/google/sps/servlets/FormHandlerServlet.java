package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String name = request.getParameter("user-name");
    String email = request.getParameter("user-email");
    String message = request.getParameter("user-message");
    String funFact = request.getParameter("user-fun-fact");

    // Print the value so you can see it in the server logs.
    System.out.println("{Name: " + name + "; Email: " + email + "; Message: " + message + (funFact.length() > 0 ? ("; Fun Fact: " + funFact) : "") + "}");

    //Redirect To Home Page
    response.sendRedirect("http://rwasim-sps-summer22.appspot.com/");
  }
}