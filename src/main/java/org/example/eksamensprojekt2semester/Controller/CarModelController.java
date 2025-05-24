package org.example.eksamensprojekt2semester.Controller;

import org.example.eksamensprojekt2semester.Enum.CarEquipment;
import org.example.eksamensprojekt2semester.Enum.FuelType;
import org.example.eksamensprojekt2semester.Enum.GearBox;
import org.example.eksamensprojekt2semester.Model.CarModel;
import org.example.eksamensprojekt2semester.Repository.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarModelController {

    @Autowired
    private CarModelRepository carModelRepository;


    @PostMapping("/createCarModel")
    public String createCarModel(@RequestParam("car_model_name") String name,
                                 @RequestParam("car_model_brand") String brand,
                                 @RequestParam("fuel_type") String fuelTypeString,
                                 @RequestParam("car_model_year") int carModelYear,
                                 @RequestParam("gear_box") String gearBoxString,
                                 @RequestParam("car_emission") int emission,
                                 @RequestParam("car_model_equipment") String carEquipmentString,
                                 @RequestParam("steel_price") double steelPrice
                                 ) {

        FuelType fuelType = FuelType.fromString(fuelTypeString);
        GearBox gearBox = GearBox.fromString(gearBoxString);
        CarEquipment carEquipment = CarEquipment.fromString(carEquipmentString);

        CarModel carModel = new CarModel(name, brand, fuelType, carModelYear, gearBox, emission, carEquipment, steelPrice);

        carModelRepository.createCarModel(carModel);


        return "redirect:/";
    }

}
