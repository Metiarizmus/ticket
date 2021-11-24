package service;

import connect.DBConnection;
import connect.PropertyInf;
import entity.Comment;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCServiceComment {

    private JDBCServiceGeneral<Comment> serviceGeneral = new JDBCServiceGeneral<>();

    private PropertyInf propertyInf = new PropertyInf();
    private final String insertComment = propertyInf.getSqlQuery().getProperty("INSERT_COMMENT");

    public boolean addCommentInDB(Comment comment) {

        List<String> commentFields = new ArrayList<>();
        commentFields.add(comment.getCommentary());
        commentFields.add(String.valueOf(comment.getOrder().getId()));

        return serviceGeneral.addInDB(commentFields,insertComment);
    }



}
