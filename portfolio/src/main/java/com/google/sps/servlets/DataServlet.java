// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.util.Date;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns  comments from the webpage.*/
@WebServlet("/comments")
public class DataServlet extends HttpServlet {
    
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    int maxCommentsToDisplay =  maxCommentsToDisplay(request);

    Query query = new Query("Comment");
    query.addSort("date", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);
    
    List<Comment> commentList = new ArrayList<>();   
    for (Entity commentEntity : results.asIterable(
      FetchOptions.Builder.withLimit(maxCommentsToDisplay))) {
      commentList.add(Comment.fromEntity(commentEntity));
    }

    Gson gson = new Gson();
    String json = gson.toJson(commentList);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    String username = request.getParameter("username");
    String comment = request.getParameter("comment");

    datastore.put(Comment.toEntity(new Comment(username, new Date(), comment)));

    Gson gson = new Gson();
    String json = gson.toJson(comment);
    response.setContentType("application/json;");
    response.getWriter().println(json);
    response.sendRedirect("/");
  }

   /** Returns the max entered by the user, or default to 5. */
  private int maxCommentsToDisplay(HttpServletRequest request) {
    // Get the input from the form.
    String maxCommentsString = request.getParameter("max-comments");

    // Convert the input to an int.
    int maxComment;
    try {
      maxComment = Integer.parseInt(maxCommentsString);
    } 
    catch (NumberFormatException e) {
      maxComment = 5;
    }
    return maxComment;
  }
}
