package com.itbatia.crud.repository.database;

import com.itbatia.crud.molel.Tag;
import com.itbatia.crud.repository.TagRepository;

import static com.itbatia.crud.utils.DatabaseConnection.*;

import java.sql.*;
import java.util.*;

public class DatabaseTagRepositoryImpl implements TagRepository {

    @Override
    public Tag save(Tag tag) {
        if (getAll().size() > 0) {
            for (Tag t : getAll()) {
                if (t.getName().equals(tag.getName())) {
                    return t;
                }
            }
        }
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String SQL_1 = "INSERT INTO tag (name) VALUES (?)";
        try {
            preparedStatement = connection.prepareStatement(SQL_1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tag.getName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            tag.setId(resultSet.getInt(1));
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
        return tag;
    }

    @Override
    public Tag getById(Integer integer) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String SQL = "SELECT * FROM tag WHERE id = ?";
        Tag tag = null;
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, integer);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                tag = new Tag(integer, resultSet.getString("name"));
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
        return tag;
    }

    @Override
    public List<Tag> getAll() {
        return getAllTagsInternal();
    }

    private List<Tag> getAllTagsInternal() {
        Connection connection = getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String SQL = "SELECT id, name FROM tag";
        List<Tag> tags = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setId(resultSet.getInt("id"));
                tag.setName(resultSet.getString("name"));
                tags.add(tag);
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
        return tags;
    }

    @Override
    public Tag update(Tag tag) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "UPDATE tag SET name = ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, tag.getName());
            preparedStatement.setInt(2, tag.getId());
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
        return tag;
    }

    @Override
    public void deleteById(Integer integer) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "DELETE FROM tag WHERE id = ?";
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
