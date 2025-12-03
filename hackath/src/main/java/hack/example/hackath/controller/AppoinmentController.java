@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @GetMapping("/placeholder")
    public String placeholder() {
        return "Appointment API ready for onsite implementation.";
    }
}
