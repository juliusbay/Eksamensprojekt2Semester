package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Model.Employee;
import org.example.eksamensprojekt2semester.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepo;

    @Autowired
    DataSource dataSource;

    public String processLogin(
            @RequestParam("username") String shortName,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        String sql = "SELECT * FROM employee WHERE short_name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, shortName);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){ //Hvis denne kører, er brugeren fundet. Kodeord bliver valideret i de to næste linjer.
                    String storedPassword = resultSet.getString("password");
                    if (storedPassword.equals(password))
                    {
                        Employee employee = new Employee();
                        employee.setEmployeeId((resultSet.getInt("user_id")));
                        employee.setEmail(resultSet.getString("email"));

                        session.setAttribute("loggedInUser", employee);

                        return "redirect:/profile";
                    } else { // Hvis ikke den kan validere loginet, bliver "error"-model displayet
                        redirectAttributes.addFlashAttribute("error", "Ugyldigt brugernavn eller kode");
                        return "redirect:/";
                    }
                } else {
                    redirectAttributes.addFlashAttribute("error", "Ugyldigt brugernavn eller kode");
                    return "redirect:/";
                }
            }
        }

        catch (SQLException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Database fejl:" +e.getMessage());
        }
        return "redirect:/profile";
    }
}
