package com.itbatia.crud.repository.database;

import com.itbatia.crud.molel.*;
import com.itbatia.crud.repository.PostRepository;

import static com.itbatia.crud.utils.DatabaseConnection.*;

import java.sql.*;
import java.util.*;

public class DatabasePostRepositoryImpl implements PostRepository {

    @Override
    public Post save(Post post) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String SQL_1 = "INSERT INTO post (content, post_status) VALUES (?, ?)";
        try {
            preparedStatement = connection.prepareStatement(SQL_1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setString(2, post.getStatus().toString());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            post.setId(resultSet.getInt(1));
            insertIdInTablePostTag(post.getId(), post.getTags());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return post;
    }

    private void insertIdInTablePostTag(Integer postId, List<Tag> tags) {
        deleteIdInTablePostTag(postId);
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "INSERT INTO post_tag (post_id, tag_id) VALUES (?, ?)";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            for (Tag tag : tags) {
                preparedStatement.setInt(1, postId);
                preparedStatement.setInt(2, tag.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void deleteIdInTablePostTag(Integer postId) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "DELETE FROM post_tag WHERE post_id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Post getById(Integer integer) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String SQL = "SELECT id, content, post_status FROM post WHERE id = ?";
        Post post = null;
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, integer);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setContent(resultSet.getString("content"));
                post.setStatus((PostStatus.valueOf(resultSet.getString("post_status"))));
                post.setTags(getAllTagsByPostId(post.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        return getAllPostsInternal();
    }

    private List<Post> getAllPostsInternal() {
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<Post> posts = new ArrayList<>();
        String SQL = "SELECT id, content, post_status FROM post";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setContent(resultSet.getString("content"));
                post.setStatus(PostStatus.valueOf(resultSet.getString("post_status")));
                post.setTags(getAllTagsByPostId(post.getId()));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return posts;
    }

    public List<Tag> getAllTagsByPostId(Integer postId) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String SQL_1 = "SELECT tag_id FROM post_tag WHERE post_id = ?";
        String SQL_2 = "SELECT name FROM tag WHERE id = ?";
        List<Integer> tagsId = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL_1);
            preparedStatement.setInt(1, postId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tagsId.add(resultSet.getInt("tag_id"));
            }
            preparedStatement = connection.prepareStatement(SQL_2);
            for (Integer tagId : tagsId) {
                preparedStatement.setInt(1, tagId);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    tags.add(new Tag(tagId, resultSet.getString("name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tags;
    }

    @Override
    public Post update(Post post) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "UPDATE post SET content = ?, post_status = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, post.getContent());
            preparedStatement.setString(2, post.getStatus().toString());
            preparedStatement.setInt(3, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        insertIdInTablePostTag(post.getId(), post.getTags());
        return post;
    }

    @Override
    public void deleteById(Integer integer) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "DELETE FROM post WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, integer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
