import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {
    @Autowired
    private TourRepository tourRepository;

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Tour getTourById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }

    public Tour createTour(Tour tour) {
        return tourRepository.save(tour);
    }

    public Tour updateTour(Long id, Tour updatedTour) {
        Tour tour = getTourById(id);
        if (tour != null) {
            tour.setName(updatedTour.getName());
            tour.setLocation(updatedTour.getLocation());
            tour.setDescription(updatedTour.getDescription());
            tour.setPrice(updatedTour.getPrice());
            tour.setCapacity(updatedTour.getCapacity());
            tour.setSchedule(updatedTour.getSchedule());
            return tourRepository.save(tour);
        }
        return null;
    }

    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }
}

@Service
public class FarmService {
    @Autowired
    private FarmRepository farmRepository;

    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    public Farm getFarmById(Long id) {
        return farmRepository.findById(id).orElse(null);
    }

    public Farm createFarm(Farm farm) {
        return farmRepository.save(farm);
    }

    public Farm updateFarm(Long id, Farm updatedFarm) {
        Farm farm = getFarmById(id);
        if (farm != null) {
            farm.setType(updatedFarm.getType());
            farm.setName(updatedFarm.getName());
            farm.setQuantity(updatedFarm.getQuantity());
            farm.setDescription(updatedFarm.getDescription());
            farm.setAge(updatedFarm.getAge());
            farm.setWeight(updatedFarm.getWeight());
            farm.setColor(updatedFarm.getColor());
            return farmRepository.save(farm);
        }
        return null;
    }

    public void deleteFarm(Long id) {
        farmRepository.deleteById(id);
    }
}
