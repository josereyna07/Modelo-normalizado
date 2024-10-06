package uao.edu.project.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uao.edu.project.backend.Entidades.Cita;
import uao.edu.project.backend.citaMedicaAPPRepository.CitaRepository;
import uao.edu.project.backend.citaMedicaAPPRepository.MedicoRepository;
import uao.edu.project.backend.citaMedicaAPPRepository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Cita")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;  // Inyección del repositorio de Paciente

    @Autowired
    private MedicoRepository medicoRepository;      // Inyección del repositorio de Médico

    @GetMapping
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable("id") String id) {
        Optional<Cita> cita = citaRepository.findById(id);
        return cita.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        if (pacienteRepository.existsById(cita.getPacienteId()) && medicoRepository.existsById(cita.getMedicoId())) {
            Cita nuevaCita = citaRepository.save(cita);
            return ResponseEntity.ok(nuevaCita);
        }
        return ResponseEntity.badRequest().build(); // Si el paciente o médico no existen
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable String id, @RequestBody Cita cita) {
        if (!citaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cita.setId(id);
        Cita updatedCita = citaRepository.save(cita);
        return ResponseEntity.ok(updatedCita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable String id) {
        if (!citaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        citaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
