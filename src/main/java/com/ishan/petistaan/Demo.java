package com.ishan.petistaan;

import com.ishan.petistaan.dto.OwnerDto;
import com.ishan.petistaan.dto.PetDto;
import com.ishan.petistaan.service.OwnerService;
import com.ishan.petistaan.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:messages.properties")
@SpringBootApplication
public class Demo implements CommandLineRunner {

	private final OwnerService ownerService;
	private final PetService petService;
	private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

	// Constructor injection for OwnerService and PetService => no need to mention @Autowired since it's the only constructor
	public Demo(OwnerService ownerService, PetService petService) {
		this.ownerService = ownerService;
		this.petService = petService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Demo.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("||||||||||||||||||||||||||||||||||||||||||||||||||||||||| START");
		logAllInfo();
		LOGGER.info("||||||||||||||||||||||||||||||||||||||||||||||||||||||||| SAVING DUPLICATE TEST");
		LOGGER.info("Saving owner with ID 1");
		OwnerDto ownerDto = new OwnerDto();
		ownerDto.setId(1);
		ownerService.saveOwner(ownerDto);
		LOGGER.info("||||||||||||||||||||||||||||||||||||||||||||||||||||||||| UPDATE TEST");
		LOGGER.info("Updating pet name for owner with ID 1");
		ownerService.updatePetName(1, "Tommy");
		LOGGER.info("Updating pet name for owner with ID 2");
		ownerService.updatePetName(2, "Milo");
		logAllInfo();
		LOGGER.info("||||||||||||||||||||||||||||||||||||||||||||||||||||||||| GET TEST");
		LOGGER.info("Fetching owner with ID 1");
		OwnerDto owner1 = ownerService.getOwnerById(1);
		LOGGER.info("Fetching owner with ID 2");
		OwnerDto owner2 = ownerService.getOwnerById(2);
		LOGGER.info("Owner 1: " + owner1);
		LOGGER.info("Owner 2: " + owner2);
		LOGGER.info("||||||||||||||||||||||||||||||||||||||||||||||||||||||||| GET EXCEPTION TEST");
		LOGGER.info("Fetching owner with ID 3");
		OwnerDto owner3 = ownerService.getOwnerById(3);
		LOGGER.info("Owner 3: " + owner3);
		LOGGER.info("||||||||||||||||||||||||||||||||||||||||||||||||||||||||| DELETE TEST");
		LOGGER.info("Deleting owner with ID 1");
		ownerService.deleteOwner(1);
		LOGGER.info("Deleting owner with ID 2");
		ownerService.deleteOwner(2);
		logAllInfo();
		LOGGER.info("||||||||||||||||||||||||||||||||||||||||||||||||||||||||| DELETE EXCEPTION TEST");
		LOGGER.info("Deleting owner with ID 1");
		ownerService.deleteOwner(1);
		LOGGER.info("||||||||||||||||||||||||||||||||||||||||||||||||||||||||| END");
	}

	private void logAllInfo() {
		LOGGER.info("All Owners:");
		for (OwnerDto owner : ownerService.getAllOwners()) {
			LOGGER.info(owner.toString());
		}

		LOGGER.info("All Pets:");
		for (PetDto pet : petService.getAllPets()) {
			LOGGER.info(pet.toString());
		}
	}
}
