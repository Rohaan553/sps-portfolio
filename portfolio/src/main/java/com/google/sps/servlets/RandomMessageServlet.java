package com.google.sps.servlets;

import java.io.IOException;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/random-message")
public class RandomMessageServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String[] messages = {"My favorite cake is cheesecake", "I grow mint in my garden", "I have ridden a camel before"};

    Gson gson = new Gson();
    String messagesAsJson = gson.toJson(messages);

    response.setContentType("application/json;");
    response.getWriter().println(messagesAsJson);
  }
}
