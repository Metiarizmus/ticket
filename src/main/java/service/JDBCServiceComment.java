package service;

import connect.PropertyInf;
import entity.Comment;
import enums.StateProperties;


import java.util.ArrayList;
import java.util.List;

public class JDBCServiceComment {

    private JDBCServiceGeneral<Comment> serviceGeneral = new JDBCServiceGeneral<>();

    private PropertyInf propertyInf = new PropertyInf();
    private final String insertComment = propertyInf.getProperties(StateProperties.SQL).getProperty("INSERT_COMMENT");

    public boolean addCommentInDB(Comment comment) {

        List<String> commentFields = new ArrayList<>();
        commentFields.add(comment.getCommentary());
        commentFields.add(String.valueOf(comment.getOrder().getId()));

        return serviceGeneral.addInDB(commentFields,insertComment);
    }



}
