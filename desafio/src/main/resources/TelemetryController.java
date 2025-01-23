import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {

    // Endpoint para dados do giroscópio
    @PostMapping("/gyroscope")
    public ResponseEntity<String> receiveGyroscopeData(@Valid @RequestBody GyroscopeData data) {
        // Lógica para processar os dados do giroscópio
        System.out.println("Dados do giroscópio recebidos: " + data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dados do giroscópio processados com sucesso.");
    }

    // Endpoint para dados do GPS
    @PostMapping("/gps")
    public ResponseEntity<String> receiveGpsData(@Valid @RequestBody GpsData data) {
        // Lógica para processar os dados do GPS
        System.out.println("Dados do GPS recebidos: " + data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dados do GPS processados com sucesso.");
    }

    // Endpoint para dados da foto
    @PostMapping("/photo")
    public ResponseEntity<String> receivePhotoData(@Valid @RequestBody PhotoData data) {
        // Lógica para processar os dados da foto
        System.out.println("Dados da foto recebidos: " + data);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dados da foto processados com sucesso.");
    }

    public static class GyroscopeData {
        @NotNull(message = "O valor de X é obrigatório.")
        private Double x;

        @NotNull(message = "O valor de Y é obrigatório.")
        private Double y;

        @NotNull(message = "O valor de Z é obrigatório.")
        private Double z;

        // Getters e Setters
        public Double getX() {
            return x;
        }

        public void setX(Double x) {
            this.x = x;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            this.y = y;
        }

        public Double getZ() {
            return z;
        }

        public void setZ(Double z) {
            this.z = z;
        }

        @Override
        public String toString() {
            return "GyroscopeData{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    '}';
        }
    }

    public static class GpsData {
        @NotNull(message = "A latitude é obrigatória.")
        private Double latitude;

        @NotNull(message = "A longitude é obrigatória.")
        private Double longitude;

        // Getters e Setters
        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "GpsData{" +
                    "latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
    }

    public static class PhotoData {
        @NotEmpty(message = "Os dados da foto são obrigatórios e não podem estar vazios.")
        private String photoBase64;

        // Getters e Setters
        public String getPhotoBase64() {
            return photoBase64;
        }

        public void setPhotoBase64(String photoBase64) {
            this.photoBase64 = photoBase64;
        }

        @Override
        public String toString() {
            return "PhotoData{" +
                    "photoBase64='" + photoBase64 + '\'' +
                    '}';
        }
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationExceptions(javax.validation.ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + ex.getMessage());
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidExceptions(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .reduce("", (msg1, msg2) -> msg1 + (msg1.isEmpty() ? "" : ", ") + msg2);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + errorMessage);
    }
}
