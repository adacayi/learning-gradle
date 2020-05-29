package uk.co.sancode.learning_gradle.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.sancode.learning_gradle.model.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Service
public class SqlHelper {
    @Autowired
    private DataSource defaultDataSource;

    public SqlHelper(DataSource defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

    public void persistAndFlush(User user) {
        var sql = String.format(
                "insert into user(user_id, name, last_name, date_of_birth) values ('%s', '%s', '%s', '%s')",
                user.getUserId().toString(),
                user.getName(),
                user.getLastName(),
                user.getDateOfBirth());

        executeSqlCommand(sql);
    }

    public User findById(UUID id) {
        var sql = String.format("select * from user where user_id = '%s'", id);
        var results = executeSqlQuery(sql, mapResultSetToEntities());

        if (results.size() == 1) {
            return results.get(0);
        }

        if (results.size() > 1) {
            throw new IllegalStateException("Multiple items found with same id");
        }

        return null;
    }

    public <T> List<T> executeSqlQuery(String sql, Function<ResultSet, List<T>> mapper) {
        try (
                var connection = defaultDataSource.getConnection();
                var statement = connection.createStatement();
                var results = statement.executeQuery(sql)) {
            return mapper.apply(results);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean executeSqlCommand(String sql) {
        boolean result = false;

        try (
                var connection = defaultDataSource.getConnection();
                var statement = connection.createStatement()) {
            result = statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    private Function<ResultSet, List<User>> mapResultSetToEntities() {
        return t -> {
            try {
                return mapResultSetToEntities(t);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    private List<User> mapResultSetToEntities(ResultSet results) throws SQLException {
        var mappedResults = new ArrayList<User>();

        while (results.next()) {
            var user = new User(
                    UUID.fromString(results.getString("user_id")),
                    results.getString("name"),
                    results.getString("lastName"),
                    results.getDate("date_of_birth").toLocalDate());
            mappedResults.add(user);
        }

        return mappedResults;
    }
}
