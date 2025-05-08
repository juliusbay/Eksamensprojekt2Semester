package org.example.eksamensprojekt2semester.Controller;


import org.example.eksamensprojekt2semester.Model.Car;
import org.example.eksamensprojekt2semester.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.util.ArrayList;

@Controller
public class CarController {
    @Autowired
    CarRepository carRepository;

    @Autowired
    DataSource dataSource;



    // SKAL HOMEREDIRECT SKAL ÆNDRES, BRUGES BARE TIL AT TESTE FOR NU!!
    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/cars"; //Returnere til testsiden, skal ændres.
    }




    @GetMapping("/cars")
    public String getCars(Model model) {

        ArrayList<Car> cars = carRepository.getAllCars();
        model.addAttribute("cars", cars);
        return "carsTestSide";

    }


    @PostMapping("/createCar")
    public String getCreateCar(@RequestParam("car-model-id") int carModelId,
                               @RequestParam("vin-number") String vinNumber,
                               @RequestParam("monthly-price") double monthlyPrice,
                               @RequestParam("color") String color){


            if (!carRepository.existsByVinNumber(vinNumber)){ //Hvis denne ikke kører, er der ikke nogen bil med samme vinNumber i databasen.
                Car car = new Car(carModelId, vinNumber, color, monthlyPrice);
                carRepository.createCar(car);

                return "redirect:/";
            } else{
                // OBS!!!!!! SKAL TILFØJES EN ERROR HVIS BILEN ALLEREDE EKSISTERER. Muligvis i fragments hvis vi bruger dette.
                return "redirect:/";
            }
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("car-id") int carId){
        carRepository.deleteCarById(carId);
        return "redirect:/";
    }

    @PostMapping("/getUpdateCar")
    public String updateCar(Model model, String vinNumber){

        Car car = carRepository.getCarByVinNumber(vinNumber);
        model.addAttribute("car", car);
        carRepository.updateCar(car);
        return "updateCar";

    }


}
