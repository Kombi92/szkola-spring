package kolobry.projekt.service;

import kolobry.projekt.domain.Car;
import kolobry.projekt.domain.Person;

import java.util.List;

public interface SellingManager {
	
	void addClient(Person person);
	List<Person> getAllClients();
	void deleteClient(Person person);
	Person findClientByPin(String pin);
	
	Long addNewCar(Car car);
	List<Car> getAvailableCars();
	void disposeCar(Person person, Car car);
	Car findCarById(Long id);

	List<Car> getOwnedCars(Person person);
	void sellCar(Long personId, Long carId);

}
