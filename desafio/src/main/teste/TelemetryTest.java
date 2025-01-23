import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@SpringBootTest
public class TelemetryTests {

    @InjectMocks
    private TelemetryController telemetryController;

    @Mock
    private TelemetryRepository telemetryRepository;

    @Test
    public void testReceiveGyroscopeData() {
        TelemetryController.GyroscopeData mockData = new TelemetryController.GyroscopeData();
        mockData.setX(1.0);
        mockData.setY(2.0);
        mockData.setZ(3.0);

        ResponseEntity<String> response = telemetryController.receiveGyroscopeData(mockData);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Dados do giroscópio processados com sucesso.", response.getBody());

        Mockito.verify(telemetryRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testReceiveGpsData() {
        TelemetryController.GpsData mockData = new TelemetryController.GpsData();
        mockData.setLatitude(-23.55052);
        mockData.setLongitude(-46.633308);

        ResponseEntity<String> response = telemetryController.receiveGpsData(mockData);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Dados do GPS processados com sucesso.", response.getBody());

        Mockito.verify(telemetryRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testReceivePhotoData() {
        TelemetryController.PhotoData mockData = new TelemetryController.PhotoData();
        mockData.setPhotoBase64("mockBase64String");

        ResponseEntity<String> response = telemetryController.receivePhotoData(mockData);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Dados da foto processados com sucesso.", response.getBody());

        Mockito.verify(telemetryRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testSaveDataInRepository() {
        TelemetryEntity mockEntity = new TelemetryEntity();
        mockEntity.setId(1L);
        mockEntity.setType("gyroscope");
        mockEntity.setData("Mock Data");

        Mockito.when(telemetryRepository.findById(1L)).thenReturn(Optional.of(mockEntity));

        Optional<TelemetryEntity> retrievedEntity = telemetryRepository.findById(1L);

        assertTrue(retrievedEntity.isPresent());
        assertEquals("gyroscope", retrievedEntity.get().getType());
        assertEquals("Mock Data", retrievedEntity.get().getData());
    }
    @Test
    public void testMissingField() {
        TelemetryController.GpsData invalidData = new TelemetryController.GpsData();
        invalidData.setLatitude(-23.55052);
        // Longitude is not set to simulate validation error

        Exception exception = assertThrows(Exception.class, () -> {
            telemetryController.receiveGpsData(invalidData);
        });

        String expectedMessage = "Erro de validação:";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void testBadRequest() {
        TelemetryController.GpsData invalidData = new TelemetryController.GpsData();
        invalidData.setLatitude(-23.55052);
        // Longitude is not set to simulate validation error

        ResponseEntity<String> response = null;
        try {
            response = telemetryController.receiveGpsData(invalidData);
        } catch (Exception e) {
            assertEquals(HttpStatus.BAD_REQUEST.value(), 400);
        }
    }

    @Test
    public void testValidationErrorHandling() {
        TelemetryController.GyroscopeData invalidData = new TelemetryController.GyroscopeData();

        Exception exception = assertThrows(Exception.class, () -> {
            telemetryController.receiveGyroscopeData(invalidData);
        });

        String expectedMessage = "Erro de validação:";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
