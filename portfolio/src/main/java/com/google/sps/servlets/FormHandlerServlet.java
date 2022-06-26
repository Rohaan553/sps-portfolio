package com.google.sps.servlets;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@WebServlet("/form-handler")
@MultipartConfig
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Get the value entered in the form.
    String name = request.getParameter("user-name");
    String email = request.getParameter("user-email");
    String message = request.getParameter("user-message");
    String funFact = request.getParameter("user-fun-fact");

    // Get the user's profile picture
    Part imgPart = request.getPart("image-upload");
    String imgName = imgPart.getSubmittedFileName() + " (" + System.currentTimeMillis() + ")";
    InputStream imgInputStream = imgPart.getInputStream();

    uploadImgToCloudStorage(imgName, imgInputStream);

    // Print the value so you can see it in the server logs.
    System.out.println("{Name: " + name + "; Email: " + email + "; Message: " + message + (funFact.length() > 0 ? ("; Fun Fact: " + funFact) : "") + "}");

    //Redirect To Home Page
    response.sendRedirect("http://rwasim-sps-summer22.appspot.com/");
  }

  /** Uploads a file to Cloud Storage */
  private static void uploadImgToCloudStorage(String imgName, InputStream imgInputStream){
    String projectId = "rwasim-sps-summer22";
    String bucketName = "rwasim-sps-summer22.appspot.com";
    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

    // Upload the file to Cloud Storage.
    BlobId blobId = BlobId.of(bucketName, imgName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

    storage.create(blobInfo, imgInputStream);
  }
}