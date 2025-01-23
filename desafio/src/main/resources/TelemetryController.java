import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {

    // Endpoint para dados do giroscópio
    @PostMapping("/gyroscope")
    public ResponseEntity<String> receiveGyroscopeData(@RequestBody GyroscopeData data) {
        // Lógica para processar os dados do giroscópio
        System.out.println("Dados do giroscópio recebidos: " + data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dados do giroscópio processados com sucesso.");
    }

    // Endpoint para dados do GPS
    @PostMapping("/gps")
    public ResponseEntity<String> receiveGpsData(@RequestBody GpsData data) {
        // Lógica para processar os dados do GPS
        System.out.println("Dados do GPS recebidos: " + data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dados do GPS processados com sucesso.");
    }

    // Endpoint para dados da foto
    @PostMapping("/photo")
    public ResponseEntity<String> receivePhotoData(@RequestBody PhotoData data) {
        // Lógica para processar os dados da foto
        System.out.println("Dados da foto recebidos: " + data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dados da foto processados com sucesso.");
    }

}
