package org.example.eksamensprojekt2semester.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Enum.Role;
import org.example.eksamensprojekt2semester.Model.Employee;
import org.example.eksamensprojekt2semester.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    // Feature for handling login/user validation
    @PostMapping("/processLogin")
    public String processLogin(
            @RequestParam("shortname") String shortName,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        String sql = "SELECT * FROM employee WHERE short_name = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, shortName);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){      // If resultSet.next runs, it has found a matching shortname and will start to validate password
                    String storedPassword = resultSet.getString("password");
                    if (storedPassword.equals(password))
                    {
                        Employee employee = new Employee();
                        employee.setEmployeeId((resultSet.getInt("employee_id")));
                        employee.setFirstName(resultSet.getString("first_name"));
                        employee.setLastName(resultSet.getString("last_name"));
                        employee.setShortName(resultSet.getString("short_name"));
                        employee.setEmail(resultSet.getString("email"));
                        employee.setRoleFromString(resultSet.getString("role"));

                        session.setAttribute("loggedInUser", employee);

                        return "redirect:/";

                    } else { // If the user cannot be validated, it will display "error" attribute.
                        redirectAttributes.addFlashAttribute("error", "Ugyldigt brugernavn eller kode");
                        return "redirect:/login";
                    }
                } else {
                    redirectAttributes.addFlashAttribute("error", "Ugyldigt brugernavn eller kode");
                    return "redirect:/login";
                }
            }
        }

        catch (SQLException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Database fejl:" +e.getMessage());
        }
        return "redirect:/";
    }

    // Method for deleting employees
    @PostMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employee_id") int employeeId) {
        employeeRepo.deleteEmployeeByEmployeeId(employeeId);

        return "redirect:/";
    }

    // GetMapping for editing employees.
    @GetMapping("/edit-employee")
    public String Employee(@RequestParam("employee_id") int employeeId, Model model, HttpSession session){
        Employee employee = employeeRepo.getEmployeeByEmployeeId(employeeId);
        model.addAttribute(employee);

        return "edit-employee";
    }

    // PostMapping for handling the update of an employee
    @PostMapping("/postEditEmployee")
    public String postEditEmployee(
            @RequestParam("employee_id") int employeeId,
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            @RequestParam("short_name") String shortName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("role") String roleAsString) {

        String upperCaseRole = roleAsString.toUpperCase();
        Role role = Role.valueOf(upperCaseRole);


        Employee employee = new Employee(employeeId, firstName, lastName, shortName, email, password, role);

        employeeRepo.updateEmployee(employee);

        return "redirect:/";
    }

    //PostMapping for creating new employees.
    @PostMapping("/createEmployee")
    public String createEmployee(
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("role") String roleAsString) {

        String upperCaseRole = roleAsString.toUpperCase();
        Role role = Role.valueOf(upperCaseRole);


        Employee employee = new Employee(firstName, lastName, email, password, role);

        employeeRepo.createEmployee(employee);

        return "redirect:/";
    }

    // Validation of user-session to enure that user is actually logged in
    public boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("loggedInUser") != null;
    }
}

