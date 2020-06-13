package com.google.sps.data;

import java.util.Date;
import com.google.appengine.api.datastore.Entity;


/** Class containing a user's comment. */
public class Comment {

  private final String username;
  private final Date date;
  private final String comment;

  public Comment(String username, Date date, String comment ) {
    this.username = username;
    this.date = date;
    this.comment = comment;
  }

  public String getUsername(){
    return username;
  }

  public Date getDate(){
    return date;
  }

  public String getComment(){
    return comment;
  }
  
  /**
   * Returns a new Comment from an entity of kind "Comment".
   * @param {Entity} commentEntity - entity of kind "Comment" with various 
   *     properties similar to the fields of a Comment object.
   */
  public static Comment fromEntity(Entity commentEntity){
    String username = (String) commentEntity.getProperty("username");    
    Date date = (Date) commentEntity.getProperty("date");
    String comment = (String) commentEntity.getProperty("comment");
    return new Comment(username, date, comment);
  }

  /**
   * Returns a new Entity of kind "Comment" from a Comment object.
   * @param {Comment} comment - the Comment object that will be made into an
   *     Entity.
   */
  public static Entity toEntity(Comment comment){
    Entity commentEntity = new Entity("Comment");
    commentEntity.setProperty("username", comment.username);
    commentEntity.setProperty("date", comment.date);
    commentEntity.setProperty("comment", comment.comment);
    return commentEntity;
  }
}

