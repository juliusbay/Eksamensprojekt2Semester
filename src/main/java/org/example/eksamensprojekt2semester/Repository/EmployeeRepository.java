package org.example.eksamensprojekt2semester.Repository;

import org.example.eksamensprojekt2semester.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class EmployeeRepository {

    @Autowired
    DataSource dataSource;

    // Method for retrieving specific employees by employeeId
    public Employee getEmployeeByEmployeeId(int id){
        Employee employee = new Employee();
        String sql = "SELECT * FROM employee WHERE employee_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        employee.setEmployeeId(resultSet.getInt("employee_id"));
                        employee.setFirstName(resultSet.getString("first_name"));
                        employee.setLastName(resultSet.getString("last_name"));
                        employee.setShortName(resultSet.getString("short_name"));
                        employee.setEmail(resultSet.getString("email"));
                        employee.setPassword(resultSet.getString("password"));

                        // The string value from the DB needs to be converted to an Enum again before it can be set for the user.
                        String roleAsString = resultSet.getString("role");
                        Employee.Role role = Employee.Role.valueOf(roleAsString);
                        employee.setRole(role);
                    }
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    // Method for creating employee users. ID is auto-increment in DB, so that is not used.
    public void createEmployee(Employee employee){
        String sql = "INSERT INTO employee (first_name, last_name, short_name, email, password, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getShortName());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getPassword());

            // The Role enum from the user object needs to be converted to a string before it can be stored in the database.
            String roleAsString = employee.getRole().toString();
            statement.setString(6, roleAsString);

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Method for updating employees in the database.
    public void updateEmployee(Employee employee){
        String sql = "UPDATE employee SET first_name = ?, last_name = ?, short_name = ?, email = ?, password = ?, role = ? WHERE employee_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getShortName());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getPassword());

            // The Role enum from the Employee object needs to be converted to a string before it can be stored in the database.
            String roleAsString = employee.getRole().toString();
            statement.setString(6, roleAsString);

            statement.setInt(7, employee.getEmployeeId());

            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Method for deleting employees by their employeeId.
    public void deleteEmployeeByEmployeeId(int id){
        String sql = "DELETE FROM employee WHERE employee_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
