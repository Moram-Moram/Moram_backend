package radiantMoramMoram.MoramMoram.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radiantMoramMoram.MoramMoram.payload.request.admin.AdminSignInRequest;
import radiantMoramMoram.MoramMoram.payload.response.admin.AdminSignInResponse;
import radiantMoramMoram.MoramMoram.service.admin.AdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/auth")
public class AuthController {

    private AdminService adminService;

    @PostMapping
    public AdminSignInResponse signIn(@RequestBody AdminSignInRequest adminSignInRequest) {
        return adminService.adminSignIn(adminSignInRequest);
    }

}
