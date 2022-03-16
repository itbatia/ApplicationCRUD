package com.itbatia.crud.repository.database;

import com.itbatia.crud.molel.*;
import com.itbatia.crud.repository.WriterRepository;

import static com.itbatia.crud.utils.DatabaseConnection.getConnection;

import java.sql.*;
import java.util.*;

public class DatabaseWriterRepositoryImpl implements WriterRepository {
    DatabasePostRepositoryImpl databasePostRepository = new DatabasePostRepositoryImpl();

    @Override
    public Writer save(Writer writer) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String SQL = "INSERT INTO writer (name) VALUES (?)";
        try {
            preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, writer.getName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            writer.setId(resultSet.getInt(1));
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
        writer.getPosts().forEach(post -> saveWriterIdInTablePost(writer.getId(), post));
        return writer;
    }

    private void saveWriterIdInTablePost(Integer writerId, Post post) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "UPDATE post SET writer_id = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, writerId);
            preparedStatement.setInt(2, post.getId());
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
    public Writer getById(Integer integer) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String SQL = "SELECT id, name FROM writer WHERE id = ?";
        Writer writer = null;
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, integer);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                writer = new Writer();
                writer.setId(resultSet.getInt("id"));
                writer.setName(resultSet.getString("name"));
                writer.setPosts(getAllPostsByWriterId(writer.getId()));
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
        return writer;
    }

    @Override
    public List<Writer> getAll() {
        return getAllWriterInternal();
    }

    private List<Writer> getAllWriterInternal() {
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<Writer> writers = new ArrayList<>();
        String SQL = "SELECT id, name FROM writer";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Writer writer = new Writer();
                writer.setId(resultSet.getInt("id"));
                writer.setName(resultSet.getString("name"));
                writer.setPosts(getAllPostsByWriterId(writer.getId()));
                writers.add(writer);
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
        return writers;
    }

    private List<Post> getAllPostsByWriterId(Integer integer) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String SQL = "SELECT id, content, post_status FROM post WHERE writer_id = ?";
        List<Post> posts = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, integer);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getInt("id"));
                post.setContent(resultSet.getString("content"));
                post.setStatus(PostStatus.valueOf(resultSet.getString("post_status")));
                post.setTags(databasePostRepository.getAllTagsByPostId(post.getId()));
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
        return posts;
    }

    @Override
    public Writer update(Writer writer) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "UPDATE writer SET name = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, writer.getName());
            preparedStatement.setInt(2, writer.getId());
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

        if (writer.getPosts() != null) {
            updatePostsByWriterID(writer);
        }
        return writer;
    }

    private void updatePostsByWriterID(Writer writer) {
        setNullInWriterIdInTablePost(writer);
        writer.getPosts().forEach(post -> saveWriterIdInTablePost(writer.getId(), post));
    }

    private void setNullInWriterIdInTablePost(Writer writer) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "UPDATE post SET writer_id = ? WHERE writer_id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, null);
            preparedStatement.setInt(2, writer.getId());
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

//    private void deletePostsByWriterId(Integer writerId) {
//        Connection connection = getConnection();
//        PreparedStatement preparedStatement = null;
//        String SQL = "DELETE FROM post WHERE writer_id = ?";
//        try {
//            preparedStatement = connection.prepareStatement(SQL);
//            preparedStatement.setInt(1, writerId);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    @Override
    public void deleteById(Integer integer) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "DELETE FROM writer WHERE id = ?";
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
