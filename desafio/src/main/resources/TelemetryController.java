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

    public static class GyroscopeData {
        private double x;
        private double y;
        private double z;


        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public double getZ() {
            return z;
        }

        public void setZ(double z) {
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
        private double latitude;
        private double longitude;


        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
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
        private String photoBase64;

        
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
}
