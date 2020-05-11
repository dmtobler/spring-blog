import org.springframework.web.bind.annotation.*;

public class MathController {
    @GetMapping(path = "/add/{num1}/and/{num2}")
    @ResponseBody
    public double add(@PathVariable double num1, @PathVariable double   num2) {
        return (num1 + num2);
    }

    @GetMapping(path = "/subtract/{num1}/from/{num2}")
    @ResponseBody
    public double subtract(@PathVariable double num1, @PathVariable double num2) {
        return (num1 - num2);
    }

    @GetMapping(path = "/multiply/{num1}/and/{num2}")
    @ResponseBody
    public double multiply(@PathVariable double num1, @PathVariable double num2) {
        return (num1 * num2);
    }

    @GetMapping(path = "/divide/{num1}/by/{num2}")
    @ResponseBody
    public double divide(@PathVariable double num1, @PathVariable double num2) {
        return (num1 / num2);
    }
}