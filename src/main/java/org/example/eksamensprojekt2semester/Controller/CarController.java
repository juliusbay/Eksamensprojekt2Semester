package org.example.eksamensprojekt2semester.Controller;


import jakarta.servlet.http.HttpSession;
import org.example.eksamensprojekt2semester.Enum.Status;
import org.example.eksamensprojekt2semester.Model.Car;
import org.example.eksamensprojekt2semester.Model.CarModel;
import org.example.eksamensprojekt2semester.Repository.CarModelRepository;
import org.example.eksamensprojekt2semester.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.Timestamp;

@Controller
public class CarController {
    @Autowired
    CarRepository carRepository;

    @Autowired
    EmployeeController employeeController;

    @Autowired
    private CarModelRepository carModelRepository;





    @PostMapping("/createCar")
    public String getCreateCar(@RequestParam("car-model-id") int carModelId,
                               @RequestParam("vin-number") String vinNumber,
                               @RequestParam("color") String color, Model model,
                               HttpSession session) {


            if (!employeeController.isUserLoggedIn(session)) {
                return "redirect:/login";
            }


            CarModel carModel = carModelRepository.getCarModelById(carModelId);

            if (!carRepository.existsByVinNumber(vinNumber)){ //Hvis denne ikke kører, er der ikke nogen bil med samme vinNumber i databasen.
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

                Car car = new Car(carModel, vinNumber, color, currentTimestamp);
                car.setStatus(Status.READY);
                carRepository.createCar(car);
                model.addAttribute("cars", car);


                return "redirect:/dashboard";
            } else{
                // OBS!!!!!! SKAL TILFØJES EN ERROR HVIS BILEN ALLEREDE EKSISTERER. Muligvis i fragments hvis vi bruger dette.
                return "redirect:/dashboard";
            }
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("vehicleId") int vehicleId){
        System.out.println("Received vehicleId to delete: " + vehicleId);

        carRepository.deleteCarById(vehicleId);
        return "redirect:/dashboard";
    }



}
